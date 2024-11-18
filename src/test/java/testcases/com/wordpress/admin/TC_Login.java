package testcases.com.wordpress.admin;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.admin.DashboardPageObject;
import actions.pageObjects.admin.LoginPageObject;
import interfaces.messages.admin.LoginPageMessage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Epic("Login")
public class TC_Login extends BaseTest {
    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
    }

    @BeforeMethod
    public void beforeMethod() {
        openUrl(driver,GlobalConstants.PAGE_URL_LOCAL_HOST);
        loginPage = PageGeneratorManager.getLoginPage(driver);
    }

    @Description("TC_Login_001_LoginWithEmptyUsername")
    @Parameters("browser")
    @Test
    public void TC_Login_001_LoginWithEmptyUsername(String browserName) {
        loginPage.inputToPassword("123111");
        loginPage.clickLoginButton();

        if (browserName.equalsIgnoreCase("firefox")) {
            Assert.assertEquals(loginPage.getErrorMessageFromValidationPrompt("username"), LoginPageMessage.FILL_THIS_FIELD_VI);
        } else {
            Assert.assertEquals(loginPage.getErrorMessageFromValidationPrompt("username"), LoginPageMessage.FILL_THIS_FIELD);
        }
    }

    @Description("TC_Login_002_LoginWithEmptyPassword")
    @Parameters("browser")
    @Test
    public void TC_Login_002_LoginWithEmptyPassword(String browserName) {
        loginPage.inputToUsername("test_username1");
        loginPage.clickLoginButton();

        if (browserName.equalsIgnoreCase("firefox")) {
            Assert.assertEquals(loginPage.getErrorMessageFromValidationPrompt("password"), LoginPageMessage.FILL_THIS_FIELD_VI);
        } else {
            Assert.assertEquals(loginPage.getErrorMessageFromValidationPrompt("password"), LoginPageMessage.FILL_THIS_FIELD);
        }
    }

    @Description("TC_Login_003_LoginWithUserNotExists")
    @Test
    public void TC_Login_003_LoginWithUserNotExists() {
        String username = "not_exist_username";
        loginPage.inputToUsername(username);
        loginPage.inputToPassword("123334");
        loginPage.clickLoginButton();

        Assert.assertEquals(loginPage.getErrorMessageFromBox(), String.format(LoginPageMessage.FM_USERNAME_NOT_EXIST, username));
    }

    @Description("TC_Login_004_LoginWithWrongPassword")
    @Test
    public void TC_Login_004_LoginWithWrongPassword() {
        loginPage.inputToUsername(GlobalConstants.ADMIN_PAGE_USERNAME);
        loginPage.inputToPassword("wrong_password");
        loginPage.clickLoginButton();

        Assert.assertEquals(loginPage.getErrorMessageFromBox(), String.format(LoginPageMessage.FM_WRONG_PASSWORD, GlobalConstants.ADMIN_PAGE_USERNAME));
    }

    @Description("TC_Login_005_LoginSuccess")
    @Test
    public void TC_Login_005_LoginSuccess() {
        loginPage.inputToUsername(GlobalConstants.ADMIN_PAGE_USERNAME);
        loginPage.inputToPassword(GlobalConstants.ADMIN_PAGE_PASSWORD);
        dashboardPage = loginPage.clickLoginButton();
        Assert.assertTrue(dashboardPage.isDashboardTitleDisplayed());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowser();
    }

}
