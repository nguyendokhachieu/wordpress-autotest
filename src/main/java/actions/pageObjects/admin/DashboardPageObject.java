package actions.pageObjects.admin;

import actions.commons.BasePage;
import interfaces.pageUIs.admin.DashboardPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class DashboardPageObject extends BasePage {
    private WebDriver driver;

    public DashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Verify if dashboard title \"Dashboard\" is displayed")
    public boolean isDashboardTitleDisplayed() {
        waitForElementVisible(driver, DashboardPageUI.DASHDOARD_TITLE);
        return isElementDisplayed(driver, DashboardPageUI.DASHDOARD_TITLE);
    }

}
