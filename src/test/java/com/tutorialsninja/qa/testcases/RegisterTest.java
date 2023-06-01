package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	
//	AccountSuccessPage accountSuccessPage;
	
	public RegisterTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndLaunchUrl(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver); 
		homePage.clickOnMyAccount();
		homePage.selectRegisterOption();		
	}

	@Test(priority=1)
	public void verifyRegiterWithMandatoryFields() {
		
		RegisterPage register = new RegisterPage(driver);
		register = new RegisterPage(driver);
		register.enterFirstName(dataProp.getProperty("firstname"));
		register.enterLastName(dataProp.getProperty("lastname"));
		register.enterEmail(Utilities.generateEmail());
		register.enterTelephone(dataProp.getProperty("telephone"));
		register.enterPassword(prop.getProperty("validPassword"));
		register.enterConfirmPassword(prop.getProperty("validPassword"));
		register.clickprivacyPolicyCheckbox();
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		accountSuccessPage = register.clickContinueBtn();
		Assert.assertTrue(accountSuccessPage.successMessageIsDisplayed());
	}
	
	@Test(priority=2)
	public void verifyRegiterWithAllFields() {
		
		RegisterPage register = new RegisterPage(driver);
		register.enterFirstName(dataProp.getProperty("firstname"));
		register.enterLastName(dataProp.getProperty("lastname"));
		register.enterEmail(Utilities.generateEmail());
		register.enterTelephone(dataProp.getProperty("telephone"));
		register.enterPassword(prop.getProperty("validPassword"));
		register.enterConfirmPassword(prop.getProperty("validPassword"));
		register.selectYesRadioOption();
		register.clickprivacyPolicyCheckbox();
		register.clickContinueBtn();	
		
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		Assert.assertTrue(accountSuccessPage.successMessageIsDisplayed());
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
