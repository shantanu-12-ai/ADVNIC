package com.jisasoftech.ADVNIC.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jisasoftech.ADVNIC.pages.LoginPage;
import com.jisasoftech.ADVNIC.utils.ConfigReader;
import com.jisasoftech.ADVNIC.utils.RemoteOTPFetcher;

public class ForgetPasswordTest extends BaseTest {

	LoginPage login;
	
	@BeforeMethod
	public void setup() {
	    if (pageObjectManager == null) {
	        super.setup();
	    }
	    login = pageObjectManager.getLoginPage();
	}

	
	@Test
	public void verifyForgetPasswordLink() throws Exception {
		assertTrue(login.isForgotPasswordLinkDisplayed(), "Forget Passsword link is not displayed.");
		Thread.sleep(3000);
	}
	
	@Test
	public void testForgetPasswordLinkNavigationWithValidUsername() throws Exception {
		login.enterUsername(ConfigReader.getProperty("usernameone"));
		login.clickOnForgotPasswordLink();
		WebElement passwordResetModalWindow= wait.until(ExpectedConditions.visibilityOf(login.getResetPasswordModalWindow()));
	    assertTrue(passwordResetModalWindow.isDisplayed(), "forget password link failed to open reset password modal window");
	    Thread.sleep(3000);
	
	
	
	
	}
	
	@Test
    public void testAlertMessageDisplayedOnForgetPasswordWithoutUsername() throws Exception {
		login.clickOnForgotPasswordLink();
		// wait for alert to appear
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		String alertMessage = alert.getText();
		assertEquals(alertMessage, "Please enter username", "Incorrect alert message");
		alert.accept();
		Thread.sleep(3000);
	}
	
	@Test
	public void verifyUsernameFieldDisabledOnForgetPasswordModalWindow() throws Exception {
		login.enterUsername(ConfigReader.getProperty("usernametwo"));
		login.clickOnForgotPasswordLink();
		assertTrue(login.isUsernameFieldDisabledOnModalWindowForgetPassword(),"username field is editable but it should be disabled ");
		Thread.sleep(3000);
	}
	
	@Test
	public void verifyPasswordMaskedForSecureOperation() {
		login.enterUsername(ConfigReader.getProperty("usernameone"));
		login.clickOnForgotPasswordLink();
		String typeAttribute = login.getInputElementTypeAttributeOnModalWindow();
		assertEquals(typeAttribute, "password", "The password is not masked as type attribute is not 'password'");
	}
	
	@Test
	public void verifySubmitButtonClickableAfterEnteringAllDetails() {
		login.enterUsername(ConfigReader.getProperty("usernameone"));
		login.clickOnForgotPasswordLink();
		login.enterNewPasswordInResetPasswordModalWindow(ConfigReader.getProperty("newpassword"));
		login.clickSendOTPButton();
		// Wait for OTP to be generated in logs
        //Thread.sleep(5000);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        // Fetch the latest OTP from remote logs
        String otp = RemoteOTPFetcher.fetchOTPFromServer();
        if (otp == null) {
            throw new RuntimeException("OTP not found in logs!");
        }

        login.enterOTP(otp);
		assertTrue(login.isSubmitButtonEnabled(), "submit button is disabled and cannot be clicked");
	}
	
	@Test
	public void verifyCancelButtonFunctionality() {
		login.enterUsername(ConfigReader.getProperty("usernameone"));
		login.clickOnForgotPasswordLink();
	    login.clickOnCancelButton();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
