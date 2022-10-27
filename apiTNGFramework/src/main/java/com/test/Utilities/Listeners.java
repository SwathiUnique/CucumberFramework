package com.test.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.mongodb.MapReduceCommand.OutputType;

public class Listeners implements ITestListener{
	
	public static TekExtentReports report = null;
	
	public void onStart(ITestContext context) {
		report = TekExtentReports.getInstance();
		report.startExtentReport();
	}	
	public void onTestStart(ITestResult tr) {
		report.startSingleTestReport(tr.getMethod().getMethodName());
		report.logTestInfo(tr.getMethod().getMethodName()+" started");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		report.logTestPassed(tr.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		report.logTestFailed(tr.getMethod().getMethodName());
		
		/*File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);//BASE64 directly attaches the image in the report
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");  //FILE option image file of scrshot is attached to report
		Date date = new Date();
		String actualDate = format.format(date);
		String screenshotPath = System.getProperty("user.dir")+"/myReports/Screenshots/"+actualDate+".jpeg";
		File dest = new File(screenshotPath);
		
		try {
			projectUtilities.copyFile(src,dest);
		}
		catch(IOException e){
			e.getStackTrace();
		}*/
		
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		report.logTestSkipped(tr.getMethod().getMethodName());
	}
	public void onFinish(ITestContext context) {
		report.endReport();
	}
	
	/*@Override
	protected ITestNGMethod[] getAllTestMethods() {
		// TODO Auto-generated method stub
		return super.getAllTestMethods();
	}*/
}
