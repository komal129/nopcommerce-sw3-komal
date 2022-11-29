package electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class ElectronicsTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp(){

        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully(){

        mouseHoverToElement(By.xpath("//div/ul[@class='top-menu notmobile']/li[2]/a[@href='/electronics']"));
        mouseHoverToElementAndClick(By.xpath("//body[1]/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));

        // Verify the text
        verifyText(By.xpath("//div[@class='page-title']/h1[text() = 'Cell phones']"), "Cell phones");
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException{

        mouseHoverToElement(By.xpath("//div/ul[@class='top-menu notmobile']/li[2]/a[@href='/electronics']"));
        mouseHoverToElementAndClick(By.xpath("//body[1]/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));

        // Verify the text
        verifyText(By.xpath("//div[@class='page-title']/h1[text() = 'Cell phones']"), "Cell phones");

        // Click on List tab
        clickOnElement(By.xpath("//div/a[text()='List']"));

        Thread.sleep(1000);
        //Click on product name “Nokia Lumia 1020” link
        clickOnElement(By.xpath("//h2/a[text()='Nokia Lumia 1020']"));

        //Verify the text “Nokia Lumia 1020”
        verifyText(By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]"), "Nokia Lumia 1020");

        //Verify the price “$349.00”
        verifyText(By.xpath("//span[@id='price-value-20']"), "$349.00");

        //Change quantity to 2
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#product_enteredQuantity_20")).clear();
        sendTextToElement(By.cssSelector("#product_enteredQuantity_20"), "2");

        // Click on “ADD TO CART” tab
        Thread.sleep(1000);
        clickOnElement(By.cssSelector("#add-to-cart-button-20"));

        // Verify the Message "The product has been added to your shopping cart" on Top green Bar
        verifyText(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"), "The product has been added to your shopping cart");

        //After that close the bar clicking on the cross button
        clickOnElement(By.xpath("//body/div[@id='bar-notification']/div[1]/span[1]"));

        // Then MouseHover on "Shopping cart" and Click on "GO TO CART" button
        mouseHoverToElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        clickOnElement(By.xpath("//button[contains(text(),'Go to cart')]"));

        // Verify the message "Shopping cart"
        verifyText(By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart");

        // Verify the quantity is 2
        Thread.sleep(2000);
        String expectedQuantity = "2";
        WebElement wb = driver.findElement(By.xpath("//td[@class='quantity']/child::input"));
        String actualQuantity = wb.getAttribute("value");
        //System.out.println(quantity);
        Assert.assertEquals(expectedQuantity, actualQuantity);

        //verifyText(By.xpath("//td[@class='quantity']/child::input"),"2");

        // Verify the Total $698.00
        verifyText(By.xpath("//tbody/tr[1]/td[6]/span[1]"), "$698.00");

        // click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

        // Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

        // Verify the Text “Welcome, Please Sign In!”
        verifyText(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), "Welcome, Please Sign In!");

        // Click on “REGISTER” tab
        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));

        // Verify the text “Register”
        verifyText(By.xpath("//h1[contains(text(),'Register')]"), "Register");

        // Fill the mandatory fields
        sendTextToElement(By.id("FirstName"), "Shanee");
        sendTextToElement(By.id("LastName"), "Jhones");
        sendTextToElement(By.id("Email"), "shanee9jhones@example.com");
        sendTextToElement(By.id("Password"), "shane12345");
        sendTextToElement(By.id("ConfirmPassword"), "shane12345");

        //Click on “REGISTER” Button
        clickOnElement(By.id("register-button"));

        //Verify the message “Your registration completed”
        verifyText(By.xpath("//div[contains(text(),'Your registration completed')]"), "Your registration completed");


        // Click on “CONTINUE” tab
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
        // Verify the text “Shopping cart”
        verifyText(By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart");

        // Click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        // Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

        // Fill the Mandatory fields
        Thread.sleep(2000);
        selectByValueFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "1");
        selectByValueFromDropDown(By.id("BillingNewAddress_StateProvinceId"), "49");
        sendTextToElement(By.id("BillingNewAddress_City"), "New York");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "Proctor close");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "380019");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "987654323");


        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[2]/div[1]/button[4]"));

        // Click on Radio Button “2nd Day Air ($0.00)”
        clickOnElement(By.xpath("//input[@id='shippingoption_2']"));

        // Click on "CONTINUE"
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[3]/div[2]/form[1]/div[2]/button[1]"));

        //Select Radio Button “Credit Card”
        Thread.sleep(3000);
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[4]/div[2]/div[1]/button[1]"));

        //Select “Visa” From Select credit card dropdown
        selectByValueFromDropDown(By.id("CreditCardType"), "visa");

        Thread.sleep(3000);
        sendTextToElement(By.id("CardholderName"), "Shanee Jones");
        sendTextToElement(By.id("CardNumber"), "4222222222222");
        selectByValueFromDropDown(By.id("ExpireMonth"), "9");
        selectByValueFromDropDown(By.id("ExpireYear"), "2023");
        sendTextToElement(By.id("CardCode"), "234");

        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[5]/div[2]/div[1]/button[1]"));

        verifyText(By.xpath("//span[contains(text(),'Credit Card')]"), "Credit Card");

        verifyText(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[6]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/ul[1]/li[1]/span[2]"), "2nd Day Air");

        verifyText(By.xpath("//tbody/tr[4]/td[2]/span[1]/strong[1]"), "$698.00");

        // Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        // Verify the Text “Thank You”
        verifyText(By.xpath("//h1[contains(text(),'Thank you')]"), "Thank you");

        // Verify the message “Your order has been successfully processed!”
        verifyText(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"), "Your order has been successfully processed!");

        // Click on "CONTINUE"
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        //Verify the text “Welcome to our store”
        verifyText(By.xpath("//h2[contains(text(),'Welcome to our store')]"), "Welcome to our store");

        //Click on “Logout” link
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));

        //Verify the URL is “https://demo.nopcommerce.com/”
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("Invalid URL", baseUrl,currentUrl);
    }



    @After
    public void teardown(){
        // closeBrowser();
    }
}
