package demo;

import demo.data.UserData;
import demo.utils.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddItemToCartTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        // Проверка, есть ли кнопка выхода. Если кнопка существует, значит, пользователь уже авторизован.
        if (app.getUserHelper().isLogOutButtonPresent()) {
            logger.info("User already logged in. Signing out...");
            app.getUserHelper().clickOnLogoutButton(); // Выход из системы
        } else {
            logger.info("LOGIN link is present. No need to Sign Out");
        }

        // Выполнение входа в систему
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().type(By.name("Email"), UserData.VALID_EMAIL);
        app.getUserHelper().type(By.name("Password"), UserData.VALID_PASSWORD);
        app.getUserHelper().click(By.id("RememberMe"));
        app.getUserHelper().clickOnLoginButton();

        // Ожидание, что пользователь будет перенаправлен на главную страницу и элемент будет виден
        new WebDriverWait(app.getUserHelper().driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText(UserData.VALID_EMAIL)));

        // Проверка, успешен ли вход в систему
        Assert.assertTrue(app.getUserHelper().driver.findElement(By.linkText(UserData.VALID_EMAIL)).isDisplayed(),
                "You're logged in to your account with email: " + UserData.VALID_EMAIL);
    }

    @Test
    public void addItemToCartTest() {
        app.getUserHelper().driver.manage().window().maximize();
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
        WebDriverWait wait = new WebDriverWait(app.getUserHelper().driver, Duration.ofSeconds(10));
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#add-to-cart-button-16")));
        addToCartButton.click();

        app.getUserHelper().click(By.xpath("//span[contains(text(),'Shopping cart')]"));

        String productName = app.getUserHelper().driver.findElement(By.xpath("//tbody/tr[1]/td[3]/a[1]")).getText();
        String productNameInCard = app.getUserHelper().driver.findElement(By.className("product-name")).getText();

        Assert.assertEquals(productName, productNameInCard);
        app.getUserHelper().clickOnLogoutButton();
    }

    @Test(dataProvider = "addItemToCartDataProvider", dataProviderClass = DataProviders.class)
    public void addItemToCartDataProviderTest(String category, String subCategory, int productIndex) {
        app.getUserHelper().driver.manage().window().maximize();
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
        WebDriverWait wait = new WebDriverWait(app.getUserHelper().driver, Duration.ofSeconds(10));
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#add-to-cart-button-16")));
        addToCartButton.click();

        app.getUserHelper().click(By.xpath("//span[contains(text(),'Shopping cart')]"));

        String productName = app.getUserHelper().driver.findElement(By.xpath("//tbody/tr[1]/td[3]/a[1]")).getText();
        String productNameInCard = app.getUserHelper().driver.findElement(By.className("product-name")).getText();

        Assert.assertEquals(productName, productNameInCard);
        app.getUserHelper().clickOnLogoutButton();
    }

    @AfterMethod//(enabled = false)
    public void Finish() {
        if (app.getUserHelper().driver != null) {
            app.getUserHelper().driver.quit();
            app.getUserHelper().driver = null;
        }


    }
}


