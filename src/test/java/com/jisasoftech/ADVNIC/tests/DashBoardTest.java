package com.jisasoftech.ADVNIC.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jisasoftech.ADVNIC.pages.DashBoardPage;
import com.jisasoftech.ADVNIC.pages.LoginPage;

public class DashBoardTest extends BaseTest {
	DashBoardPage dash;
	LoginPage lp;

	@BeforeMethod
	public void initialSetUp() throws InterruptedException{
		lp = pageObjectManager.getLoginPage();
		lp.login();
		dash = pageObjectManager.getDashBoardPage();	
	}
	
	
  @Test
  public void validateAfterSucessfullLoginDashboardPageAppearsByDefault() throws InterruptedException{
	  assertTrue(driver.getCurrentUrl().contains("Dashboard"), "login didn't redirect to dashboardpage");
	  Thread.sleep(4000);
  }
  
  @Test
  public void verifyDashBoardTitle() throws InterruptedException{
	 String actualTitle = dash.getTitleOfDashBoardPage();
	 String expectedTitle = "Dashboard - SecureVault Admin Portal";
	 assertEquals(actualTitle, expectedTitle);
	 Thread.sleep(2000);
  }
  
  @Test
  public void verifyDashBoardElementsDisplayed() throws InterruptedException {
	  assertTrue(dash.footerIsDisplayed(), "footer not displayed");
	  assertTrue(dash.sidebarIsDisplayed(), "sidebar is not displayed");
	  assertTrue(dash.pageCenterIsDisplayed(), "page center area not displayed");
	  Thread.sleep(2000);
  }
  
  @Test
  public void validateHamburgerMenuToggle() {
      boolean finalState = dash.toggleAndVerifyMenu();
      System.out.println("Final state of the menu: " + (finalState ? "Expanded" : "Minimized"));
  }

  
  
  
  @Test
  public void verifyDashboardElements() throws InterruptedException{
	  String actualHeaderText = dash.getHeaderText();
	  String expectedHeaderText = "Dashboard";
	  assertEquals(actualHeaderText, expectedHeaderText, "Header text does not match");
	  
	  // verify widget values
	  Map<String, String> expectedValues = new HashMap<>();
	  expectedValues.put("Users Registered", "258");
	  expectedValues.put("Application Registered", "220");
	  expectedValues.put("Token Rules", "174");
	  expectedValues.put("Reference Policies", "150");
	  expectedValues.put("HSMs", "1");
	  expectedValues.put("Keys", "291");
	  expectedValues.put("Reference Tokens", "0");
	  for(Map.Entry<String, String> entry:expectedValues.entrySet()) {
		  assertTrue(DashBoardPage.verifyWidgetText(driver, entry.getKey(), entry.getValue()), 
				    "Mismatch in widget value for: " + entry.getKey());

	  }
	  Thread.sleep(2000);
  }
  
  @Test
  public void validateSignOut() {
	  dash.clickOnProfile();
	  dash.clickOnSignoutLink();
	  assertTrue(driver.getCurrentUrl().contains("Adv"), "user not able to logout");
  }
  
  @Test
  public void validationOfLinks() {
	  boolean isValid = dash.verifyLinks();
	  assertTrue(isValid, "link is not redirecting to appropriate website");
  }
  
  @Test
  public void validateDropdownToggle() {
      // Call the method to verify dropdown functionality
      boolean isDropdownTogglingCorrectly = dash.verifyDropdownToggle(driver);
      // Assert dropdown toggling behavior
      assertTrue(isDropdownTogglingCorrectly, "Dropdown menu did not toggle correctly.");
  }
  
  @Test
  public void testDropdownStatistics() {
      String[] filters = {"All", "Last day", "Last 7 days", "Last 30 days"};

      for (String filterOption : filters) {
          dash.selectDropdownOption(filterOption);

          // Wait for the page to update
          try {
              Thread.sleep(2000); // Temporary wait
          } catch (InterruptedException e) {
              e.printStackTrace();
          }

          // Verify each statistic
          assert dash.getUsersRegisteredValue().equals(dash.getExpectedUsers(filterOption))
              : "Mismatch in Users Registered value for filter: " + filterOption;

          assert dash.getApplicationsRegisteredValue().equals(dash.getExpectedApplications(filterOption))
              : "Mismatch in Applications Registered value for filter: " + filterOption;

          assert dash.getTokenRulesValue().equals(dash.getExpectedTokenRules(filterOption))
              : "Mismatch in Token Rules value for filter: " + filterOption;

          assert dash.getReferencePoliciesValue().equals(dash.getExpectedReferencePolicies(filterOption))
              : "Mismatch in Reference Policies value for filter: " + filterOption;

          assert dash.getHsmsValue().equals(dash.getExpectedHSMValue(filterOption))
              : "Mismatch in HSMs value for filter: " + filterOption;

          assert dash.getKeysValue().equals(dash.getExpectedKeys(filterOption))
              : "Mismatch in Keys value for filter: " + filterOption;

          assert dash.getReferenceTokensValue().equals(dash.getExpectedReferenaceTokenValues(filterOption))
              : "Mismatch in Reference Tokens value for filter: " + filterOption;

          System.out.println("All statistics verified successfully for filter: " + filterOption);
      }
  }
  
  @Test
  public void verifyServerDetails() {
	  dash.clickOnManagementSummary();
	  String actualServerName = dash.getServerName();
      String actualServerTime = dash.getServerTime();
      String actualLastLogin =  dash.getLastLogin();
      String actualLicenseType = dash.getLicenseType();
      String actualHomeDirectory = dash.getHomeDirectory();
      
      String expectedServerName = "localhost";
      String expectedLicenseType = "Development";
      String expectedHomeDirectory = "/var/lib/deployments/adv/advadminportal/portal/";
      // getting current time and date
      LocalDateTime now = LocalDateTime.now();
      String expectedServerTime = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a"));
      String expectedLastLogin = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a"));
      
      String todayDate = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
      assertTrue(actualServerTime.contains(todayDate), "Server time date is incorrect");
      assertTrue(actualLastLogin.contains(todayDate),"last login date is incorrect");
      
     assertEquals(actualServerName, expectedServerName, "server name does not match");
     assertEquals(actualLicenseType, expectedLicenseType, "Licene Type does not match");
     assertEquals(actualHomeDirectory, expectedHomeDirectory, "Home directory path does not match");
  
  }


  
}
