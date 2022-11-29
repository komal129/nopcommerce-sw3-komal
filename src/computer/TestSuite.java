package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class TestSuite extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp(){

        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder(){

        mouseHoverToElement(By.xpath("//div/ul[@class='top-menu notmobile']/li[1]/a[@href='/computers']"));
        String add_to_cart_xpath = "//div[./h2 = 'Build your own computer']//button[text() = 'Add to cart']";
        clickOnElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));
        selectByVisibleTextFromDropDown(By.name("products-orderby"), "Name: Z to A");

        List<WebElement> list = driver.findElements(By.xpath("//div[@class='details']"));
        int size = list.size();

        String[] sorted = new String[size]; // Sorted array to store products

        // iterate products and store in array
        for (int i = 0; i < size; i++){
            String expr = "//div[@class='item-grid']/div["+(i+1)+"]/div/div[@class='details']/h2/a";
            sorted[i] = driver.findElement(By.xpath(expr)).getText();
        }

        // Check the array values in ascending order
        Assert.assertEquals("Build your own computer", sorted[0]);
        Assert.assertEquals("Digital Storm VANQUISH 3 Custom Performance PC", sorted[1]);
        Assert.assertEquals("Lenovo IdeaCentre 600 All-in-One PC", sorted[2]);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException{

        // Click on Computers tab
        mouseHoverToElement(By.xpath("//div/ul[@class='top-menu notmobile']/li[1]/a[@href='/computers']"));

        // Click on Desktop in submenu
        clickOnElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));

        // Select sort by A to Z
        selectByVisibleTextFromDropDown(By.name("products-orderby"), "Name: A to Z");

        Thread.sleep(Long.parseLong("5000"));

        // Click on Add to cart button
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"));

        //This is from requirement
        String expectedText = "Build your own computer";

        // Get text
        String actualText = getTextFromElement(By.xpath("//h1[contains(text(),'Build your own computer')]"));

        // Verify the text
        Assert.assertEquals(actualText, expectedText);

        // Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectByVisibleTextFromDropDown(By.name("product_attribute_1"), "2.2 GHz Intel Pentium Dual-Core E2200");

        // Select "8GB [+$60.00]" using Select class
        selectByVisibleTextFromDropDown(By.name("product_attribute_2"), "8GB [+$60.00]");

        //Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));
      //  driver.findElement(By.xpath("//input[@id='product_attribute_3_7']")).click();

        // Select OS radio "Vista Premium [+$60.00]"
        //driver.findElement(By.xpath("//input[@id='product_attribute_4_9']")).click();
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));

        // Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander[+$5.00]"
        driver.findElement(By.xpath("//input[@id='product_attribute_5_10']")).findElement(By.xpath("//input[@id='product_attribute_5_12']")).click();

        // Verify the price "$1,475.00"
        String expectedPrice = "$1,475.00";

        // Get text
        String actualPrice = getTextFromElement(By.xpath("//span[text() = '$1,475.00']"));

        // Verify the text
        Assert.assertEquals(expectedPrice, actualPrice );

        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));

        // Verify the message
        String expectedMessage  = "The product has been added to your shopping cart";

        String actualMessage = getTextFromElement(By.xpath("//body[1]/div[5]/div[1]/p[1]"));

        Assert.assertEquals(expectedMessage, actualMessage);

        clickOnElement(By.xpath("//body[1]/div[5]/div[1]/span[1]"));

        //MouseHover on "Shopping cart" and Click on "GO TO CART" button
        mouseHoverToElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        clickOnElement(By.xpath("//button[contains(text(),'Go to cart')]"));

        // Verify the message "Shopping cart"

        String expectedCartMsg = "Shopping cart";
        String actualCartMsg = getTextFromElement(By.xpath("//div[@class='page-title']/h1[contains(text(),'Shopping cart')]"));
        Assert.assertEquals("Shopping cart is not displayed",expectedCartMsg, actualCartMsg);

        //Change the Qty to "2" and Click on "Update shopping cart"
        Thread.sleep(1000);
        WebElement Quantity = driver.findElement(By.xpath("//td[@class='quantity']/child::input"));
        Quantity.clear();
        Quantity.sendKeys("2");
        Thread.sleep(1000);

        // Update the shopping cart
        clickOnElement(By.xpath("//button[@id='updatecart']"));

        //Verify the Total"$2,950.00"
        verifyText(By.xpath("//span[@class='product-subtotal'][text()='$2,950.00']"), "$2,950.00");

        // click on checkbox
        clickOnElement(By.id("termsofservice"));

        // Click on Checkout button
        clickOnElement(By.xpath("//button[@id='checkout']"));

        // Verify the message
        verifyText(By.xpath("//h1[text()='Welcome, Please Sign In!']"),"Welcome, Please Sign In!" );

        // Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[text()= 'Checkout as Guest']"));

        // Fill the all mandatory field
        Thread.sleep(2000);
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "Joe"); //Enter first name
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Jhones"); //Enter Last name
        sendTextToElement(By.id("BillingNewAddress_Email"), "joejhones@example.com"); //Enter Email
        selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"), "233");
        sendTextToElement(By.id("BillingNewAddress_City"), "Rugby");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "13 Proctor Close");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "380019");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "9876543215");

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//div[1]/button[4]"));

        //Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.id("shippingoption_1"));

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//form[1]/div[2]/button[1]"));

        //Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));

        clickOnElement(By.xpath("//ol[1]/li[4]/div[2]/div[1]/button[1]"));

        //Select “Master card” From Select credit card dropdown
        selectByValueFromDropDown(By.id("CreditCardType"), "MasterCard");

        //2.28 Fill all the details
        sendTextToElement(By.id("CardholderName"), "Joe Jhones");
        sendTextToElement(By.id("CardNumber"), "99922 22583 46663 6236");

        selectByValueFromDropDown(By.id("ExpireMonth"), "8");
        selectByValueFromDropDown(By.id("ExpireYear"), "2028");

        sendTextToElement(By.id("CardCode"), "4008");

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//ol[1]/li[5]/div[2]/div[1]/button[1]"));

        //Verify “Payment Method” is “Credit Card”
        verifyText(By.xpath("//span[contains(text(),'Credit Card')]"), "Credit Card");

        // Verify Text
        verifyText(By.xpath("//div[1]/div[2]/div[2]/ul[1]/li[1]/span[2]"), "Next Day Air");

        //Verify Text
        verifyText(By.xpath("//tbody/tr[4]/td[2]/span[1]/strong[1]"), "$2,950.00");

        // Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        //Verify the Text “Thank You”
        verifyText(By.xpath("//h1[contains(text(),'Thank you')]"), "Thank you");

        //Verify the message “Your order has been successfully processed!”
        verifyText(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"), "Your order has been successfully processed!");

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        //Verify the text “Welcome to our store”
        verifyText(By.xpath("//h2[contains(text(),'Welcome to our store')]"), "Welcome to our store");

    }
    @After
    public void teardown(){
        // closeBrowser();
    }
}
