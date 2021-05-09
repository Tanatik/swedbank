/*
 **   Write by Oleksandr  aka TanaTik@gmail.com
 */
package swedbank.tests.ui;

import org.junit.Test;
import swedbank.pageObjects.swedbank.ConsentModalWidget;
import swedbank.pageObjects.swedbank.MortgageLoanCalculatorPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static swedbank.pageObjects.swedbank.MortgageLoanCalculatorPage.SITE_URL;

public class MortgageLoanCalculatorTest extends BaseTest {

    MortgageLoanCalculatorPage mortgageLoanCalculatorPage;

    private final String totalMonthlyIncome = "1000";
    private final String loanAmount = "45000";
    private final int loanTerm = 11;

    @Test
    public void signUpParticalTest() {
        this.driver.get(SITE_URL);
        mortgageLoanCalculatorPage = new MortgageLoanCalculatorPage();
        mortgageLoanCalculatorPage.confirmPage();
        new ConsentModalWidget().acceptAllCookies();
        mortgageLoanCalculatorPage.getLoanHomePrice();
        mortgageLoanCalculatorPage
                .setBorrowersCheck(false)
                .setDependantsCheck(true)
                .setTotalMonthlyIncome(totalMonthlyIncome)
                .setDependantsRatioTwo()
                .waitAnimation("49739 €")
                .setObligationsCheck(false)
                .setLoanTerm(loanTerm * 12 + "")
                .setLoanAmount(loanAmount);

        assertThat("Label loan amount is correct", mortgageLoanCalculatorPage.getChooseLoanAmountSliderLabel(), equalTo(loanAmount + " €"));
        assertThat("Label loan Term is correct", mortgageLoanCalculatorPage.getChooseLoanTermSliderLabel(), equalTo(loanTerm + " years"));

        assertThat("Result of calculation Insure your Home is correct", mortgageLoanCalculatorPage.getInsureYourHome(), equalTo("6.96"));
        assertThat("Result of calculation Insure your Life is correct", mortgageLoanCalculatorPage.getInsureYourLife(), equalTo("12.60"));
        assertThat("Result of calculation Insure your Loan payments is correct", mortgageLoanCalculatorPage.getInsureYourLoanPayments(), equalTo("11.59"));

        assertThat("Result of calculation Loan Monthly loan payment is correct", mortgageLoanCalculatorPage.getLoanMonthlyPayment(), equalTo("386"));
        assertThat("Result of calculation Loan Maximum loan amount is correct", mortgageLoanCalculatorPage.getLoanMaximumAmount(), equalTo("49739"));
        assertThat("Result of calculation Loan Maximum home price is correct", mortgageLoanCalculatorPage.getLoanHomePrice(), equalTo("0"));
    }


}
