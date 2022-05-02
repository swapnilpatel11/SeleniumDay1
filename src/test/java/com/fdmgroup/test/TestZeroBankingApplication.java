package com.fdmgroup.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fdmgroup.util.DriverUtilities;

public class TestZeroBankingApplication {
	private DriverUtilities driverUtilities;
	private WebDriver driver;
	
	@Before
	public void setUp() {
		driverUtilities = DriverUtilities.getInstance();
		driver = driverUtilities.getDriver();
	}
	
	@Test
	public void testBasicFunctionality() {
		driver.manage().window().maximize();
		driver.get("http://zero.webappsecurity.com/index.html");
		WebElement signIn = driver.findElement(By.id("signin_button"));
		signIn.click();
		
		WebElement loginPageLabel = driver.findElement(By.tagName("h3"));
		String actual=loginPageLabel.getText();
		assertEquals("Log in to ZeroBank",actual);
		WebElement userField = driver.findElement(By.name("user_login"));
		userField.sendKeys("john"); //input from keyboard
		userField.clear();
		
		driver.findElement(By.partialLinkText("Forgot")).click();
		driver.findElement(By.linkText("Zero Bank")).click();
		
	}
	
	@Test
	public void testCustomizedXpath() {
		driver.manage().window().maximize();
		driver.get("http://zero.webappsecurity.com/index.html");
		driver.findElement(By.xpath("//h4[contains(text(),' Online')]")).getText();
		
	}
	
	@Test
	public void testSecond() {
		driver.manage().window().maximize();
		driver.get("http://zero.webappsecurity.com/index.html");
		
		List<WebElement> listOfElements = driver.findElements(By.cssSelector("ul > li"));
		for(WebElement element : listOfElements) {
			System.out.println(element.getText());
		}
	}
	
	@After
	public void destroy() {
		driver.quit();
	}

}
