package com.jisasoftech.ADVNIC.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.jisasoftech.ADVNIC.pages.ApplicationManagerPage;
import com.jisasoftech.ADVNIC.pages.DashBoardPage;
import com.jisasoftech.ADVNIC.pages.LoginPage;
import com.jisasoftech.ADVNIC.utils.ConfigReader;

public class ApplicationModuleTest extends BaseTest {

LoginPage login;
DashBoardPage dash;
ApplicationManagerPage app;
	
	@BeforeMethod
	public void setup() throws Exception {
	    if (pageObjectManager == null) {
	        super.setup();
	    }
	    login = pageObjectManager.getLoginPage();
	    login.enterUsername(ConfigReader.getProperty("username"));
	    login.enterPassword(ConfigReader.getProperty("password"));
	    // manually entering captcha and to halt thread sleep is added
	    Thread.sleep(20000);
	    login.clickOnLogInButton();
	    dash = pageObjectManager.getDashBoardPage();
	    app = pageObjectManager.getApplicationManagerPage();
	}
	
	@Test
	public void validateApplicationManagerPageLoads() {
		SoftAssert softAssert = new SoftAssert();
		
		dash.clickOnApplicationModuleLink();
		dash.clickOnApplicationManagerLink();
		
		// validate page loaded
		softAssert.assertTrue(app.isApplicationManagerPageLoaded(), "Application Manager page did not load properly.");
		
		//validate Table Headers
		String[] expectedHeaders = {"Sr.No", "Application Name", "PKI Authentication", "Status", "On-board", "Validate Data", "Action"};
        List<WebElement> headers = driver.findElements(By.xpath("//table/thead/tr/th"));
        for(int i =0; i < expectedHeaders.length; i++) {
        	if(i == 1 && i == 3)
        		continue;
        	softAssert.assertEquals(headers.get(i).getText(), expectedHeaders[i], "Mismatch in table headers.");
        }
        
        // Validate Action Buttons Exist in Each Row
        List<WebElement> actionButtons = driver.findElements(By.xpath("//td[last()]/span/a"));
        softAssert.assertFalse(actionButtons.isEmpty(), "Action buttons are missing in the table.");

        softAssert.assertAll(); // Execute all assertions
		
	}
	

	
	
}
