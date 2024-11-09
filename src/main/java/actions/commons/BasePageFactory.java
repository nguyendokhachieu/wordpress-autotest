package actions.commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

// argument accepts only WebElement when using Factory Object
public class BasePageFactory {
    public void openPageURL(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.alertIsPresent()
        );
    }

    public void acceptAlert(WebDriver driver) {
        waitAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitAlertPresence(driver).dismiss();
    }

    public String getTextInAlert(WebDriver driver) {
        return waitAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String keyToSend) {
        waitAlertPresence(driver).sendKeys(keyToSend);
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runningWindow : allWindows) {
            if (!runningWindow.equals(parentID)) {
                driver.switchTo().window(runningWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runningWindow : allWindows) {
            driver.switchTo().window(runningWindow);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runningWindow : allWindows) {
            if (!runningWindow.equals(parentID)) {
                driver.switchTo().window(runningWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public Set<Cookie> getCookie(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
    }

    public void deleteCookie(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }

    public void sleepInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public void clickToElement(WebDriver driver, WebElement element) {
        element.click();
    }

    public void sendKeyToElement(WebDriver driver, WebElement element, String keyToSend) {
        element.clear();
        element.sendKeys(keyToSend);
    }

    public String getTextFromElement(WebDriver driver, WebElement element) {
        return element.getText();
    }

    public void selectItemInDefaultDropdown(WebDriver driver, WebElement element, String visibleText) {
        new Select(element).selectByVisibleText(visibleText);
    }

    public void getFirstSelectedOptionInDefaultDropdown(WebDriver driver, WebElement element) {
        new Select(element).getFirstSelectedOption();
    }

    public boolean isDefaultDropdownMultiple(WebDriver driver, WebElement element) {
        return new Select(element).isMultiple();
    }

    public void waitForElementVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementInvisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
    }


}
