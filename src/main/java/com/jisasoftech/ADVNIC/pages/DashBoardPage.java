package com.jisasoftech.ADVNIC.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashBoardPage {
	private static WebDriver driver;
	
	@FindBy(css = "aside.main-sidebar")
	private WebElement sidebar;
	
	@FindBy(css = "div.container-fluid:nth-child(1)")
	private WebElement pageCenterField;
	
	@FindBy(css = "footer.main-footer")
	private WebElement footer;
	
	@FindBy(xpath = "//h1")
	private WebElement header;
	
	@FindBy(css = "footer.main-footer > span")
	private WebElement mainfooter;
	
	@FindBy(xpath = "//div[@class='btn-group']")
	private WebElement dropdown;
	
	@FindBy(xpath = "//a[@href='/Adv/Login/Logout?status=1']")
	private WebElement signoutLink;
	
	@FindBy(xpath = "//a")
	private List<WebElement> links;
	
	@FindBy(css = "ul.navbar-nav a.nav-link")
	private WebElement hamburgerMenu;
	
	@FindBy(css = "select#filterCnt")
	private WebElement dropDown;
	
	@FindBy(css = "select#filterCnt option")
	List<WebElement> dropdownoptions;
	
	
	@FindBy(xpath = "//div[contains(@class, 'small-box') and .//p[text()='Users Registered']]//h3")
	private WebElement usersRegistered;

	@FindBy(xpath = "//div[contains(@class, 'small-box') and .//p[text()='Application Registered']]//h3")
	private WebElement applicationsRegistered;

	@FindBy(xpath = "//div[contains(@class, 'small-box') and .//p[text()='Token Rules']]//h3")
	private WebElement tokenRules;

	@FindBy(xpath = "//div[contains(@class, 'small-box') and .//p[text()='Reference Policies']]//h3")
	private WebElement referencePolicies;

	@FindBy(xpath = "//div[contains(@class, 'small-box') and .//p[text()='HSMs']]//h3")
	private WebElement hsms;

	@FindBy(xpath = "//div[contains(@class, 'small-box') and .//p[text()='Keys']]//h3")
	private WebElement keys;

	@FindBy(xpath = "//div[contains(@class, 'small-box') and .//p[text()='Reference Tokens']]//h3")
	private WebElement referenceTokens;
    
	@FindBy(css = "a#custom-tabs-two-settings-tab")
	private WebElement managementSummaryTab;
	
	@FindBy(xpath = "//td[text()='Server Name']/following-sibling::td")
	private WebElement serverNameElement;
	
    @FindBy(xpath = "//td[text()='Server Time']/following-sibling::td")
	private WebElement serverTimeElement;
    
    @FindBy(xpath = "//td[text()='Last Login']/following-sibling::td")
    private WebElement lastLoginElement;
    
    @FindBy(xpath = "//td[text()='License Type']/following-sibling::td")
    private WebElement licenseTypeElement;
    
    @FindBy(xpath = "//td[text()='Home Directory']/following-sibling::td")
    private WebElement homeDirectoryElement;

	
	
	
	public DashBoardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getTitleOfDashBoardPage() {
		return driver.getTitle();
	}
	
	public boolean sidebarIsDisplayed() {
		return sidebar.isDisplayed();
	}
	
	public boolean pageCenterIsDisplayed() {
		return pageCenterField.isDisplayed();
	}
	
	public boolean footerIsDisplayed() {
		return footer.isDisplayed();
	}
	
	public String getHeaderText() {
		return header.getText();
	}
	
	public String getFooterText() {
		return mainfooter.getText();
	}
	
	public static boolean verifyWidgetText(WebDriver driver, String widgetName, String expectedWidgetValue){
		try {
		String widgetValueElementLocator  = "//div[@class='inner']/p[contains(text(), '" + widgetName + "')]/preceding-sibling::h3";
	    String  actualWidgetValue = driver.findElement(By.xpath(widgetValueElementLocator)).getText().trim();
	    if(actualWidgetValue.equals(expectedWidgetValue.trim()))
	    	return true;
	    else
	    	return false;
	
	}catch(Exception e) {
		System.out.println("unable to locate element" + e.getMessage());
		return false;
	}
	}
	public void clickOnProfile() {
		dropdown.click();
	}
	
	public void clickOnSignoutLink() {
		signoutLink.click();
	}
	
	public boolean verifyLinks() {
			for(WebElement link: links) {
				String linkText = link.getText();
				String href = link.getAttribute("href");
				if(href != null && !href.isEmpty()) {
					driver.navigate().to(href);
					String currentUrl = driver.getCurrentUrl();
					if(currentUrl.contains(href))
						return true;			
					
				}
			}
			return false;
			
}
	
	public boolean toggleAndVerifyMenu() {
	    try {
	        // Check initial state (expanded)
	        boolean isInitiallyDisplayed = sidebar.isDisplayed();

	        // Click the hamburger menu to toggle
	        hamburgerMenu.click();
	        Thread.sleep(1000); // Wait for the animation to complete

	        // Check if the sidebar visibility changed
	        boolean isAfterClickDisplayed = sidebar.isDisplayed();

	        // Validate the menu was minimized/expanded
	        if (isInitiallyDisplayed && !isAfterClickDisplayed) {
	            System.out.println("Menu successfully minimized");
	        } else if (!isInitiallyDisplayed && isAfterClickDisplayed) {
	            System.out.println("Menu successfully expanded");
	        } else {
	            throw new Exception("Menu did not toggle as expected");
	        }

	        // Return the final state for confirmation
	        return isAfterClickDisplayed;
	    } catch (Exception e) {
	        System.out.println("Error during menu toggle test: " + e.getMessage());
	        return false;
	    }
	}
	
	public boolean verifyDropdownToggle(WebDriver driver) {
	    try {
	        // Locate the <select> dropdown field
	        //WebElement dropdownElement = driver.findElement(By.cssSelector("select#filterCnt")); // Update selector as needed

	        // Initialize Select class
	        Select dropdown = new Select(dropDown);

	        // Get the list of options before clicking (initial state)
	        List<WebElement> optionsBeforeClick = dropdown.getOptions();
	        int initialOptionsCount = optionsBeforeClick.size();

	        // Simulate the click on the dropdown
	        dropDown.click(); // This should open the dropdown menu
	        Thread.sleep(1000); // Allow time for the dropdown to render

	        // Get the list of options after clicking
	        List<WebElement> optionsAfterClick = dropdown.getOptions();
	        int optionsCountAfterClick = optionsAfterClick.size();

	        // Check if the options are visible after clicking
	        boolean isOptionsVisible = optionsCountAfterClick > 0;

	        // Simulate the click again to close the dropdown
	        dropDown.click();
	        Thread.sleep(1000); // Allow time for dropdown to close

	        // Optionally verify that interacting again closes the dropdown (if applicable)
	        // For a <select>, options count won't change, so this is mainly a state check.

	        // Return the result of the visibility test
	        return isOptionsVisible;
	    } catch (Exception e) {
	        System.out.println("Error while verifying dropdown: " + e.getMessage());
	        return false;
	    }
	}
	
	public String getExpectedUsers(String optionText) {
		switch(optionText) {
		
		case "Last day":
			return usersRegistered.getText();
		case "Last 7 days":
			return usersRegistered.getText();
		case "Last 30 days":
		    return usersRegistered.getText();
		default:
			return usersRegistered.getText();				
		}	
	}
	
	public String getExpectedApplications(String optionText) {
		switch(optionText) {
		
		case "Last day":
			return applicationsRegistered.getText();
		case "Last 7 days":
			return applicationsRegistered.getText();
		case "Last 30 days":
			return applicationsRegistered.getText();
		default:
			return applicationsRegistered.getText();				
		}	
	}
	
	public String getExpectedTokenRules(String optionText) {
		switch(optionText) {
		
		case "Last day":
			return tokenRules.getText();
		case "Last 7 days":
			return tokenRules.getText();
		case "Last 30 days":
			return tokenRules.getText();
		default:
			return tokenRules.getText();				
		}	
	}
	
	public String getExpectedReferencePolicies(String optionText) {
		switch(optionText) {
		
		case "Last day":
			return referencePolicies.getText();
		case "Last 7 days":
			return referencePolicies.getText();
		case "Last 30 days":
			return referencePolicies.getText();
		default:
			return referencePolicies.getText();				
		}	
	}
	
	public String getExpectedHSMValue(String optionText) {
		switch(optionText) {
		
		case "Last day":
			return hsms.getText();
		case "Last 7 days":
			return hsms.getText();
		case "Last 30 days":
			return hsms.getText();
		default:
			return hsms.getText();				
		}	
	}
	
	public String getExpectedKeys(String optionText) {
		switch(optionText) {
		
		case "Last day":
			return keys.getText();
		case "Last 7 days":
			return keys.getText();
		case "Last 30 days":
			return keys.getText();
		default:
			return keys.getText();				
		}	
	}
	
	public String getExpectedReferenaceTokenValues(String optionText) {
		switch(optionText) {
		
		case "Last day":
			return referenceTokens.getText();
		case "Last 7 days":
			return referenceTokens.getText();
		case "Last 30 days":
			return referenceTokens.getText();
		default:
			return referenceTokens.getText();				
		}	
	}
	
	// Method to select a dropdown value
    public void selectDropdownOption(String optionText) {
        Select dropdown = new Select(dropDown);
        dropdown.selectByVisibleText(optionText);
    }
    
    public String getUsersRegisteredValue() {
        return usersRegistered.getText().trim();
    }

    public String getApplicationsRegisteredValue() {
    	waitForElementToBeVisible(applicationsRegistered);
        return applicationsRegistered.getText().trim();
    }

    public String getTokenRulesValue() {
        return tokenRules.getText().trim();
    }

    public String getReferencePoliciesValue() {
        return referencePolicies.getText().trim();
    }

    public String getHsmsValue() {
        return hsms.getText().trim();
    }

    public String getKeysValue() {
        return keys.getText().trim();
    }

    public String getReferenceTokensValue() {
        return referenceTokens.getText().trim();
    }

    public static void waitForElementToBeVisible(WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(element));
	}
    
    public void clickOnManagementSummary() {
    	managementSummaryTab.click();
    }

    public String getServerName() {
    	return serverNameElement.getText();
    }
    
    public String getServerTime() {
    	return serverTimeElement.getText();
    }
    
    public String getLastLogin() {
    	return lastLoginElement.getText();
    }
    
    public String getLicenseType() {
    	return licenseTypeElement.getText();
    }
    
    public String getHomeDirectory() {
    	return homeDirectoryElement.getText();
    }
    
    
    
	}

