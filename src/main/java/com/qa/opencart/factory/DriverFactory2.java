package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import net.bytebuddy.asm.Advice.Return;

public class DriverFactory2 {
	// we never create static WebDriver in Driverfactory bcz it will problem for parallel execution
	WebDriver driver;
	Properties prop;

	public WebDriver initDriver(String browserName) {

		System.out.println("browser name is : " + browserName);

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Please pass the right browser : " + browserName);
		}

		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		return driver;

	}

	public Properties initProp() {
		 prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

}
