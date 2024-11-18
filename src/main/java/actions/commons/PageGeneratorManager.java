package actions.commons;

import actions.pageObjects.admin.DashboardPageObject;
import actions.pageObjects.admin.LoginPageObject;
import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static DashboardPageObject getDashboardPage(WebDriver driver) {
        return new DashboardPageObject(driver);
    }
}
