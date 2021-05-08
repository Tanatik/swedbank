package swedbank.base;

import swedbank.driver.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BlockObject
{
    final private Locator _locator;

    public BlockObject (Locator locator)
    {
        this._locator = locator;
    }

    public Locator getLocator ()
    {
        return this._locator;
    }

    public WebDriver driver ()
    {
        return DriverSetup.getInstance().getDriver();
    }

    public void confirmBlock ()
    {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver(), Timeouts.DEF_TIMEOUT_BLOCK_OBJECT_WAIT);
            wait.until(d -> d.findElements(this._locator.getWDLocator()).size() != 0);
        } catch (Throwable e) {
            throw new RuntimeException("I can not confirm that block is loaded. My name is " + this.getClass().getSimpleName());
        }
    }
}