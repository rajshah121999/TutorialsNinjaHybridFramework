package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int implicit_wait = 10;
	public static final int pageload_wait = 10;
	public static String screenshotName;


	public static String generateEmail() {
		Date date = new Date();
		String email = "raj" + date.toString().replace(" ", "_").replace(":", "-") + "@gmail.com";
		return email;
	}

	
	public static Object[][] getTestDataFromExcel(String sheetName) {

		XSSFWorkbook wb = null;
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjaTestdata.xlsx");
		try {
			FileInputStream fisExcel = new FileInputStream(excelFile);
			wb = new XSSFWorkbook(fisExcel);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = wb.getSheet(sheetName);
		
		int rownum = sheet.getLastRowNum();
		int colnum = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rownum][colnum];
		
		for(int i=0; i<rownum; i++) {
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0;j<colnum;j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING: data[i][j] = cell.getStringCellValue();
				break;
				
				case NUMERIC: data[i][j] = Integer.toString((int)cell.getNumericCellValue());
				break;
				
				case BOOLEAN: data[i][j] = cell.getBooleanCellValue();
				break;
				
				}
				
			}
		}
		
		return data;
	}
	
	public static String captureScreenshot(WebDriver driver, String testName) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty(System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png");
		try {
			FileHandler.copy(src, new File(dest));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dest;
	}

}
