package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp(){

        openBrowser(baseUrl);
    }

    public void selectMenu(String menu){
        String mainmenu = menu.toLowerCase();
        String expression = "//a[@href ='/" + mainmenu + "']";
        driver.findElement(By.xpath(expression)).click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("Navigated successfully", currentUrl, baseUrl + menu);

    }

    @Test
    public void verifyPageNavigation(){

       mouseHoverToElement(By.xpath("//a[@href='/computers']"));
       selectMenu("computers");
    }



    @After
    public void teardown(){
       // closeBrowser();
    }


}
