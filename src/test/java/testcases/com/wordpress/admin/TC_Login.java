package testcases.com.wordpress.admin;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.admin.LoginPageObject;
import interfaces.messages.admin.LoginPageMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class TC_Login extends BaseTest {
    private WebDriver driver;
    private LoginPageObject loginPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("http://localhost/wordpress/wp-admin");
        loginPage = PageGeneratorManager.getLoginPage(driver);
    }

    @Parameters("browser")
    @Test(priority = 1)
    public void TC_Login_001_LoginWithEmptyUsername(String browserName) {
        log.info("Input to Password");
        loginPage.inputToPassword("123111");
        log.info("Click to Login button");
        loginPage.clickLoginButton();

        log.info("Verify error message");
        if (browserName.equalsIgnoreCase("firefox")) {
            verifyEquals(loginPage.getErrorMessageFromValidationPrompt("username"), LoginPageMessage.FILL_THIS_FIELD_VI);
        } else {
            verifyEquals(loginPage.getErrorMessageFromValidationPrompt("username"), LoginPageMessage.FILL_THIS_FIELD);
        }
    }

    @Parameters("browser")
    @Test(priority = 2)
    public void TC_Login_002_LoginWithEmptyPassword(String browserName) {
        log.info("Input to Username");
        loginPage.inputToUsername("test_username1");
        log.info("Click to Login button");
        loginPage.clickLoginButton();

        log.info("Verify error message");
        if (browserName.equalsIgnoreCase("firefox")) {
            verifyEquals(loginPage.getErrorMessageFromValidationPrompt("password"), LoginPageMessage.FILL_THIS_FIELD_VI);
        } else {
            verifyEquals(loginPage.getErrorMessageFromValidationPrompt("password"), LoginPageMessage.FILL_THIS_FIELD);
        }
    }

    @Test(priority = 3)
    public void TC_Login_003_LoginWithUserNotExists() {
        String username = "not_exist_username";
        log.info("Input to Username");
        loginPage.inputToUsername(username);
        log.info("Input to Password");
        loginPage.inputToPassword("123334");
        log.info("Click to Login button");
        loginPage.clickLoginButton();

        log.info("Verify error message");
        verifyEquals(loginPage.getErrorMessageFromBox(), String.format(LoginPageMessage.FM_USERNAME_NOT_EXIST, "username"));
    }

    @Test(priority = 4)
    public void TC_Login_004_LoginWithWrongPassword() {
        loginPage.inputToUsername(GlobalConstants.ADMIN_PAGE_USERNAME);
        loginPage.inputToPassword("wrong_password");
        loginPage.clickLoginButton();

        verifyEquals(loginPage.getErrorMessageFromBox(), String.format(LoginPageMessage.FM_WRONG_PASSWORD, GlobalConstants.ADMIN_PAGE_USERNAME));
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

}
