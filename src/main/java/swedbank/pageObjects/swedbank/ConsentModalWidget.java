package swedbank.pageObjects.swedbank;

import swedbank.base.Locator;
import swedbank.base.PageObject;
import swedbank.base.widget.GUIButton;

public class ConsentModalWidget extends PageObject {

    private final GUIButton acceptAllCookies = new GUIButton(Locator.create("//ui-modal//button[contains(@class,'consent__accept-all-button')]"));

    @Override
    protected Locator readyLocator() {
        return acceptAllCookies.locator();
    }

    public ConsentModalWidget acceptAllCookies() {
        acceptAllCookies.click();
        return this;
    }
}
