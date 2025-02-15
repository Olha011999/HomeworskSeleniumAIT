package demo;

import demo.data.UserData;
import demo.utils.DataProviders;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preconditions() {
        //logger.info("@BeforeMethod in Login tests");
        if (app.getUserHelper().isLogOutButtonPresent()) {
            logger.info("User already in. Sing out...");
            app.getUserHelper().clickOnLogoutButton();
        } else {
            logger.info("Login link is present. No need to sing out");
        }
    }

    @Test
    public void loginExistedUserPositiveTest() {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().type(By.name("Email"), "portishead20@gmail.com");
        app.getUserHelper().type(By.name("Password"), "Password@81");
        app.getUserHelper().click(By.id("RememberMe"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertTrue(app.driver.findElement(By.cssSelector("img[alt='Tricentis Demo Web Shop']")).isDisplayed());
        Assert.assertTrue(app.driver.findElement(By.xpath("//*[contains(text(),'portishead20@gmail.com')]")).isDisplayed());
    }


    @Test(dataProvider = "loginDataProvider", dataProviderClass = DataProviders.class)
    public void loginExistedUserDataProviderPositiveTest(String email, String password) {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().typeEmail(email);
        app.getUserHelper().typePassword(password);
        app.getUserHelper().click(By.id("RememberMe"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertTrue(app.driver.findElement(By.cssSelector("img[alt='Tricentis Demo Web Shop']")).isDisplayed());
        Assert.assertTrue(app.driver.findElement(By.xpath("//*[contains(text(),'portishead20@gmail.com')]")).isDisplayed());
        app.getUserHelper().clickOnLogoutButton();
    }


    @Test
    public void loginExistedDataUserPositiveTest() {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().type(By.name("Email"), UserData.VALID_EMAIL);
        app.getUserHelper().type(By.name("Password"), UserData.VALID_PASSWORD);
        app.getUserHelper().click(By.id("RememberMe"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertTrue(app.driver.findElement(By.cssSelector("img[alt='Tricentis Demo Web Shop']")).isDisplayed());
        Assert.assertTrue(app.driver.findElement(By.xpath("//*[contains(text(),'portishead20@gmail.com')]")).isDisplayed());
    }


    @AfterMethod
    public void postconditions() {
        // app.getUserHelper().clickOnLogoutButton();
        // logger.info("@AfterMethod in Login tests");
    }

}