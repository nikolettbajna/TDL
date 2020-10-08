package com.qa.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyListsPage {
	
	private WebDriver driver;
	public final static String URL = "http://localhost:5500/html/viewLists.html";
//	private Integer id;
	
	@FindBy(xpath = "//*[@id=\"backToHome\"]")
	private WebElement backToHomeLink;
	
//	@FindBy(css = "body > div > section > article > p > article:nth-child(${id}) > a")
//	private WebElement viewList;
	
	public MyListsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void backToHomeTest(){
		backToHomeLink.click();
		System.out.println(driver.getTitle());
	}

}
