package demo.fw;


import demo.core.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactHelper extends BaseHelper {
    Logger logger = LoggerFactory.getLogger(ContactHelper.class);

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void navigatePage2(String url) {
        logger.info("Typing url" + url);
        driver.navigate().to(url);
    }

}
