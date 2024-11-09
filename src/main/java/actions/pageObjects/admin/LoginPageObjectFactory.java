package actions.pageObjects.admin;

import actions.commons.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjectFactory extends BasePageFactory {
    private WebDriver driver;

    @CacheLookup
    @FindBy(how = How.XPATH, using = "//input[@id='wp-submit']")
    private WebElement loginButton;

    @CacheLookup
    @FindBy(xpath = "//input[@id='user_login']")
    private WebElement usernameInput;

    public LoginPageObjectFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void refresh() {
        refreshCurrentPage(driver);
    }

    public void clickLoginButton() {
        waitForElementClickable(driver, loginButton);
        clickToElement(driver, loginButton);
    }

    public void inputToUsername(String username) {
        waitForElementVisible(driver, usernameInput);
        sendKeyToElement(driver, usernameInput, username);
    }

}
