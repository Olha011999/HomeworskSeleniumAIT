package demo;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class HomeworkTests_13 extends TestBase {

    @Test
    public void FirstHomework() {
        app.getUserHelper().driver.findElement(By.xpath(" //a[contains(text(),'Register')]"));
        app.getUserHelper().driver.findElement(By.xpath("(//li[@class='inactive']//a)[1]"));
        app.getUserHelper().driver.findElement(By.xpath("//body/div[4]/div[1]/div[1]/div[3]/form[1]/input[2]"));
        app.getUserHelper().driver.findElement(By.xpath("//p[contains(text(),'Welcome to the new Tricentis store!')]"));
        app.getUserHelper().driver.findElement(By.xpath("//a[contains(text(),'Tricentis')]"));
        app.getContactHelper().navigatePage2("https://demowebshop.tricentis.com/gift-cards");
        app.getUserHelper().driver.findElement(By.xpath("//a[contains(text(),'$5 Virtual Gift Card')]"));
        app.getUserHelper().driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
        app.getUserHelper().driver.findElement(By.xpath("//a[contains(text(),'Tricentis')]"));
        app.getUserHelper().driver.findElement(By.xpath("//select[@id='products-viewmode']"));
        app.getUserHelper().driver.findElement(By.xpath("//a[contains(text(),'My account')]"));
        app.getUserHelper().driver.findElement(By.xpath("//span[contains(text(),'Wishlist')]"));
        app.getUserHelper().driver.navigate().to("https://demowebshop.tricentis.com/computers");
        app.getUserHelper().driver.findElement(By.xpath("(//a[@title='Show products in category Desktops'])[1]"));
    }

}
