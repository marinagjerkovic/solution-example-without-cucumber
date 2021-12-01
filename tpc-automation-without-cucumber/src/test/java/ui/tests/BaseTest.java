package ui.tests;

import helpers.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import ui.pages.AuthenticationPage;
import ui.pages.HeaderMenuPage;
import ui.pages.MyAccountPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;

    AuthenticationPage authenticationPage;
    MyAccountPage myAccountPage;
    HeaderMenuPage headerMenuPage;

    @BeforeTest(alwaysRun = true)
    public void setupDriver() {
        WebDriverFactory.setupDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = WebDriverFactory.createWebDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File savedScreenshot = new File("target/screenshots/" + result.getTestClass().getRealClass().getSimpleName() + "/" + result.getMethod().getMethodName() + ".jpg");
            FileUtils.copyFile(screenshot, savedScreenshot);
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
