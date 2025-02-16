package demo.core;

import com.google.common.io.Files;
import demo.fw.ContactHelper;
import demo.fw.UserHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseHelper {
    public WebDriver driver;

    Logger logger = LoggerFactory.getLogger(BaseHelper.class);

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    UserHelper userHelper;
    ContactHelper contactHelper;

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public boolean isElementPresent(By locator) {return driver.findElements(locator).size() > 0;}

    public void type(By locator, String text) {
        logger.info("Typing email" +text);
        //driver.findElement(By.name("Email")).click();
        if (text != null) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);}
    }

//    public void click(By locator) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
//        element.click();
//    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        if(alert == null){
            return false;
        } else {
            driver.switchTo().alert().accept();
            return true;
        }
    }


    public String takeScreenshot() {
        if (driver == null) {
            System.out.println("Driver is null. Cannot take screenshot.");
            return null;
        }

        // Проверяем наличие алерта перед снятием скриншота
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Таймаут на обнаружение алерта
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();  // или alert.dismiss();
            System.out.println("Alert был найден и закрыт.");
        } catch (NoAlertPresentException e) {
            // Нет алерта, продолжаем
        } catch (TimeoutException e) {
            System.out.println("Алерт не появился в течение таймаута, продолжаем снятие скриншота.");
        }

        // Захват скриншота
        File tmp;
        try {
            tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        } catch (Exception e) {
            System.out.println("Ошибка при попытке сделать скриншот: " + e.getMessage());
            return null;
        }

        File screenshot = new File("src/test_screenshots/screen-" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении скриншота: " + e.getMessage());
            return null;
        }

        System.out.println("Скриншот сохранен в: " + screenshot.getAbsolutePath());
        return screenshot.getAbsolutePath();
    }

}
