package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;


public class SearchTest extends Base{
	
	
	public SearchTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndLaunchUrl(prop.getProperty("browserName"));
			
	}
	
	@Test(priority=1)
	public void verifySearchwithValidProduct() {
		
		HomePage homePage = new HomePage(driver);
		homePage.enterSearchValue(dataProp.getProperty("validSearch"));
		homePage.clickSearchBtn();
		SearchPage searchPage = new SearchPage(driver);
		Assert.assertTrue(searchPage.searchTabIsDisplayed(), "Search page is not opened");
	}
	
	@Test(priority=2)
	public void verifySearchwithInvalidProduct() {
		
		HomePage homePage = new HomePage(driver);
		homePage.enterSearchValue(dataProp.getProperty("invalidSearch"));
		homePage.clickSearchBtn();	
		SearchPage searchPage = new SearchPage(driver);
		Assert.assertEquals(searchPage.noProductMatchMessage(),dataProp.getProperty("expectedMessage"));
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
