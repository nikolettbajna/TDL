package com.qa.selenium;

import java.util.List;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath = "//*[@id=\"title\"]")
	private WebElement newTitle;
	
	@FindBy(xpath = "//*[@id=\"createBtn\"]")
	private WebElement create;
	
	public void backToHomeTest(){
		backToHomeLink.click();
		System.out.println(driver.getTitle());
	}
	
	public void newList() {
		String nTitle = "example2";
		newTitle.sendKeys(nTitle);
		create.click();
		List<WebElement> lists = driver.findElements(By.cssSelector("/html/body/div/section/article/p"));
        for(WebElement list: lists) {
        	System.out.println(list.findElement(By.cssSelector("p alticle:last-child")).getText());
        }
	}

}
