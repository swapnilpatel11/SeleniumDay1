package com.fdmgroup.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.fdmgroup.util.DriverUtilities;

public class TestScriptsZeroBanking {
	private DriverUtilities driverUtilities;
	private WebDriver driver;
	
	@Before
	public void setUp() {
		driverUtilities = DriverUtilities.getInstance();
		driver = driverUtilities.getDriver();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //Implicit wait applied
	}
	
	@Test
	public void loginTest() {
		driver.manage().window().maximize();
		driver.get("http://zero.webappsecurity.com/index.html");
		WebElement signInButton = driver.findElement(By.xpath("//button[contains(@id,'signin')]"));
		signInButton.click();
		
		String actual = driver.findElement(By.tagName("h3")).getText();
		assertEquals("Log in to ZeroBank",actual);
		
		driver.findElement(By.xpath("//input[contains(@id,'login')]")).sendKeys("username");
		driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys("password");
		//before click
		WebElement checkbox = driver.findElement(By.xpath("//*[contains(@type,'checkbox')]"));
		
		System.out.println(checkbox.isSelected());
		checkbox.click();
		//after click
		System.out.println(checkbox.isSelected());
		
		WebElement signIn = driver.findElement(By.xpath("//input[contains(@name,'submit')]"));
		
		//Different way to submit 
		signIn.click(); //1)click method
		//signIn.submit(); //2) submit button
		//signIn.sendKeys(Keys.ENTER); //3)Enter key button
		
		driver.navigate().back();
		
		//Click on Online Banking
		driver.findElement(By.xpath("//strong[contains(text(),'Online Banking')]")).click();
		
		//Click on Pay Bills
		driver.findElement(By.xpath("//span[contains(@id,'pay_bills_link')]")).click();
		
		//Verification for right page
		String paybillPage = driver.findElement(By.xpath("//h2[contains(text(),'Make payments to your saved payees')]")).getText();
		assertEquals("Make payments to your saved payees",paybillPage);
		
		WebElement payeeDropdown = driver.findElement(By.xpath("//select[@id='sp_payee']"));
		Select payeeSelect = new Select(payeeDropdown);
		List<WebElement> listOfOptions =  payeeSelect.getOptions(); //it gives all options list
		for(WebElement element : listOfOptions) {
			System.out.println(element.getText());
		}
		
	//	payeeSelect.selectByIndex(2);
	//	payeeSelect.selectByValue("bofa");  //it selects value which is inside DOM value tag
		payeeSelect.selectByVisibleText("Apple");
		
		System.out.println("Dropdown multiple : " + payeeSelect.isMultiple());
						
		WebElement usernameDropdown = driver.findElement(By.xpath("//a[contains(.,'username')]"));
		String username = driver.findElement(By.xpath("//a[contains(.,'username')]")).getText();
		assertEquals("username",username);
		
		usernameDropdown.click();
		
		WebElement profileLabel = driver.findElement(By.xpath("//a[normalize-space()='My Profile']"));
		System.out.println("Profile label is: " +profileLabel.isEnabled());
		
		WebElement logoutLink = driver.findElement(By.xpath("//a[@id='logout_link']"));
		if(logoutLink.isDisplayed())
		{
			logoutLink.click();
		}
		
		
	}
}
