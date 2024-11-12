package actions.reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import actions.commons.GlobalConstants;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstants.RELATIVE_PROJECT_PATH + "/extentV5/ExtentReport.html");
        reporter.config().setReportName("Wordpress HTML Report");
        reporter.config().setDocumentTitle("Wordpress HTML Report");
        reporter.config().setTimelineEnabled(true);
        reporter.config().setEncoding("utf-8");
        reporter.config().setTheme(Theme.DARK);

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Project", "Wordpress");
        extentReports.setSystemInfo("Team", "Basus VN");
        extentReports.setSystemInfo("JDK version", GlobalConstants.JAVA_VERSION);
        return extentReports;
    }
}