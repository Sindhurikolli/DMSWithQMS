package com.pss.dms.Listerners;

import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.pss.dms.login.QMSLoginDetails;

public class TestListener extends QMSLoginDetails implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
//		System.out.println("Test Name - " +result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String className = result.getInstanceName();
		System.out.println("Successfully executed Class - " + className);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		// screenshot code
		// response if API is failed

		System.out.println("Failed Test Case - " + result.getInstanceName());
//		System.out.println("Failed Test Method - " +result.getMethod());
		if (null != result.getThrowable()) {
//		      String msg = result.getThrowable().getMessage();
//		      System.out.println(msg);
			result.getThrowable().printStackTrace();
		}
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot screenshot = (TakesScreenshot) driver;
				File src = screenshot.getScreenshotAs(OutputType.FILE);
				String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
				FileUtils.copyFile(src, new File(".\\" + "/FailedSS/" + result.getName() + timeStamp + ".png"));
				System.out.println("Successfully captured a screenshot");
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Skipped Test - " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("onTestFailedButWithinSuccessPercentage - " + result.getName());
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
//		System.out.println("onStart - " +context.getName());

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
//		System.out.println("onFinish - " +context.getName());	
	}

}
