package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value=\"Login\"]")
	private WebElement LoginBtn;

	@FindBy(xpath="//div[@class=\"alert alert-danger alert-dismissible\"]")
	private WebElement alertMessage;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterEmailAddress(String emailText) {
		emailField.sendKeys(emailText);
	}
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	public void clickLoginBtn() {
		LoginBtn.click();
	}

	public String alertMessageText() {
		return alertMessage.getText();
	}
}
