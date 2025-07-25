package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccountsPageTitle() {
        String title = AppConstants.ACCOUNTS_PAGE_TITLE;
        System.out.println("Accounts page title: " + title);
        return title;
    }

	public boolean isLogoutLinkExist() {
		return eleUtil.isElementDisplayed(logoutLink);
	}

	public int getTotalAccountsPageHeader() {
		return eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
	}

	public List<String> getAccPageHeaders() {
		List<WebElement> headersList = eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> headersValueList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String header = e.getText();
			headersValueList.add(header);
		}
		return headersValueList;
	}

	public void doSearch(String searchKey) {
		eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		eleUtil.doClick(searchIcon);
	}
}
