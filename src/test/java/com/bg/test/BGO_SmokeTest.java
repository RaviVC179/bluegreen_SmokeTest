package com.bg.test;

import java.util.Date;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.bg.pages.VSSA;
import com.bg.util.GenericUtils;

public class BGO_SmokeTest extends GenericUtils {
	
	ExtentHtmlReporter htmlreports;
	ExtentReports extent;
	ExtentTest test;
	VSSA vssa;
	
	@BeforeSuite
	public void beforeSuite(){
		configure();
	}
	
	@BeforeClass
	public void reportSettings(){
		Date date = new Date();
		String filename = System.getProperty("user.dir"+"\\ExtentReports\\BGO_SmokeTestResults"+date.getTime()+".html");
		
		htmlreports = new ExtentHtmlReporter(filename);
		extent = new ExtentReports();
		extent.attachReporter(htmlreports);
		
		htmlreports.config().setReportName("Hybris_Regression Automation Test Results");
		htmlreports.config().setTheme(Theme.STANDARD);
		htmlreports.config().setTestViewChartLocation(ChartLocation.TOP);
		
		extent.setSystemInfo("UserName", "TE328511");
		extent.setSystemInfo("HostName", "CAI-PC");
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("Environment", "Stage");
		extent.setSystemInfo("Selenium", "2.5.3");
		extent.setSystemInfo("Java", "1.8");
	}
	
	@BeforeTest
	public void beforeTest(){
		vssa = PageFactory.initElements(driver, VSSA.class);
	}
	
	@AfterClass
	public void afterClass(){
		extent.flush();
	}
	
	@AfterMethod
	public void reportTearDown(ITestResult result){
		if(result.getStatus()==result.FAILURE){
			test.log(Status.FAIL, result.getName() +  "       "  +  result.getThrowable());
		}
		
		if(result.getStatus()==result.SUCCESS){
			test.log(Status.PASS, result.getName() + " PASSED ");
		}
		
		if(result.getStatus()==result.SKIP){
			test.log(Status.SKIP, result.getName() + " SKIPPED ");
		}
	}
	
	@Test(priority=0)
	public void Launch_BGO(){
		test=extent.createTest("Launch_BGO");
		test.log(Status.INFO, "Test Case Execution started");
		
		//launchApp(CONFIG.getProperty("BG_URL"), "Stage");
		driver.navigate().to("https://www.bluegreenvacations.com/");
		System.out.println("BGO Application launched");
	}

}
