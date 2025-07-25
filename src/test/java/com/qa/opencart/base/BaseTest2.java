package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.factory.DriverFactory2;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.LoginPage2;

public class BaseTest2 {
    WebDriver driver;
	DriverFactory2 df;
	protected Properties prop;


	protected LoginPage2 loginPage2;

	@BeforeTest
	public void setUp() {

		df = new DriverFactory2();
		driver = df.initDriver("chrome");
		prop=df.initProp();
		
		loginPage2 = new LoginPage2(driver);
		

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
