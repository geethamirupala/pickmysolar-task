package com.pmst.qa.pages;

import com.pmst.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearhSubPage extends TestBase {

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchTxt;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchBtn;

    public SearhSubPage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getSearchTxt() {
        return searchTxt;
    }

    public WebElement getSearchBtn() {
        return searchBtn;
    }

    public void enterSearchTxt(String text){
        this.searchTxt.sendKeys(text);
    }

    public void searchPage(){
        this.searchBtn.click();
    }



}
