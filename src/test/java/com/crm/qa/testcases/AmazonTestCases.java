package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AmazonTestCases extends TestBase {
    AmazonTestCases(){
        super();
        initialization();
    }

    @BeforeClass
    public void loadAmazonPage(){
       // driver.get("https://www.amazon.in");

    }

    @Test(priority = 0)
    public  void createAccount() throws InterruptedException {
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.cssSelector("a#nav-link-accountList>span>span")).click();
        driver.findElement(By.linkText("Start here.")).click();

        driver.findElement(By.id("ap_customer_name")).sendKeys("NaveenTestOTP");
        driver.findElement(By.id("auth-country-picker-container")).click();

        driver.findElement(By.xpath("//ul[@role='application']//li/a[contains(text(),'United States +1')]")).click();
        driver.findElement(By.id("ap_phone_number")).sendKeys("3343734019");
        driver.findElement(By.id("ap_password")).sendKeys("TestAutomation@123");
        driver.findElement(By.id("continue")).click();



       // driver.findElement(By.id("auth-pv-enter-code")).sendKeys(OTPNumber);

    }



    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
