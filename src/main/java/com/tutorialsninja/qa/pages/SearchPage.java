package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	
	@FindBy(xpath="//a[text()=\"Search\"]")
	private WebElement searchTab;
	
	@FindBy(xpath="//input[@id=\"button-search\"]/following-sibling::p")
	private WebElement noProductMatchMessage;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public Boolean searchTabIsDisplayed() {
		return searchTab.isDisplayed();
	}
	
	public String noProductMatchMessage() {
		return noProductMatchMessage.getText();
	}
}
