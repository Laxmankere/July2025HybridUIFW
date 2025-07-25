package com.qa.opencart.tests;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test
	public void getPageTitleTest() {
		String actualtitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualtitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void loginPageUrlTest() {
		String actualUrl = loginPage.getLoginPageURL();
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	

	@Test(priority = Integer.MAX_VALUE)
	public void loginTest() throws InterruptedException {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String accountPageTitle = loginPage.getAccountPageTitle();
		Thread.sleep(3000);
		Assert.assertEquals(accountPageTitle,AppConstants.ACCOUNTS_PAGE_TITLE);
		
	}
}
