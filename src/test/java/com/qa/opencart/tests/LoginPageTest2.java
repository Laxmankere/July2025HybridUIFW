package com.qa.opencart.tests;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.base.BaseTest2;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest2 extends BaseTest2 {

	@Test
	public void getPageTitleTest() {
		String actualtitle = loginPage2.getLoginPageTitle();
		Assert.assertEquals(actualtitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void loginPageUrlTest() {
		String actualUrl = loginPage2.getLoginPageURL();
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	

	@Test(priority = Integer.MAX_VALUE)
	public void loginTest() throws InterruptedException {
		loginPage2.doLogin("laxmankere@gmail.com", "Test@1234");
		String accountPageTitle = loginPage2.getAccountPageTitle();
		Thread.sleep(3000);
		Assert.assertEquals(accountPageTitle,AppConstants.ACCOUNTS_PAGE_TITLE);
		
	}
}
