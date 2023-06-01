package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {

	public WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public Base() {

		// Loading tesdata.properties file
		// ----------------------------------------------
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		try {
			FileInputStream dataFis = new FileInputStream(dataPropFile);
			dataProp.load(dataFis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("testdata.properties loaded");

		
		// Loading config.properties file ----------------------------------------------
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("Config.properties loaded");

	}

	public WebDriver initializeBrowserAndLaunchUrl(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicit_wait));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.pageload_wait));
		driver.get(prop.getProperty("url"));

		return driver;

	}

}
