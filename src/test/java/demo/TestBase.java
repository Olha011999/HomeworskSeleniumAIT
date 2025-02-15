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
        app.init();
    }

    @BeforeMethod
    public void setUp(Method method) {
        if (app.getUserHelper().driver == null) {
            app.getUserHelper().initDriver(); // Инициализация драйвера, если он еще не установлен
        }
        //System.out.println("@BeforeMethod");
        logger.info("Test is started: [" + method.getName() + "]");
        //app.init();
    }

    @AfterMethod(enabled = true)
    public void Finish(Method method, ITestResult result) {
        if (result.isSuccess()) {
            logger.info("Test is passed: [" + method.getName() + "]");
        } else {
            logger.error("Test is failed: [" + method.getName() + "], Screenshot: [" + app.getContactHelper().takeScreenshot() + "]");
        }

        // Проверка на наличие активной сессии перед завершением
        try {
            if (app.getUserHelper().driver != null) {
                // Здесь можно добавить дополнительные действия, если необходимо
            }
        } catch (NoSuchSessionException e) {
            logger.error("Сессия больше не существует: " + e.getMessage());
        }
    }


    @AfterSuite(enabled = true)
    public void tearDownSuite() {
        //logger.info("@AfterSuite in TestBase");
        app.stop();
    }
}
