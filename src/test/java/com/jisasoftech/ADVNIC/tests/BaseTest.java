package com.jisasoftech.ADVNIC.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.jisasoftech.ADVNIC.utils.ConfigReader;
import com.jisasoftech.ADVNIC.utils.PageObjectManager;
import com.jisasoftech.ADVNIC.utils.WebDriverManagerUtil;

public class BaseTest {

	public static WebDriver driver;
	public PageObjectManager pageObjectManager;
	public WebDriverWait wait;
	
	@BeforeClass
	public void setup() throws Exception{
		WebDriverManagerUtil util = new WebDriverManagerUtil();
		driver = WebDriverManagerUtil.initializeDriver();
		driver.get(ConfigReader.getProperty("url"));
		byPassSecurity(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("explicit_wait"))));
		pageObjectManager = new PageObjectManager(driver);
	}
	
	@AfterClass
	public void tearDown() {
		if(driver != null)
			driver.quit();
	}
	public static void byPassSecurity(WebDriver driver) {
		driver.findElement(By.cssSelector("button#details-button")).click();
		driver.findElement(By.cssSelector("a#proceed-link")).click();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	}
