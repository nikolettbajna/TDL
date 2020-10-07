package com.qa.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewListPage {
	
	private WebDriver driver;
	public final static String URL = "http://localhost:5500/html/newList.html";
	
	public NewListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath = "//*[@id=\"backToHome\"]")
	private WebElement backToHomeLink;
	
	public void backToHomeTest(){
		backToHomeLink.click();
		System.out.println(driver.getTitle());
	}

}
