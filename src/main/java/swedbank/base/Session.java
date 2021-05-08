package swedbank.base;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class Session {
//    private WebDriver _driver;
//    private static Session _instance;
    // private Session () { /* Empty */}

 /*   public static Session get ()
    {
        if (_instance == null)
            _instance = new Session();

        return _instance;
    }*/

/*
     public WebDriver driver ()
    {
        if (this._driver == null) {

            // System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", "/Volumes/D/Projects/Qa_auto/chromedriver");
            this._driver = new ChromeDriver();

*/
/*            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName("chrome");
            try {
                this._driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub/"), cap);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }*//*



        }
        return this._driver;
    }
*/

  /*  public void close ()      {
        if (this._driver != null) {
            this._driver.quit();
            this._driver = null;
        }
    }*/

    private static Session instance = new Session();
    private ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("start-maximized");
        option.addArguments("--disable-cache");
        option.addArguments("--disable-web-security");
        option.addArguments("disable-infobars");
        option.addArguments("test-type");
        option.addArguments("disable-application-cache");
        option.addArguments("disk-cache-size=0");
        option.addArguments("disable-gpu");
        option.addArguments("--window-size=1920,1080");
        option.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6)" +
                " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36 selenium UI test");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, option);

        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);

        return new ChromeDriver(option);
    });

    private Session() {
        ChromeDriverManager.chromedriver().setup();
    }

    public static Session getInstance() {
        return instance;
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void removeDriver() {
        driver.get().quit();
        driver.remove();
    }
}