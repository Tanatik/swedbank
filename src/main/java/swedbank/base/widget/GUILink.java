package swedbank.base.widget;

import swedbank.base.Locator;
import swedbank.base.Widget;

public class GUILink extends Widget
{
    public GUILink(Locator locator) {
        super(locator);
    }

    public void click ()
    {
        _logger.debug("Click " + this.locator().getWDLocator());
        this.waitAction();
        this.element().click();
        this.waitAction();
    }
}
