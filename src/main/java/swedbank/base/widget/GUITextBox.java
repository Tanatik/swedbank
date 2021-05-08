package swedbank.base.widget;

import org.openqa.selenium.Keys;
import swedbank.base.Locator;
import swedbank.base.Widget;

import static swedbank.base.Timeouts.waitFewSec;

public class GUITextBox extends Widget {

    public GUITextBox(Locator locator) {
        super(locator);
    }

    final public void setValue(String value) {
        _logger.debug("Set value \"" + value + "\" for " + locator().getWDLocator());
        if (!this.element().isEnabled()) {
            waitAction();
        }
        this.element().click();
        this.element().clear();
        this.element().sendKeys(Keys.BACK_SPACE + value);
        this.waitAction();
    }

    public void clearByBackSpace() {
        _logger.debug("Clear by back Space symbols length: " + this.element().getAttribute("value").length());
        if (this.element().getAttribute("value").length() > 0) {
            this.waitAction();
            for (int i = 0; i <= this.element().getAttribute("value").length() + 4; i++) {
                this.element().sendKeys(Keys.BACK_SPACE);
                waitFewSec(50);
            }
            this.waitAction();
        }
    }

    public void click() {
        _logger.debug("Click " + this.locator().getWDLocator());
        this.element().click();
        this.waitAction();
    }

}