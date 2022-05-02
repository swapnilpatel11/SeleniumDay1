package com.fdmgroup.test;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.fdmgroup.util.DriverUtilities;

public class TestBasicSeleniumCommands {
	private DriverUtilities driverUtilities;
	private WebDriver driver;
	
	@Before
	public void setUp() {
		driverUtilities = DriverUtilities.getInstance();
		driver = driverUtilities.getDriver();
	}
	
	@Test
	public void testBrowserCommands() throws IOException {
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		System.out.println("Title of current page: " + driver.getTitle());
		System.out.println("current URL: " + driver.getCurrentUrl());
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcFile,new File( "C:\\Users\\Swapn\\eclipse-workspace\\WebDrvierDemoDay1\\target\\screenshot.png"));
		
		driver.navigate().to("https://www.adidas.com"); //to navigate to specific URL
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		
		System.out.println("BrowserName: "+((RemoteWebDriver)driver).getCapabilities().getBrowserName());
		System.out.println("BrowserVersion: "+((RemoteWebDriver)driver).getCapabilities().getBrowserVersion());
	}
	
	@After
	public void destory() {
		//driver.close();  //close current browser
		driver.quit(); //close all windows
	}
}
