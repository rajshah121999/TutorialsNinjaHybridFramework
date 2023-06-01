package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentListeners;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener {
	ExtentReports extentReports;
	ExtentTest extentTest;

	public void onStart(ITestContext context) {
		extentReports = ExtentListeners.generateExtentReport();
		
	}

	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest = extentReports.createTest(testName);
		extentTest.log(Status.INFO, testName + " started executing");
		
	}

	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.PASS, testName + " got successfully executed");

	}

	public void onTestFailure(ITestResult result) {
		String testName = result.getName();

		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		String dest = Utilities.captureScreenshot(driver, result.getName());

		extentTest.addScreenCaptureFromPath(dest);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + " got Failed");

	}

	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		System.out.println(result.getName() + " test got skipped");
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + "  got skipped");
		
	}

	public void onFinish(ITestContext context) {
		System.out.println("Project execution completed");
		extentReports.flush();
		
		// To directly open extent report without manually launcing it
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentreports.html";
		File extentReportFile = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
	}

}
