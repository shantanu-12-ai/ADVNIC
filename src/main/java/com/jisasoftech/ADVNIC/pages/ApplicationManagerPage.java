package com.jisasoftech.ADVNIC.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jisasoftech.ADVNIC.utils.ConfigReader;

public class ApplicationManagerPage {

	public WebDriver driver;
	public WebDriverWait wait;
	
	public ApplicationManagerPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("explicit_wait"))));
	}
	
	@FindBy(xpath = "//h1[contains(text(),'Application Manager')]")
	private WebElement pageHeading;
	
	public boolean isApplicationManagerPageLoaded() {
	    try {
	    	
	         wait.until(ExpectedConditions.visibilityOf(pageHeading));
	        return pageHeading.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}


}
