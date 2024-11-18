package actions.pageObjects.admin;

import actions.commons.BasePage;
import actions.utilities.JavaHelper;
import interfaces.pageUIs.admin.LoginPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void refresh() {
        refreshCurrentPage(driver);
    }

    @Step("Click to Login button")
    public void clickLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }

    @Step("Input to Username: {0}")
    public void inputToUsername(String username) {
        waitForElementVisible(driver, LoginPageUI.USERNAME_INPUT);
        sendKeyToElement(driver, LoginPageUI.USERNAME_INPUT, username);
    }

    @Step("Input to Password: {0}")
    public void inputToPassword(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_INPUT);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_INPUT, password);
    }

    @Step("Get error message from validation prompt")
    public String getErrorMessageFromValidationPrompt(String fromField) {
        JavaHelper.sleepInSeconds(1);
        if (fromField.equals("username")) {
            return getElementValidationMessage(driver, LoginPageUI.USERNAME_INPUT);
        } else if (fromField.equals("password")) {
            return getElementValidationMessage(driver, LoginPageUI.PASSWORD_INPUT);
        }
        return "";
    }

    @Step("Get error message from box")
    public String getErrorMessageFromBox() {
        waitForElementVisible(driver, LoginPageUI.ERROR_BOX_MESSAGE);
        return getTextFromElement(driver, LoginPageUI.ERROR_BOX_MESSAGE);
    }
}
