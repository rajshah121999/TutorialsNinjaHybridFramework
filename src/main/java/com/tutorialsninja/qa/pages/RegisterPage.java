package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameTextField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameTextField;
	
	@FindBy(id="input-email")
	private WebElement emailTextField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneTextField;
	
	@FindBy(id="input-password")
	private WebElement passwordTextField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPasswordTextField;
	
	@FindBy(xpath="//input[@name=\"newsletter\"][@value=\"1\"]")
	private WebElement yesRadioBtn;
	
	@FindBy(xpath="//input[@name=\"newsletter\"][@value=\"0\"]")
	private WebElement noRadioBtn;
	
	@FindBy(xpath="//input[@type=\"checkbox\"]")
	private WebElement privacyPolicyCheckbox;
	
	@FindBy(xpath="//input[@value=\"Continue\"]")
	private WebElement continueButton;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstName) {
		firstNameTextField.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		lastNameTextField.sendKeys(lastName);
	}
	
	public void enterEmail(String email) {
		emailTextField.sendKeys(email);
	}
	
	public void enterTelephone(String telephone) {
		telephoneTextField.sendKeys(telephone);
	}
	
	public void enterPassword(String password) {
		passwordTextField.sendKeys(password);
	}
	
	public void enterConfirmPassword(String confirmPassword) {
		confirmPasswordTextField.sendKeys(confirmPassword);
	}
	
	public void selectYesRadioOption() {
		yesRadioBtn.click();
	}
	
	public void selectNoRadioOption() {
		noRadioBtn.click();
	}
	
	public void clickprivacyPolicyCheckbox() {
		privacyPolicyCheckbox.click();
	}
	
	public AccountSuccessPage clickContinueBtn() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

}
