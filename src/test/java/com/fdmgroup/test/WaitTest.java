package com.fdmgroup.test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fdmgroup.util.DriverUtilities;

public class WaitTest {
	
	private DriverUtilities driverUtilities;
	private WebDriver driver;
	
	@Before
	public void setUp() {
		driverUtilities = DriverUtilities.getInstance();
		driver = driverUtilities.getDriver();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //Implicit wait applied
	}
	
	@Test
	@Ignore
	public void explicitWaitTest() {
		driver.get("https://www.google.com");
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("fdm group" + Keys.ENTER);
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		WebElement result = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//h3[@class='LC20lb MBeuO DKV0Md'][contains(text(),'Tech & Business Careers | Recruit Train Deploy | F')])[1]")));
		System.out.println(result.getText());
		driver.quit();
	}
	
	@Test
	public void fluentWait() {
		driver.get("https://www.google.com");
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("Selenium" + Keys.ENTER);

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(40))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		
		WebElement seleniumLink = wait.until(new Function<WebDriver,WebElement>(){

			@Override
			public WebElement apply(WebDriver t) {
				WebElement element = driver.findElement(By.partialLinkText("Selenium - Health Professional"));
				if(element.isEnabled()) {
					System.out.println("Element is visible");
				}
				return element;
			}
			
		});
		
		seleniumLink.click();
	}

}
