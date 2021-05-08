package swedbank.base;

import org.apache.log4j.Logger;
import swedbank.driver.DriverSetup;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;
import java.util.List;

public class Widget {

    final protected Logger _logger = Logger.getLogger(this.getClass());

    final private Locator _locator;

    public Widget(Locator locator) {
        this._locator = locator;
    }

    final public Locator locator() {
        return this._locator;
    }

    final protected WebDriver driver() {
        return DriverSetup.getInstance().getDriver();
    }

    final public WebElement element() {
        return this.element(Timeouts.DEF_TIMEOUT_WIDGET_WAIT);
    }

    final public WebElement element(int timeoutSeconds) {
        final SearchContext context = getLocatorContext(this._locator);
        WebDriverWait wait = new WebDriverWait(DriverSetup.getInstance().getDriver(), timeoutSeconds);
        wait.until(d -> {
            List<WebElement> list = context.findElements(this._locator.getWDLocator());
            return list.size() != 0;
        });
        //  Locator temp = this._locator;
        //  while (temp.getParent() != null) {
        //     temp = temp.getParent();
        //     System.out.print(this._locator.getWDLocator().toString() + " <- ");
        //  }
        // System.out.println(temp.getWDLocator().toString());
        return context.findElement(this._locator.getWDLocator());
    }

    static private SearchContext getLocatorContext(Locator locator) {
        SearchContext result;
        if (locator.getParent() == null) {
            result = DriverSetup.getInstance().getDriver();
        } else {
            SearchContext parentContext = getLocatorContext(locator.getParent());
            result = parentContext.findElement(locator.getParent().getWDLocator());
        }
        return result;
    }

    private JavascriptExecutor _executor;

    protected JavascriptExecutor executor() {
        if (_executor == null)
            this._executor = (JavascriptExecutor) this.driver();
        return this._executor;
    }

    public final WebElement scrollToElement() {
        WebElement element = this.element();
        //this.executor().executeScript("arguments[0].scrollIntoView(false);", element);
        this.executor().executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
        return element;
    }


    final public void moveCursorToElement() {
        this.scrollToElement();
        this.dispatchMouseEvent("mouseenter");
        this.dispatchMouseEvent("mouseover");
        this.dispatchMouseEvent("onmouseover");
    }

    final public void moveCursorOut() {
        this.scrollToElement();
        this.dispatchMouseEvent("mouseleave");
        this.dispatchMouseEvent("mouseout");
    }

    final public boolean isExist(int timeoutSeconds) {
        boolean exists = false;
        long endTime = Instant.now().getEpochSecond() + timeoutSeconds;
        while (true) {
            try {
                this.element(0);
                exists = true;
            } catch (Throwable e) {

            }
            if (exists || endTime <= Instant.now().getEpochSecond())
                break;
            try {
                Thread.sleep(500);
            } catch (Throwable throwable) {

            }
        }
        return exists;
    }

    final public boolean isExist() {
        return this.isExist(Timeouts.DEF_TIMEOUT_WIDGET_WAIT);
    }


    private void dispatchMouseEvent(String eventName) {
        this.executor().executeScript(
                "var e = document.createEvent('Events');" +
                        "e.initEvent('" + eventName + "',true,false);" +
                        "arguments[0].dispatchEvent(e);",
                this.element()
        );
    }

    final protected void waitAction() {
        try {
            Thread.sleep(Timeouts.DEF_TIMEOUT_WIDGET_ACTION_WAIT);
        } catch (Throwable e) { /* Ignore */ }
    }

    final public String getText() {
        waitAction();
        String text = this.element().getText().trim();
        _logger.debug("Get text for locator ( " + this.locator().getWDLocator() + " ) - result: " + text);
        return text;
    }

    final public String getAttributeOfElement(String attributeOfElement) {
        String text = this.element().getAttribute(attributeOfElement);
        return text;
    }

}