/*
 **   Write by Oleksandr  aka TanaTik@gmail.com
 */
package swedbank.pageObjects.swedbank;

import swedbank.base.Locator;
import swedbank.base.PageObject;
import swedbank.base.widget.GUICheckBox;
import swedbank.base.widget.GUIElement;
import swedbank.base.widget.GUIRatioBtn;
import swedbank.base.widget.GUITextBox;

import static swedbank.base.Timeouts.waitFewSec;

public class MortgageLoanCalculatorPage extends PageObject {

    public final static String SITE_URL = "https://www.swedbank.lt/private/credit/loans/home?language=ENG";

    private final GUIElement calculatorView = new GUIElement(Locator.create("#calculator-section"));
    private final GUICheckBox borrowersCheck = new GUICheckBox(Locator.create("//input[@id='borrowersCheck']"));
    private final GUICheckBox dependantsCheck = new GUICheckBox(Locator.create("//input[@id='dependantsCheck']"));
    private final GUIRatioBtn dependantsRatioOne = new GUIRatioBtn(Locator.create("//input[@id='dependants1']"));
    private String dependantsRatioTwoSt = "//input[@id='dependants2']";
    private GUIRatioBtn dependantsRatioTwo = new GUIRatioBtn(Locator.create(dependantsRatioTwoSt + "/../label[2]"));

    private final GUICheckBox obligationsCheck = new GUICheckBox(Locator.create("//input[@id='obligationsCheck']"));
    private final GUITextBox totalMonthlyIncome = new GUITextBox(Locator.create("//input[@id='income']"));
    private final GUITextBox chooseLoanAmount = new GUITextBox(Locator.create("//ui-slider[@id='slider1']//input[@class='ui-slider__input']"));
    private final GUIElement chooseLoanAmountSliderLabel = new GUIElement(Locator.create("//ui-slider[@id='slider1']//div[@class='ui-slider__value']"));
    private final GUITextBox chooseLoanTerm = new GUITextBox(Locator.create("//ui-slider[@id='slider2']//input[@class='ui-slider__input']"));
    private final GUIElement chooseLoanTermSliderLabel = new GUIElement(Locator.create("//ui-slider[@id='slider2']//div[@class='ui-slider__value']"));

    // Locators of results  calculation
    private final GUIElement insureYourHome = new GUIElement(Locator.create("#homeInsurancePayment"));
    private final GUIElement insureYourLife = new GUIElement(Locator.create("#lifeInsurancePayment"));
    private final GUIElement insureYourLoanPayments = new GUIElement(Locator.create("#loanInsurancePayment"));

    private final GUIElement loanMonthlyPayment = new GUIElement(Locator.create("#month-payment"));
    private final GUIElement loanMaximumAmount = new GUIElement(Locator.create("#slider-financed"));
    private final GUIElement loanHomePrice = new GUIElement(Locator.create("#maximumHomePrice"));

    @Override
    protected Locator readyLocator() {
        return calculatorView.locator();
    }

    // @Step("Set checkbox Applying with a co-applicant")
    public MortgageLoanCalculatorPage setBorrowersCheck(boolean isEnable) {
        if (isEnable) {
            borrowersCheck.set();
        } else {
            borrowersCheck.unset();
        }
        return this;
    }

    // @Step("Set checkbox More than one dependant in family")
    public MortgageLoanCalculatorPage setDependantsCheck(boolean isEnable) {
        if (isEnable) {
            dependantsCheck.set();
        } else {
            dependantsCheck.unset();
        }
        return this;
    }

    // @Step("Set ration in  More than one dependant in family 2 or more")
    public MortgageLoanCalculatorPage setDependantsRatioTwo() {
        wainUntilVisible(dependantsRatioTwoSt);
        dependantsRatioTwo.select();
        return this;
    }

    // @Step("Set checkbox Obligations")
    public MortgageLoanCalculatorPage setObligationsCheck(boolean isEnable) {
        if (isEnable) {
            obligationsCheck.set();
        } else {
            obligationsCheck.unset();
        }
        return this;
    }

    // @Step("Set Total Monthly Income")
    public MortgageLoanCalculatorPage setTotalMonthlyIncome(String value) {
        totalMonthlyIncome.setValue(value);
        return this;
    }

    // @Step("Set Loan Amount")
    public MortgageLoanCalculatorPage setLoanAmount(String value) {
        chooseLoanAmount.clearByBackSpace();
        chooseLoanAmount.setValue(value);
        insureYourLife.click();
        return this;
    }

    public MortgageLoanCalculatorPage waitAnimation(String text) {
        for (int i = 0; i < 10; i++) {
            if (text.equals(chooseLoanAmount.getAttributeOfElement("value"))) {
                _logger.debug("Animation is done!");
                break;
            } else {
                waitFewSec(500);
                _logger.debug("Current value " + chooseLoanAmount.getAttributeOfElement("value"));
                _logger.debug("wait:  500 msec ");
            }
        }
        return this;
    }

    // @Step("Set Loan Term")
    public MortgageLoanCalculatorPage setLoanTerm(String value) {
        chooseLoanTerm.setValue(value);
        return this;
    }

    // @Step("Get Label value on Slider choose Loan Amount")
    public String getChooseLoanAmountSliderLabel() {
        return chooseLoanAmountSliderLabel.getText();
    }

    // @Step("Get Label value on Slider choose Loan Term")
    public String getChooseLoanTermSliderLabel() {
        return chooseLoanTermSliderLabel.getText();
    }

    // @Step("Get Insure Your Home")
    public String getInsureYourHome() {
        return insureYourHome.getText();
    }

    // @Step("Get Insure Your Life")
    public String getInsureYourLife() {
        return insureYourLife.getText();
    }

    // @Step("Get insure Your Loan Payments")
    public String getInsureYourLoanPayments() {
        return insureYourLoanPayments.getText();
    }

    // @Step("Get Loan Monthly Payment")
    public String getLoanMonthlyPayment() {
        return loanMonthlyPayment.getText();
    }

    // @Step("Get Loan Maximum Amount")
    public String getLoanMaximumAmount() {
        return loanMaximumAmount.getText();
    }

    // @Step("Get Loan Home Price")
    public String getLoanHomePrice() {
        loanHomePrice.scrollToElement();
        return loanHomePrice.getText();
    }


}
