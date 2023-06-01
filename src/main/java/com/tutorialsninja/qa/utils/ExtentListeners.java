package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListeners {

	public static ExtentReports generateExtentReport() {
		
		ExtentReports extent = new ExtentReports();
		File extentReportsFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentreports.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(extentReportsFile);
		
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setReportName("Tutorails Ninja Test Automation Report");
		spark.config().setDocumentTitle("TN Automation report");
		spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extent.attachReporter(spark);
		
		Properties configProp = new Properties();
		File configFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(configFile);
			configProp.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		extent.setSystemInfo("Application URL",configProp.getProperty("url"));
		extent.setSystemInfo("Browser",configProp.getProperty("browserName"));
		extent.setSystemInfo("Operating System",System.getProperty("os.name"));
		extent.setSystemInfo("Username",System.getProperty("username"));
		extent.setSystemInfo("java Version",System.getProperty("java.version"));
		
		return extent;
	}

}
