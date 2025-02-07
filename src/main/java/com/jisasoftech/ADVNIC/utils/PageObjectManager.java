package com.jisasoftech.ADVNIC.utils;

import org.openqa.selenium.WebDriver;

import com.jisasoftech.ADVNIC.pages.ApplicationManagerPage;
import com.jisasoftech.ADVNIC.pages.DashBoardPage;
import com.jisasoftech.ADVNIC.pages.LoginPage;

public class PageObjectManager {
	
	LoginPage login;
	DashBoardPage dash;
	ApplicationManagerPage app;
	 
	public WebDriver driver;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public LoginPage getLoginPage() {
		if(login == null) {
			login = new LoginPage(driver);
		}
		
		return login;
	}
	
	public DashBoardPage getDashBoardPage() {
		if(dash == null) {
			dash = new DashBoardPage(driver);
		}
		return dash;
	}
	
	public ApplicationManagerPage getApplicationManagerPage() {
		if(app == null) {
			app = new ApplicationManagerPage(driver);
		}
		return app;
	}
}
