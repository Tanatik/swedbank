package swedbank.base.widget;

import swedbank.base.Locator;
import swedbank.base.Widget;
import org.openqa.selenium.support.ui.Select;

public class GUISelect extends Widget {

    public GUISelect(Locator locator) {
        super(locator);
    }

    final public void selectTextValue (String textValue) {

        _logger.debug("Set value \"" + textValue + "\" for " + locator().getWDLocator());
        // Select dropdown = new Select(driver.findElement(By.id("designation")));
        Select dropdown = new Select(element());
        dropdown.selectByVisibleText(textValue);
        this.waitAction();
    }


}
