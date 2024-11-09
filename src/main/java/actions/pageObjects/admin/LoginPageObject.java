package actions.pageObjects.admin;

import actions.commons.BasePage;
import interfaces.pageUIs.admin.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void refresh() {
        refreshCurrentPage(driver);
    }

    public void clickLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }

    public void inputToUsername(String username) {
        waitForElementVisible(driver, LoginPageUI.USERNAME_INPUT);
        sendKeyToElement(driver, LoginPageUI.USERNAME_INPUT, username);
    }

    public void inputToPassword(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_INPUT);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_INPUT, password);
    }

    public String getErrorMessageFromValidationPrompt(String fromField) {
        sleepInSeconds(1);
        if (fromField.equals("username")) {
            return getElementValidationMessage(driver, LoginPageUI.USERNAME_INPUT);
        } else if (fromField.equals("password")) {
            return getElementValidationMessage(driver, LoginPageUI.PASSWORD_INPUT);
        }
        return "";
    }

    public String getErrorMessageFromBox() {
        waitForElementVisible(driver, LoginPageUI.ERROR_BOX_MESSAGE);
        System.out.println("ERROR MSG: " + getTextFromElement(driver, LoginPageUI.ERROR_BOX_MESSAGE));
        return getTextFromElement(driver, LoginPageUI.ERROR_BOX_MESSAGE);
    }
}
