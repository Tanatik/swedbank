package swedbank.base.widget;

import swedbank.base.Locator;
import swedbank.base.Widget;

public class GUIButton extends Widget {

    public GUIButton(Locator locator) {
        super(locator);
    }

    public void click() {
        _logger.debug("Click " + this.locator().getWDLocator());
        this.element().click();
        this.waitAction();
    }
}
