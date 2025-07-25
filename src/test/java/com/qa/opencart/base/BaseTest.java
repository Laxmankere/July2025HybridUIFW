package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;

	protected LoginPage loginPage;
	protected AccountsPage accPage;

	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {

		df = new DriverFactory();
		prop = df.initProp();
		
		   if (browserName != null) {
	            prop.setProperty("browser", browserName); // setting browser value dynamically
	        }

		driver = df.initDriver(prop);

		loginPage = new LoginPage(driver);
		accPage = new AccountsPage(driver);
		SoftAssert softAssert = new SoftAssert();

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
