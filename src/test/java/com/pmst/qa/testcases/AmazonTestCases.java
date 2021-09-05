package com.pmst.qa.testcases;

import com.pmst.qa.base.TestBase;
import com.pmst.qa.pages.CreateUserPage;
import com.pmst.qa.pages.SearhSubPage;
import com.pmst.qa.util.TwilioUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonTestCases extends TestBase {

    private CreateUserPage createUserPage;
    private SearhSubPage searhSubPage;


    AmazonTestCases(){
        super();
        initialization();
    }

    @BeforeClass
    public void loadAmazonPage(){
       // driver.get("https://www.amazon.in");

    }

  // @Test
    public  void createAccount() throws InterruptedException {
        Thread.sleep(2000);


        Actions action= new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@id='nav-link-accountList-nav-line-1']"))).perform();
        //action.perform();
        //Thread.wait(1000);


        WebElement ele = driver.findElement(By.xpath("//*[@id='nav-flyout-ya-signin']/a/span"));
        ele.click();

        //driver.findElement(By.id("ap_customer_name")).sendKeys("NaveenTestOTP");
        driver.findElement(By.xpath("//*[@id='createAccountSubmit']")).click();

        Thread.sleep(2000);

        //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


        //Creating Create User page object
        createUserPage = new CreateUserPage();

        //driver.findElement(By.xpath("//*[@id=\"ap_customer_name\"]")).sendKeys("PickMySolarT1");
//        driver.findElement(By.xpath("//ul[@role='application']//li/a[contains(text(),'United States +1')]")).click();
//        driver.findElement(By.id("ap_phone_number")).sendKeys("3343734019");
//        driver.findElement(By.id("ap_password")).sendKeys("TestAutomation@123");
//        driver.findElement(By.id("continue")).click();


        createUserPage.setCustomerName(prop.getProperty("customername"));
        createUserPage.setPhoneNumber(prop.getProperty("username"));
        createUserPage.setPassword(prop.getProperty("password"));
        createUserPage.getCountryCodeSpan().click();
        //
        WebElement dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.partialLinkText("United States"))));
        driver.findElement(By.partialLinkText("United States")).click();
        //adding a wait to popup to be closed
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        createUserPage.enterCreateUserBtn();

        //get otp from twilio
        //String otp = TwilioUtil.getOTP();














       // driver.findElement(By.id("auth-pv-enter-code")).sendKeys(OTPNumber);
    //driver
    }


    @Test
    public void login(){
        //loading Home page login
        super.reloadHomePage();
        Actions action= new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@id='nav-link-accountList-nav-line-1']"))).perform();
        WebElement ele = driver.findElement(By.xpath("//*[@id='nav-flyout-ya-signin']/a/span"));
        ele.click();
        driver.findElement(By.xpath("//*[@id='ap_email']")).sendKeys(prop.getProperty("username"));
        driver.findElement(By.id("continue")).click();

        WebElement dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ap_password"))));
        driver.findElement(By.id("ap_password")).sendKeys(prop.getProperty("password"));
        driver.findElement(By.id("signInSubmit")).click();

        //String otp = TwilioUtil.getOTP();
        //System.out.println(otp);

        searhSubPage = new SearhSubPage();
        searhSubPage.enterSearchTxt("fans");
        searhSubPage.searchPage();


        List<WebElement>  noResultSpan = driver.findElements(By.xpath("//span[contains(text(),'No Results for')]"));
        Assert.assertTrue(noResultSpan.size() == 0);



        List<WebElement>  dataSearchResList= driver.findElements(By.xpath("//div[@data-index]"));

        dataSearchResList.get(1).click();

        //bestSellers.get(1).click();
       driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

       driver.findElement(By.id("add-to-cart-button")).click();






        ////*[@id="ap_email"]

    }



    @AfterMethod
    public void tearDown(){

        //driver.quit();
    }
}
