package swedbank.base.widget;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import swedbank.base.Locator;
import swedbank.base.Widget;

public class GUICheckBox extends Widget {

    public GUICheckBox(Locator locator) {
        super(locator);
    }

    final public boolean isSelected() {
        return this.getInput().isSelected();
    }

    final public void set() {
        if (!this.isSelected())
            this.clickInputJS();
    }

    final public void unset() {
        if (this.isSelected())
            this.clickInputJS();
    }

    final public void click() {
        this.clickInputJS();
    }

    private WebElement getInput() {
        WebElement input;
/*        if (this.tagName().equalsIgnoreCase("input")) {*/
            input = this.element();
/*        } else {
            input = this.element().findElement(By.xpath(".//input[@type='checkbox']"));
        }*/
        return input;
    }

    private void clickInputJS() {
        // Workaround
        ((JavascriptExecutor) this.driver()).executeScript("arguments[0].click();", this.getInput());
    }
}
