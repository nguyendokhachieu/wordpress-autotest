package testcases.com.wordpress.admin;

import actions.commons.BaseTest;
import actions.commons.GlobalConstants;
import actions.commons.InitializeData;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.admin.DashboardPageObject;
import actions.pageObjects.admin.LoginPageObject;
import actions.utilities.JavaHelper;
import interfaces.messages.admin.LoginPageMessage;
import io.qameta.allure.Epic;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("Dashboard")
public class TC_Dashboard extends BaseTest {
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
        openUrl(driver, GlobalConstants.PAGE_URL_LOCAL_HOST);
        loginPage = PageGeneratorManager.getLoginPage(driver);

        dashboardPage = PageGeneratorManager.getDashboardPage(driver);
    }

    @Parameters("browser")
    @Test(priority = 1)
    public void TC_Dashboard_001_(String browserName) {

    }
}
