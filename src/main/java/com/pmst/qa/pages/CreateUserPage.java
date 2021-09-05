package com.pmst.qa.pages;

import com.pmst.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateUserPage extends TestBase {
    @FindBy(xpath = "//*[@id=\"ap_register_form\"]")
    private WebElement form;

    @FindBy(xpath = "//*[@id='ap_customer_name']")
    private WebElement customerName;

    @FindBy(xpath = "//*[@id='auth-country-picker']")
    private WebElement countryCode;

    @FindBy(xpath = "//*[@id='ap_phone_number']")
    private WebElement phoneNumber;

    @FindBy(xpath = "//*[@id='ap_password']")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"ap_email\"]")
    private WebElement email;

    @FindBy(xpath = "//*[@id=\"continue\"]")
    private WebElement continueBtn;



    @FindBy(xpath = "//*[@id=\"auth-country-picker-container\"]")
    private WebElement countryCodeSpan;

    public CreateUserPage() {
        PageFactory.initElements(driver, this);
    }


    public boolean verifyCustomerNameInput(){
        return this.customerName.isDisplayed();
    }

    public boolean verifySelectCountryCode(){ return this.countryCode.isDisplayed();};

    public WebElement getForm() {
        return this.form;
    }

    public WebElement getCustomerName() {
        return this.customerName;
    }

    public WebElement getCountryCode() {
        return this.countryCode;
    }

    public WebElement getPhoneNumber() {
        return this.phoneNumber;
    }

    public WebElement getPassword() {
        return this.password;
    }

    public WebElement getEmail() {
        return this.email;
    }

    public WebElement getContinueBtn() {
        return this.continueBtn;
    }

    public WebElement getCountryCodeSpan() {
        return this.countryCodeSpan;
    }

    public WebElement getSelectCountryCode(){ return this.countryCode;};

    public void setCustomerName(String name) { this.customerName.sendKeys(name);}

    public void setCountryCode(String code) { ((Select)this.countryCode).selectByValue(code);}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber.sendKeys(phoneNumber);}

    public void setPassword(String password) {this.password.sendKeys(password);}

    public void setEmail(String email) {this.password.sendKeys(email);}

    public void enterCreateUserBtn(){
        this.continueBtn.click();
    }











}
