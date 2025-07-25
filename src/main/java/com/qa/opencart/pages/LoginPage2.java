package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class LoginPage2 {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By logo = By.cssSelector("img.img-responsive");

	public  LoginPage2(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. Public Page Actions/Methods
	public String getLoginPageTitle() {
		String title = driver.getTitle();
		System.out.println("login page title: " + title);
		return title;
	}

	public String getLoginPageURL() {
		String url = driver.getCurrentUrl();
		System.out.println("login page URL: " + url);
		return url;
	}
	
    public String getAccountPageTitle() {
        String accountPagetitle = driver.getTitle();
        System.out.println("Account page title : " + accountPagetitle);
        return accountPagetitle;
    }

	public void doLogin(String userName, String pwd) {
		System.out.println("Credential are : " + userName + " : " + pwd);
		eleUtil.doSendKeys(username, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
	}

}
