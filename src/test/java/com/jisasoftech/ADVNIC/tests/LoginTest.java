package com.jisasoftech.ADVNIC.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jisasoftech.ADVNIC.pages.LoginPage;
import com.jisasoftech.ADVNIC.utils.ConfigReader;

public class LoginTest extends BaseTest{
	
	LoginPage login; 
	
	@BeforeMethod
	public void initializeLoginObject() {
		login = new LoginPage(driver);
	}
	
	@Test
	public void validateLoginPageElements() {
		assertTrue(login.isEmailDisplayed(), "email field is not displayed");
		assertTrue(login.isForgotPasswordLinkDisplayed(), "forgot password link is not displayed");
		assertTrue(login.isLogoDisplayed(), "logo is not displayed");
		assertTrue(login.isPasswordDisplayed(), "password field is not displayed");
		assertTrue(login.isSigninButtonDisplayed(), "sign in button is not displayed");
		assertTrue(login.isVersionDisplayed(), "version is not displayed");
	}
	
	@Test
	public void validateLoginWithValidData() throws InterruptedException {
		login.enterUsername(ConfigReader.getProperty("username"));
		login.enterPassword(ConfigReader.getProperty("password"));
		Thread.sleep(20000);
		login.clickOnLogInButton();
		Thread.sleep(6000);
		login.handleAlert();
		Thread.sleep(6000);
	}
	
	@Test
	public void verifyTitle() {
		String ExpectedTitle = "SecureVault Admin Portal | Log in";
		String actualTitle = login.getTitle();
		assertEquals(actualTitle, ExpectedTitle, "tile is wrong");
	}
	
	@Test
	public void validatePasswordMasked() throws InterruptedException {
		login.enterPassword(ConfigReader.getProperty("password"));
		Thread.sleep(6000);
		String typeAttributeValue = login.getPasswordElement().getAttribute("type");
		assertEquals(typeAttributeValue, "password", "password field is not masked");
	}
	
	@Test
	public void validatePasswordErrorMessage() throws InterruptedException {
		login.enterUsername(ConfigReader.getProperty("username"));
		login.clickOnLogInButton();
		String actualPasswordErrorMessage = login.getPasswordErrorMessage();
		String expectedPasswordErrorMessage = "Please enter password";
		assertEquals(actualPasswordErrorMessage, expectedPasswordErrorMessage, "error message is incorrect");
		Thread.sleep(4000);
	}
	
	@Test
	public void validateUsernameErrorMessage() throws InterruptedException{
		login.enterPassword(ConfigReader.getProperty("password"));
		login.clickOnLogInButton();
		String actualErrorMessage = login.getUserNameErrorMessage();
		String expectedErrorMessage = "Please enter user email";
		assertEquals(actualErrorMessage, expectedErrorMessage, "wrong error message");
		Thread.sleep(4000);
	}
	
	@Test
	public void validateBlankFieldsErrorMessage() throws InterruptedException {
		login.clickOnLogInButton();
		String actualPasswordErrorMessage = login.getPasswordErrorMessage();
		String expectedPasswordErrorMessage = "Please enter password";
		assertEquals(actualPasswordErrorMessage, expectedPasswordErrorMessage, "error message is incorrect");
		String actualErrorMessage = login.getUserNameErrorMessage();
		String expectedErrorMessage = "Please enter user email";
		assertEquals(actualErrorMessage, expectedErrorMessage, "wrong error message");
		Thread.sleep(5000);
	}
	
	@Test
	public void validateLoginWithInvalidPassword() throws InterruptedException {
		login.enterUsername(ConfigReader.getProperty("username"));
		login.enterPassword(ConfigReader.getProperty("invalidpassword"));
		Thread.sleep(20000);
		login.clickOnLogInButton();
		Thread.sleep(6000);
		login.handleAlert();
		Thread.sleep(6000);
		String actualInvalidPasswordErrorMessage= login.getInvalidCredentialErrorMessage();
		String expectedInvalidPasswordErrorMessage = "You have entered wrong credentials. You have only 2 attempt(s) left. ";
		assertEquals(actualInvalidPasswordErrorMessage.trim(),expectedInvalidPasswordErrorMessage.trim(), "Error message is incorrect");
		
	}
	
	@Test
	public void validateLoginWithInvalidUsername() throws InterruptedException{
		login.enterUsername(ConfigReader.getProperty("invalidusername"));
		login.enterPassword(ConfigReader.getProperty("password"));
		Thread.sleep(20000);
		login.clickOnLogInButton();
		Thread.sleep(6000);
		login.handleAlert();
		Thread.sleep(6000);
		String actualInvalidUsernameErrorMessage= login.getInvalidCredentialErrorMessage();
		String expectedInvalidUsernameErrorMessage = "You have entered wrong credentials. You have only 2 attempt(s) left. ";
		assertEquals(actualInvalidUsernameErrorMessage.trim(),expectedInvalidUsernameErrorMessage.trim(), "Error message is incorrect");
		
	}
	
	@Test
	public void validteLoginWithInvalidCredntials() throws InterruptedException{
		login.enterUsername(ConfigReader.getProperty("invalidusername"));
		login.enterPassword(ConfigReader.getProperty("invalidpassword"));
		Thread.sleep(20000);
		login.clickOnLogInButton();
		Thread.sleep(6000);
		login.handleAlert();
		Thread.sleep(6000);
		String actualInvalidUsernameErrorMessage= login.getInvalidCredentialErrorMessage();
		String expectedInvalidUsernameErrorMessage = "Wrong username or password. ";
		assertEquals(actualInvalidUsernameErrorMessage.trim(),expectedInvalidUsernameErrorMessage.trim(), "Error message is incorrect");
		
	}
	
	@Test
	public void validateErrorMessageForInvalidEmailFormat() {
		login.enterUsername(ConfigReader.getProperty("invalidemailformat"));
		login.clickOnPasswordField();
		String actualInvalidEmailFormatErrorMsg = login.getInvalidEmailFormatErrorMessage();
		String expectedInvalidEmailFormatErrorMsg = "Please enter valid User email ";
		assertEquals(actualInvalidEmailFormatErrorMsg.trim(), expectedInvalidEmailFormatErrorMsg.trim(), "wrong error message displayed");
	}
	
	@Test
	public void testRedirectLogin() throws InterruptedException {
		login.enterUsername(ConfigReader.getProperty("username"));
		login.enterPassword(ConfigReader.getProperty("password"));
		Thread.sleep(20000);
		login.clickOnLogInButton();
		Thread.sleep(6000);
		login.handleAlert();
		// checking if login is successful
		assertTrue(driver.getCurrentUrl().contains("Dashboard"), "login unsucessful");
		String loggedInUrl = driver.getCurrentUrl();
		// opening new browser
		WebDriver newDriver = new ChromeDriver();
		newDriver.get(loggedInUrl);
		byPassSecurity(newDriver);
		assertTrue(newDriver.getCurrentUrl().contains("Adv"), "user is not redirected to login page");
		Thread.sleep(3000);
		
	}
	
	@Test
	public void validateBackButtonDoesNotLogOutUser() {
		login.enterUsername(ConfigReader.getProperty("username"));
		login.enterPassword(ConfigReader.getProperty("password"));
		login.clickOnLogInButton();
		driver.navigate().back();
		assertTrue(driver.getCurrentUrl().contains("Dashboard"), "back button logs out user which is not expected");
		
	}
}


