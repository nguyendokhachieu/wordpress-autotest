package actions.commons;

import actions.pageObjects.admin.DashboardPageObject;
import actions.pageObjects.admin.LoginPageObject;
import actions.utilities.JavaHelper;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Set;

public class InitializeData extends BaseTest {
    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPageObject;

    public static Set<Cookie> cookies;

    @Parameters("browser")
    @BeforeTest
    public void beforeTest(String browserName) {
        driver = getBrowserDriver(browserName);
        openUrl(driver, GlobalConstants.PAGE_URL_LOCAL_HOST);

        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.inputToUsername(GlobalConstants.ADMIN_PAGE_USERNAME);
        loginPage.inputToPassword(GlobalConstants.ADMIN_PAGE_PASSWORD);
        loginPage.clickLoginButton();

        dashboardPageObject = PageGeneratorManager.getDashboardPage(driver);
        verifyTrue(dashboardPageObject.isDashboardTitleDisplayed());
        JavaHelper.sleepInSeconds(2);
        cookies = dashboardPageObject.getCookie(driver);

        closeBrowser();
    }
}
