package demo.core;

import demo.fw.ContactHelper;
import demo.fw.UserHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ApplicationManager {

    public WebDriver driver;
    UserHelper userHelper;
    ContactHelper contactHelper;

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    private final String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        try {
            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("chrome_headless")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                driver = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else if (browser.equalsIgnoreCase("safari")) {
                driver = new SafariDriver();
            } else {
                throw new IllegalArgumentException("❌ Некорректный браузер: " + browser + ". Доступные варианты: chrome, firefox, edge, safari.");
            }

            driver.get("https://demowebshop.tricentis.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            logger.info("Запущен браузер: " + browser);
            userHelper = new UserHelper(driver);
            contactHelper = new ContactHelper(driver);
        } catch (Exception e) {
            logger.error("Ошибка при инициализации драйвера: " + e.getMessage());
            throw e; // Пробрасываем исключение дальше
        }
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер закрыт.");
        }
    }
}
