package com.test.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.test.constants.SourcePaths;


public class TekExtentReports {


	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static TekExtentReports object;
	
	public TekExtentReports () {
		
	}
	public static TekExtentReports getInstance() {
		if(object==null)
			object = new TekExtentReports();
		return object;
	}
	public void startExtentReport() {
		
		htmlReporter = new ExtentHtmlReporter(SourcePaths.GENERATE_EXTENTREPORT_PATH);
		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("Tekarch API Automation Scripts");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Tekarch API");
		extent.setSystemInfo("Environment", "End to End Automation Testing");
		extent.setSystemInfo("User Name", "Swathi");	
	}
	public void startSingleTestReport(String testName) {
		logger = extent.createTest(testName);
	}
	public void logTestInfo(String message) {
		logger.log(Status.INFO,message);
	}
	public void logTestPassed(String testcaseName) {
		logger.log(Status.PASS, MarkupHelper.createLabel(testcaseName + " is passed",ExtentColor.GREEN ));
	}
	public void logTestFailed(String testcaseName) {
		logger.log(Status.FAIL, MarkupHelper.createLabel(testcaseName + " is failed",ExtentColor.RED));
	}
	public void logTestfailedwithException(Exception e) {
		logger.log(Status.ERROR, e);
	}
	public void logTestSkipped(String testcaseName) {
		logger.log(Status.SKIP, MarkupHelper.createLabel(testcaseName+ " is skipped", ExtentColor.YELLOW));
	}
	public void endReport(){
		extent.flush();
	}
}
