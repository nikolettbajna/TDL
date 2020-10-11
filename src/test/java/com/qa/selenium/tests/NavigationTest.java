package com.qa.selenium.tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.selenium.IndexPage;

public class NavigationTest {
	public static WebDriver driver;
	IndexPage iPage;
	
	@BeforeClass
	public static void initialise() {
		System.setProperty(
			"webdriver.chrome.driver",
			"D:\\Nikolett\\QA\\TDL\\src\\test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1366, 768));
	    driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	
	@Test
	public void pageLoadingTests() throws Exception {
		driver.get(IndexPage.URL);
        assertEquals("To-Do List Project", driver.getTitle());
        iPage = new IndexPage(driver);
        iPage.newListLoadingTest();
        iPage.myListsLoadingTest();
	}
	
	@AfterClass
	public static void cleanUp() {
		driver.quit();
	}
}
