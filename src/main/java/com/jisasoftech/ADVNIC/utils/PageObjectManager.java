package com.jisasoftech.ADVNIC.utils;

import org.openqa.selenium.WebDriver;

import com.jisasoftech.ADVNIC.pages.DashBoardPage;
import com.jisasoftech.ADVNIC.pages.LoginPage;

public class PageObjectManager {
	
	LoginPage login;
	DashBoardPage dash;
	 
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
}
