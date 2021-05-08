package swedbank.tests.ui;

import org.junit.After;
import org.junit.Before;
import swedbank.driver.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import swedbank.driver.DriverSetup;

abstract public class BaseTest {
    public WebDriver driver;
    SoftAssert softAssert;

    @Before
    public void setup() {
        driver = DriverSetup.getInstance().getDriver();
        softAssert = getAssert();
    }

    @After
    public void closeBrowser() {
        DriverSetup.getInstance().removeDriver();
    }

    SoftAssert getAssert() {
        softAssert = new SoftAssert();
        return softAssert;
    }

    // public SignIn openLoginPage() {
    //  return openPage(getBaseURL(), SignIn.class);
    //}

    <T> T openPage(String link, Class<T> expectedPage) {
        driver.get(link);
        return PageFactory.initElements(driver, expectedPage);
    }
}