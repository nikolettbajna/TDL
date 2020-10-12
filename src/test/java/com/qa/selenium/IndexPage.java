package com.qa.selenium;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
	
	private WebDriver driver;
	public final static String URL = "http://localhost:5500/html/index.html";
	private NewListPage nLPage;
	private MyListsPage mLPage;
	
	public IndexPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath = "//*[@id=\"newList\"]")
	private WebElement newListLink;
	
	@FindBy(xpath = "//*[@id=\"myLists\"]")
	private WebElement myListsLink;
	
	public void newListLoadingTest() {
		newListLink.click();
		System.out.println(driver.getTitle());
        assertEquals("New To-Do List", driver.getTitle());
        driver.get(NewListPage.URL);
        nLPage = new NewListPage(driver);
        nLPage.backToHomeTest();
	}
	
	public void myListsLoadingTest() {
		myListsLink.click();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());
        assertEquals("My To-Do Lists", driver.getTitle());
        driver.get(MyListsPage.URL);
        mLPage = new MyListsPage(driver);
        mLPage.backToHomeTest();
	}
	
}
