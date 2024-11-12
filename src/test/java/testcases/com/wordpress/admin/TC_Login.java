package testcases.com.wordpress.admin;

import java.lang.reflect.Method;
import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.admin.LoginPageObject;
import actions.reportConfig.ExtentTestManager;
import com.aventstack.extentreports.Status;
import interfaces.messages.admin.LoginPageMessage;
import org.openqa.selenium.WebDriver;
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
        loginPage.inputToPassword("123111");
        loginPage.clickLoginButton();

        if (browserName.equalsIgnoreCase("firefox")) {
            verifyEquals(loginPage.getErrorMessageFromValidationPrompt("username"), LoginPageMessage.FILL_THIS_FIELD_VI);
        } else {
            verifyEquals(loginPage.getErrorMessageFromValidationPrompt("username"), LoginPageMessage.FILL_THIS_FIELD);
        }
    }

    @Parameters("browser")
    @Test(priority = 2)
    public void TC_Login_002_LoginWithEmptyPassword(String browserName) {
        loginPage.inputToUsername("test_username1");
        loginPage.clickLoginButton();

        if (browserName.equalsIgnoreCase("firefox")) {
            verifyEquals(loginPage.getErrorMessageFromValidationPrompt("password"), LoginPageMessage.FILL_THIS_FIELD_VI);
        } else {
            verifyEquals(loginPage.getErrorMessageFromValidationPrompt("password"), LoginPageMessage.FILL_THIS_FIELD);
        }
    }

    @Test(priority = 3)
    public void TC_Login_003_LoginWithUserNotExists(Method method) {
        ExtentTestManager.startTest(method.getName(), "Register to system with valid Email and Password");
        ExtentTestManager.getTest().log(Status.INFO, "TC_Login_003 - Step 01: Input to username");

        String username = "not_exist_username";
        loginPage.inputToUsername(username);
        loginPage.inputToPassword("123334");
        loginPage.clickLoginButton();

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
