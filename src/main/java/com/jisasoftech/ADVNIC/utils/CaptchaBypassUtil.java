package com.jisasoftech.ADVNIC.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CaptchaBypassUtil {

    /**
     * For manual CAPTCHA solving.
     * Pauses the execution and waits for manual input to proceed.
     * @param driver WebDriver instance
     */
	public static void waitForManualCaptcha(WebDriver driver) {
	    System.out.println("Solve the CAPTCHA manually, and the code will resume after detecting a change.");
	    // Wait until the CAPTCHA field is cleared or changed
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
	    By captchaLocator = By.cssSelector("input#captchaInput");
	    wait.until(ExpectedConditions.attributeToBe(captchaLocator, "value", "")); // Assuming CAPTCHA value changes to empty after solving.
	}

    
    

}
