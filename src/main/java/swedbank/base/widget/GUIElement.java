package swedbank.base.widget;

import swedbank.base.Locator;
import swedbank.base.Widget;

public class GUIElement extends Widget
{
    public GUIElement(Locator locator) {
        super(locator);
    }

    final public void click ()
    {
        _logger.debug("Click " + this.locator().getWDLocator());
        this.waitAction();
        this.element().click();
        this.waitAction();
    }

}
