package actions.commons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.Duration;

public class BaseTest {
    private WebDriver driver;
    protected final Logger log = LogManager.getLogger(getClass());

    public WebDriver getDriver() {
        return driver;
    }

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
        this.driver = driver;

        return driver;
    }

    protected boolean verifyTrue(boolean condition) {
        boolean status = true;
        try {
            Assert.assertTrue(condition);
            log.info("---------------------- Passed -----------------------");
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("---------------------- Failed -----------------------");
        }
        return status;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean status = true;
        try {
            Assert.assertFalse(condition);
            log.info("---------------------- Passed -----------------------");
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("---------------------- Failed -----------------------");
        }
        return status;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("---------------------- Passed -----------------------");
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("---------------------- Failed -----------------------");
        }
        return status;
    }

//    @BeforeSuite
//    public void deleteFileInReport() {
//        // Remove all file in ReportNG screenshot (image)
//        deleteAllFileInFolder("reportNGImage");
//
//        // Remove all file in Allure attachment (json file)
//        deleteAllFileInFolder("allure-json");
//    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.RELATIVE_PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
