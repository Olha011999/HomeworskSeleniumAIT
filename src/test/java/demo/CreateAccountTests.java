package demo;

import demo.data.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CreateAccountTests extends TestBase {


    @Test
    public void createAccountPositiveTest() {
        app.getUserHelper().click(By.xpath("//a[contains(text(),'Register')]"));
        app.driver.findElement(By.xpath("//label[contains(text(),\'Gender:\')]"));
        if (app.driver.findElement(By.xpath("//label[contains(text(),\'Gender:\')]")) == app.driver.findElement(By.xpath("//label[contains(text(),'Male')]"))) {
            app.getUserHelper().click(By.xpath("//label[contains(text(),'Male')]"));
        } else {
            app.getUserHelper().click(By.xpath("//label[contains(text(),'Female')]"));
        }
        app.getUserHelper().click(By.name("FirstName"));
        app.driver.findElement(By.name("FirstName")).sendKeys("Tommmy");
        app.getUserHelper().click(By.name("LastName"));
        app.driver.findElement(By.name("LastName")).sendKeys("Shallommmy");
        app.getUserHelper().click(By.name("Email"));
        app.driver.findElement(By.name("Email")).sendKeys(UserData.RANDOM_EMAIL);
        app.getUserHelper().click(By.name("Password"));
        app.driver.findElement(By.name("Password")).sendKeys(UserData.VALID_PASSWORD);
        app.getUserHelper().click(By.name("ConfirmPassword"));
        app.driver.findElement(By.name("ConfirmPassword")).sendKeys(UserData.VALID_PASSWORD);
        app.getUserHelper().click(By.cssSelector(" #register-button"));
       app.driver.findElement(By.linkText(UserData.RANDOM_EMAIL));
        Assert.assertTrue(app.driver.findElement(By.linkText(UserData.RANDOM_EMAIL)).isDisplayed(), "Your registration completed");
        app.getUserHelper().clickOnLogoutButton();
    }
//"portishead208961"+System.currentTimeMillis()+"@gmail.com"

    @Test(priority = 2)
    public  void CreateAccountNegativeTest(){
        app.getUserHelper().click(By.xpath("//a[contains(text(),'Register')]"));
        app.driver.findElement(By.xpath("//label[contains(text(),\'Gender:\')]"));
        if (app.driver.findElement(By.xpath("//label[contains(text(),\'Gender:\')]")) == app.driver.findElement(By.xpath("//label[contains(text(),'Male')]"))) {
            app.getUserHelper().click(By.xpath("//label[contains(text(),'Male')]"));
        } else {
            app.getUserHelper().click(By.xpath("//label[contains(text(),'Female')]"));
        }
        app.getUserHelper().click(By.name("FirstName"));
        app.driver.findElement(By.name("FirstName")).sendKeys("Tommmy");
        app.getUserHelper().click(By.name("LastName"));
        app.driver.findElement(By.name("LastName")).sendKeys("Shallommmy");
        app.getUserHelper().click(By.name("Email"));
        app.driver.findElement(By.name("Email")).sendKeys(UserData.VALID_EMAIL);
        app.getUserHelper().click(By.name("Password"));
        app.driver.findElement(By.name("Password")).sendKeys(UserData.VALID_PASSWORD);
        app.getUserHelper().click(By.name("ConfirmPassword"));
        app.driver.findElement(By.name("ConfirmPassword")).sendKeys(UserData.VALID_PASSWORD);
        app.getUserHelper().click(By.cssSelector(" #register-button"));
        WebElement errorMessage = app.driver.findElement(By.xpath("//li[contains(text(),'The specified email already exists')]"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }

}
