package demo.fw;

import demo.core.BaseHelper;
import demo.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    Logger logger = LoggerFactory.getLogger(UserHelper.class);

    public UserHelper clickOnLoginButton() {
        click(By.cssSelector("input.button-1.login-button"));
        return this;
    }

    public UserHelper clickOnLoginLink() {
       click(By.xpath("//a[contains(text(),'Log in')]"));
        return this;
    }
    public UserHelper clickOnLogoutButton() {
        click(By.xpath("//a[contains(text(),'Log out')]"));
        return this;
    }
    public UserHelper typeEmail(String email) {
        logger.info("Typing email: " + email);
        type(By.xpath("//input[@id='Email']"), email);
        return this;
    }
    public UserHelper typePassword(String password) {
        logger.info("Typing password: " + password);
        type(By.xpath("//input[@id='Password']"), password);
        return this;
    }


    public void fillInLoginForm(User user) {
        typeEmail((user.getEmail()));
        typePassword((user.getPassword()));
//        try {
//            // Заполняем поле email
//            if (user.getEmail() != null) {
//                typeEmail(user.getEmail());
//            } else {
//                // Если email не введен, ждём 10 секунд и переходим к паролю
//                Thread.sleep(10000);  // 10 секунд
//                if (user.getEmail() == null) {
//                    driver.findElement(By.xpath("//div[@class='message-error']//div[1]"));
//                }
//            }
//
//            // Заполняем поле password
//            if (user.getPassword() != null) {
//                typePassword(user.getPassword());
//            } else {
//                // Если пароль не введен, ждём 10 секунд и показываем ошибку
//                Thread.sleep(10000);  // 10 секунд
//                if (user.getPassword() == null) {
//                    driver.findElement(By.xpath("//div[@class='message-error']//div[1]"));
//                }
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    public UserHelper checkLogin() {
        Assert.assertTrue(isElementPresent(By.xpath("//a[contains(text(),'Log out')]")));
        return this;

    }
    public boolean isLogOutButtonPresent() {
        return isElementPresent(By.xpath("//a[contains(text(),'Log out')]"));
    }
    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }
    public void clickIfPresent(By locator) {
        if (isElementPresent(locator)) {
            click(locator);
        }
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public void initDriver() {
        driver = new ChromeDriver();
    }
}


