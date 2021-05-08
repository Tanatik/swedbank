package swedbank.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import swedbank.base.widget.GUIElement;
import swedbank.driver.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import swedbank.base.Locator;

abstract public class PageObject {

    final protected Logger _logger = Logger.getLogger(this.getClass());
    final protected WebDriver _driver;

    public PageObject() {
        this._driver = DriverSetup.getInstance().getDriver();
    }

    public void confirmPage() {
        // _logger.debug("Confirm page is load");
        try {
            WebDriverWait wait = new WebDriverWait(this._driver, Timeouts.DEF_TIMEOUT_PAGE_OBJECT_WAIT);
            wait.until(d -> d.findElements(this.readyLocator().getWDLocator()).size() != 0);
        } catch (Throwable e) {
            throw new RuntimeException("I can not confirm that page is loaded. My name is " + this.getClass().getSimpleName());
        }
    }

    public void wainUntilVisible(String xpathLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(this._driver, Timeouts.DEF_TIMEOUT_PAGE_OBJECT_WAIT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
        } catch (Throwable e) {
            throw new RuntimeException("I can not confirm that element is Display. My name is " + this.getClass().getSimpleName());
        }
    }

    abstract protected Locator readyLocator();

    public void switchToFrame(GUIElement _readyLocator) {
        _driver.switchTo().frame(_readyLocator.element());
    }

    public void switchToMainContent() {
        _driver.switchTo().defaultContent();
    }

}
