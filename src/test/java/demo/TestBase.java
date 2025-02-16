package demo;

import demo.core.ApplicationManager;
import org.openqa.selenium.NoSuchSessionException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;


public class TestBase {
    protected final ApplicationManager app = new ApplicationManager(System.getProperty("browser", "chrome"));

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUpSuite() {
        // System.out.println("@BeforeSuite");
        //logger.info("@BeforeSuite in TestBase");
        //app.init();
    }

    @BeforeMethod
    public void setUp(Method method) {
        logger.info("Test is started: [" + method.getName() + "]");
        app.init();
    }

    @AfterMethod
    public void tearDown(Method method, ITestResult result) {
        app.stop();
        if (result.isSuccess()) {
            logger.info("Test is PASSED: [" + method.getName() + "]");
        } else {
            logger.error("Test is FAILED: [" + method.getName() + "], Screenshot: [" + app.getUserHelper().takeScreenshot() + "]");
        }
    }
    @AfterSuite(enabled = true)
    public void tearDownSuite() {
        //logger.info("@AfterSuite in TestBase");
        //app.stop();
    }
}
