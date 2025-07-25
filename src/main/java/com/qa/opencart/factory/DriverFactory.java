package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;


import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	// we never create static WebDriver in Driverfactory bcz it will problem for
	// parallel execution
	WebDriver driver;
	Properties prop;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	// earlier we pass into initDriver(String browserName) but now we are fetching
	// browser & url from config so given
	// properties object reference to initDriver method
	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println("browser name is : " + browserName);

		OptionsManager optionsManager = new OptionsManager(prop);

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			// driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("----------Invalid Browser-----------"  + browserName + " is invalid");
			throw new BrowserException("-----------Invalid browser----------------");
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	/**
	 * this method is returning the driver with threadlocal
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to init the properties from config file
	 * 
	 * @return
	 */
	// mvn clean install -Denv="qa"
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
		System.out.println("running tests on env: " + envName);

		try {
			if (envName == null) {
				System.out.println("env is null....hence running tests on QA env");
				ip = new FileInputStream("src/test/resources/config/qa.config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("plz pass the right env name..." + envName);
					throw new FrameworkException("INVALID ENV NAME");
				}
			}
			prop.load(ip);

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return prop;
	}

	public static String getScreenshot(String methodName) {
		TakesScreenshot ts = (TakesScreenshot) getDriver();
		File srcFile = ts.getScreenshotAs(OutputType.FILE);//temp dir
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()+ ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
