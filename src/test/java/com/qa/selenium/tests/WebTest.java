package com.qa.selenium.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTest {

	public static WebDriver driver;
	public final static String URL = "http://localhost:5500/html/index.html";
	
	public static String test;
	
	@BeforeClass
	public static void initialise() {
		System.setProperty(
			"webdriver.chrome.driver",
			"D:\\Nikolett\\QA\\TDL\\src\\test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1366, 768));
	    driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
	}
	
	@Test
	public void pageTests() throws Exception {
		driver.get(URL);
		WebElement newList = driver.findElement(By.xpath("//*[@id=\"newList\"]"));
	    newList.click();
	    WebElement newTitle = driver.findElement(By.xpath("//*[@id=\"title\"]"));
	    String nT = "extra list test";
	    newTitle.sendKeys(nT);
	    WebElement createList = driver.findElement(By.xpath("//*[@id=\"createBtn\"]"));
	    createList.click();
	    assertEquals("New To-Do List",driver.getTitle());
	    System.out.println(driver.getTitle());
	    List<WebElement> lists = driver.findElements(By.cssSelector("p[class='tdlists']"));
	    System.out.println("Array size = "+lists.size());
        for(WebElement list: lists) {
        	test = list.findElement(By.cssSelector("article:nth-child[2] h3")).getText();
        	System.out.println("test: "+test);
        }
		assertEquals("~ shopping list", test);
		WebElement deleteList = driver.findElement(By.cssSelector("body > div > section > article > p > article:nth-child(2) > button:nth-child(2)"));
	    deleteList.click();
	    assertEquals("~ and a list", test);
	    WebElement viewList = driver.findElement(By.cssSelector("body > div > section > article > p > article:nth-child(1) > button:nth-child(1)"));
	    viewList.click();
	    assertEquals("Tasks in my To-Do List", driver.getTitle());
	    
	}
	
	@AfterClass
	public static void cleanUp() {
		driver.quit();
	}
	
}
