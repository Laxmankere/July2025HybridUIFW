package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.exceptions.BrowserException;

public class DriverFactory3 {
	// we never create static WebDriver in Driverfactory bcz it will problem for
	// parallel execution
	WebDriver driver;
	Properties prop;

	// earlier we pass into initDriver(String browserName) but now we are fetching
	// browser & url from config so given
	// properties object reference to initDriver method
	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println("browser name is : " + browserName);

		OptionsManager optionManager = new OptionsManager(prop);

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver(optionManager.getChromeOptions());
			break;
		case "firefox":
			driver = new FirefoxDriver(optionManager.getFirefoxOptions());
			break;
		case "edge":
			driver = new EdgeDriver(optionManager.getEdgeOptions());
			break;
		default:
			throw new BrowserException("-------Invalid browser ---------: " + browserName);
		}

		driver.get(prop.getProperty("url"));
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
