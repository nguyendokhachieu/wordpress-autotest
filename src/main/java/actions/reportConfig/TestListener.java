package actions.reportConfig;

import actions.commons.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.io.IOException;

public class TestListener extends BaseTest implements ITestListener {

    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
    }

    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        System.out.println("Test failed: " + result.getName());
        // Take a screenshot on test failure
        takeScreenshot(driver, result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    public void onFinish(ITestContext context) {
        System.out.println("Test finished: " + context.getName());
    }

    public void takeScreenshot(WebDriver driver, String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(srcFile, new File("screenshots/" + testName + ".png"));
            System.out.println("Screenshot taken for failed test: " + testName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
