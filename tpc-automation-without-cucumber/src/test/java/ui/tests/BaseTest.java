package ui.tests;

import helpers.PageObjectManager;
import helpers.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ui.pages.AuthenticationPage;
import ui.pages.HeaderMenuPage;
import ui.pages.MyAccountPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;
    PageObjectManager pageObjectManager;

    @BeforeMethod(groups = "groupExample")
    public void setup() {
        driver = WebDriverFactory.createWebDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        pageObjectManager = new PageObjectManager(driver, wait);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) throws IOException {
        System.out.println("after method ran even if method not annotated with group");
        if (result.getStatus() == ITestResult.FAILURE) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File savedScreenshot = new File("target/screenshots/" + result.getTestClass().getRealClass().getSimpleName() + "/" + result.getMethod().getMethodName() + ".jpg");
            FileUtils.copyFile(screenshot, savedScreenshot);
        } else if (result.getStatus() == ITestResult.SKIP) {
            // do something, preferably take screenshot
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
