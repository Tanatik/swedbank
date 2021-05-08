package swedbank.driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.*;

import java.util.logging.Level;

public class DriverSetup {
    private static DriverSetup instance = new DriverSetup();
    private ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> {
        ChromeOptions option = new ChromeOptions();
        //option.addArguments("start-maximized");
        option.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        option.addArguments("--disable-cache");
        option.addArguments("--disable-web-security");
        //option.addArguments("disable-infobars");
        //option.addArguments("test-type");
        option.addArguments("disable-application-cache");
        //option.addArguments("disk-cache-size=0");
        option.addArguments("disable-gpu");
        option.addArguments("--no-sandbox");
        option.addArguments("--disable-dev-shm-usage");
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

    private DriverSetup() {
        ChromeDriverManager.chromedriver().setup();
    }

    public static DriverSetup getInstance() {
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
