package demo;

import demo.utils.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddItemToCartTests extends TestBase {

    @BeforeMethod
    public void preconditions() {

        try {
            app.getUserHelper().clickOnLoginLink();
            app.getUserHelper().typeEmail("portishead20891@gmail.com");
            app.getUserHelper().typePassword("Password@81");
            app.getUserHelper().clickIfPresent(By.id("RememberMe"));
            app.getUserHelper().clickOnLoginButton();

            // Ожидание, пока элемент станет видимым
            new WebDriverWait(app.getUserHelper().driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("portishead20891@gmail.com")));

            // Проверка, что пользователь успешно вошел в систему
            Assert.assertTrue(app.getUserHelper().isLogOutButtonPresent(), "You're not logged in to your account");
        } catch (Exception e) {
            logger.error("Error during preconditions: " + e.getMessage());
            Assert.fail("Preconditions failed: " + e.getMessage());
        }
    }
//    @BeforeMethod
//    public void preconditions() {
//        app.getUserHelper().clickOnLoginLink();
//        app.getUserHelper().type(By.name("Email"), "portishead20891@gmail.com");
//        app.getUserHelper().type(By.name("Password"), "Password@81");
//        app.getUserHelper().click(By.id("RememberMe"));
//        app.getUserHelper().clickOnLoginButton();
//        app.getUserHelper().driver.findElements(By.cssSelector("img[alt='Tricentis Demo Web Shop']"));
//        new WebDriverWait(app.getUserHelper().driver, Duration.ofSeconds(5))
//                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("portishead20891@gmail.com")));
//
//        Assert.assertTrue(app.getUserHelper().driver.findElement(By.linkText("portishead20891@gmail.com")).isDisplayed(),
//                "You're logged in to your account");
//    }

    @Test
    public void addItemToCartTest() {
        app.getUserHelper().click(By.xpath("(//ul[@class='top-menu']//a)[2]"));
        //driver.navigate().to("https://demowebshop.tricentis.com/computers");
        app.getUserHelper().click(By.xpath("(//h2[@class='title']//a)[1]"));
        //driver.navigate().to("https://demowebshop.tricentis.com/desktops");
        app.getUserHelper().click(By.xpath("(//input[@class='button-2 product-box-add-to-cart-button'])[2]"));
        //driver.navigate().to("https://demowebshop.tricentis.com/build-your-own-computer");
        app.getUserHelper().click(By.cssSelector("#product_attribute_16_5_4"));
        app.getUserHelper().click(By.cssSelector("#product_attribute_16_6_5"));
        app.getUserHelper().click(By.cssSelector("#product_attribute_16_3_6_19"));
        app.getUserHelper().click(By.cssSelector("#product_attribute_16_4_7_21"));
        app.getUserHelper().click(By.xpath("//input[@id='product_attribute_16_8_8_22']"));
        app.getUserHelper().click(By.xpath("//input[@id='addtocart_16_EnteredQuantity']"));
        app.getUserHelper().click(By.cssSelector("#add-to-cart-button-16"));
        app.getUserHelper().click(By.xpath("//span[contains(text(),'Shopping cart')]"));

        String productName = app.getUserHelper().driver.findElement(By.xpath("//tbody/tr[1]/td[3]/a[1]")).getText();
        System.out.println(productName);
        String productNameInCard = app.getUserHelper().driver.findElement(By.className("product-name")).getText();
        System.out.println(productNameInCard);
        Assert.assertEquals(productName, productNameInCard);
    }

    @Test(dataProvider = "addItemToCartDataProvider", dataProviderClass = DataProviders.class)
    public void addItemToCartDataProviderTest(String category, String subCategory, int productIndex) {

        app.getUserHelper().click(By.xpath("//ul[@class='top-menu']//a[contains(text(),'" + category + "')]"));
        app.getUserHelper().click(By.xpath("//h2[@class='title']//a[contains(text(),'" + subCategory + "')]"));
        app.getUserHelper().click(By.xpath("(//input[@class='button-2 product-box-add-to-cart-button'])[" + productIndex + "]"));
        app.getUserHelper().clickIfPresent(By.cssSelector("#product_attribute_16_5_4"));
        app.getUserHelper().clickIfPresent(By.cssSelector("#product_attribute_16_6_5"));
        app.getUserHelper().clickIfPresent(By.cssSelector("#product_attribute_16_3_6_19"));
        app.getUserHelper().clickIfPresent(By.cssSelector("#product_attribute_16_4_7_21"));
        app.getUserHelper().clickIfPresent(By.xpath("//input[@id='product_attribute_16_8_8_22']"));
        app.getUserHelper().click(By.cssSelector("#add-to-cart-button-16"));
        app.getUserHelper().click(By.xpath("//span[contains(text(),'Shopping cart')]"));

        String productName = app.getUserHelper().getText(By.xpath("//tbody/tr[1]/td[3]/a[1]"));
        String productNameInCart = app.getUserHelper().getText(By.className("product-name"));

        System.out.println("Selected product: " + productName);
        System.out.println("Item in your cart: " + productNameInCart);

        Assert.assertEquals(productName, productNameInCart);
    }

    @AfterMethod//(enabled = false)
    public void Finish() {
            if (app.getUserHelper().driver != null) {
                app.getUserHelper().driver.quit();
                app.getUserHelper().driver = null;
            }
        //app.getUserHelper().driver.quit();
//        if (app.getUserHelper().driver != null) {
//            app.getUserHelper().driver.quit();
//        }

    }
}


