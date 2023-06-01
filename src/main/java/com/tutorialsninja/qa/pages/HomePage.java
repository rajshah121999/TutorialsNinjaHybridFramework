package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(xpath="//span[text()=\"My Account\"]")
	private WebElement myAccountDropDown;

	@FindBy(xpath="//a[text()=\"Login\"]")
	private WebElement loginOption;
	
	@FindBy(xpath="//a[text()=\"Register\"]")
	private WebElement registerOption;
	
	@FindBy(name="search")
	private WebElement searchTextField;
	
	@FindBy(xpath="//button[@class=\"btn btn-default btn-lg\"]")
	private WebElement searchBtn;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void clickOnMyAccount() {
		myAccountDropDown.click();
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public void selectRegisterOption() {
		registerOption.click();
	}
	
	public void enterSearchValue(String value) {
		searchTextField.sendKeys(value);
	}
	
	public void clickSearchBtn() {
		searchBtn.click();
	}
}
