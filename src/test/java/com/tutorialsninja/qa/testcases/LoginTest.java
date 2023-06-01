package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndLaunchUrl(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();
	}

	@DataProvider(name = "validCredentialsProvider")
	public Object[][] supplyTestdata() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 1, dataProvider = "validCredentialsProvider")
	public void verifyLoginWithValidCredentials(String email, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickLoginBtn();
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.myAccountHeaderIsDisplayed(), "Login failed");

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmail());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickLoginBtn();		
		Assert.assertEquals(loginPage.alertMessageText(),dataProp.getProperty("expectedWarningMessage"));
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
