package com.jisasoftech.ADVNIC.pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jisasoftech.ADVNIC.utils.ConfigReader;

public class LoginPage {
	private WebDriver driver;
	public WebDriverWait wait;

	@FindBy(css = "input#loginModel_UserName")
	private WebElement email;
	
	@FindBy(css = "input#loginModel_Password")
	private WebElement password;
	
	@FindBy(css = "input#captchaInput")
	private WebElement captchaInputField;
	
	@FindBy(css = "button#SignIn")
	private WebElement signInBtn;
	
	@FindBy(css = "img.login-logo")
	private WebElement logoElement;
	
	@FindBy(css = "a#resetPasswored")
	private WebElement forgotPasswordLink;
	
	@FindBy(xpath = "//p[contains(text(), 'Version: 8')]")
	private WebElement version;
	
	@FindBy(css = "input#captchaInput")
	private WebElement captchaInputBox;
	
	@FindBy(xpath = "//title")
	private WebElement loginPageTitle;
	
	@FindBy(css = "span#loginModel_Password-error")
	private WebElement passwordErrorMessage;
	
	@FindBy(css = "span#loginModel_UserName-error")
	private WebElement usernameErrorMessage;
	
	@FindBy(xpath = "//form[@id='login']/following-sibling::b")
	private WebElement invalidCredentialsErrorMessage;
	
	@FindBy(css = "span#loginModel_UserName-error")
	private WebElement invalidEmailFormatErrorMessage;
	
	@FindBy(css = "div.modal-content")
	private WebElement resetPasswordModalWindow;
	
	@FindBy(css = "input#fogetPassword_FPUsername")
	private WebElement usernameModalWindow;
	
	@FindBy(css = "input#fogetPassword_FPNewPassowrd")
	private WebElement passwordModalWindow;
	
	@FindBy(css = "button#sendOTP")
	private WebElement sendOTPButton;
	
	@FindBy(css = "input#fogetPassword_MobileOTP")
	private WebElement otpInputField;
	
	@FindBy(css = "button#btnsubmit")
	private WebElement submitButton;
	
	@FindBy(css = "button#cancelResetPass")
	private WebElement cancelButton;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public boolean isEmailDisplayed() {
		return email.isDisplayed();		
	}
	
	public boolean isPasswordDisplayed() {
		return password.isDisplayed();
	}
	
	public boolean isSigninButtonDisplayed() {
		return signInBtn.isDisplayed();
	}
	
	public boolean isLogoDisplayed() {
		return logoElement.isDisplayed();
	}
	
	public boolean isForgotPasswordLinkDisplayed() {
       return forgotPasswordLink.isDisplayed();
		
	}
	public boolean isVersionDisplayed() {
		return version.isDisplayed();
	}
	
	public void enterUsername(String emailid) {
		email.sendKeys(emailid);
	}
	
	public void enterPassword(String passWord) {
		password.sendKeys(passWord);
	}
	
	public void clickOnLogInButton() {
		signInBtn.click();
	}
	
	public void handleAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			System.out.println("Alert text is: " + alert.getText());
			alert.accept();
		}
		catch(NoAlertPresentException e) {
			System.out.println("Alert is not present.");
		}
	}
	
	public static String readCaptcha(WebElement captchaElement) {
        if (captchaElement == null || captchaElement.getText().isEmpty()) {
            throw new RuntimeException("CAPTCHA element is missing or has no text");
        }
        return captchaElement.getText().trim();
    }
	
	public void enterCaptcha() {
		
		captchaInputBox.sendKeys();
		
	}
	
    public String getTitle() {
    	return driver.getTitle();
    }
    
    public WebElement getPasswordElement() {
    	return password;
    }
    
    public String getPasswordErrorMessage() {
    	return passwordErrorMessage.getText();
    	
    }
    
    public String getUserNameErrorMessage() {
    	
    	return usernameErrorMessage.getText();
    }

    public String getInvalidCredentialErrorMessage() {
    	return invalidCredentialsErrorMessage.getText();
    }
    
    public String getInvalidEmailFormatErrorMessage() {
    	return invalidEmailFormatErrorMessage.getText();
    }
    
    public void clickOnPasswordField() {
    	password.click();
}
  public void login() throws InterruptedException {
	  email.sendKeys(ConfigReader.getProperty("username"));
	  password.sendKeys(ConfigReader.getProperty("password"));
	  Thread.sleep(20000);
	  clickOnLogInButton();
	  Thread.sleep(5000);
	  handleAlert();
	  
  }
  
  public void clickOnForgotPasswordLink() {
	  forgotPasswordLink.click();
	  
  }
  
  public WebElement getResetPasswordModalWindow() {
	  return resetPasswordModalWindow;
  }
  
  public boolean isUsernameFieldDisabledOnModalWindowForgetPassword() {
	  return usernameModalWindow.isEnabled();
  }
  
  public String getInputElementTypeAttributeOnModalWindow() {
	  return passwordModalWindow.getAttribute("type");
  }
  
  public void enterNewPasswordInResetPasswordModalWindow(String newPassword) {
	  passwordModalWindow.sendKeys(newPassword);
  }
  
  public void clickSendOTPButton() {
	  sendOTPButton.click();
  }
  
  public void enterOTP(String otp) {
	  otpInputField.sendKeys(otp);
  }
  
  public void clickSubmitButton() {
	  submitButton.click();
  }
 
  public void clickOnCancelButton() {
	  cancelButton.click();
  }
  
  public boolean isSubmitButtonEnabled() {
	 return submitButton.isEnabled();
  }
  
  public boolean isCancelButtonEnabled() {
	  return cancelButton.isEnabled();
  }
  
  
  
  
  
  
  
  
  
  
  
  
}