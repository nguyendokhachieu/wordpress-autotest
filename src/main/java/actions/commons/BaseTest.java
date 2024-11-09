package actions.commons;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.time.Duration;

public class BaseTest {

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        WebDriver driver;
        switch (browser) {
            case CHROME:
                driver = new ChromeDriver();
                break;

            case FIREFOX:
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("general.useragent.locale", "en-US");
                profile.setPreference("intl.accept_languages", "en-US");
                profile.setPreference("--lang", "en-us");
                FirefoxOptions options = new FirefoxOptions();
                options.setProfile(profile);
                driver = new FirefoxDriver(options);
                break;

            case EDGE:
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Browser name must be either ['chrome', 'firefox', 'edge']");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().maximize();

        return driver;
    }
}
