package swedbank.base.widget;

import org.openqa.selenium.By;
import swedbank.base.Locator;
import swedbank.base.Widget;

public class GUIRatioBtn extends Widget {

    public GUIRatioBtn(Locator locator) {
        super(locator);
    }

    final public String getName() {
        return this.element().findElement(By.xpath("./label")).getText();
    }

    final public boolean isSelected() {
        return this.element().findElement(By.xpath(".//input[@type='radio']")).isSelected();
    }

    final public void select() {
        this.waitAction();
        this.element().click();
        this.waitAction();
    }
}
