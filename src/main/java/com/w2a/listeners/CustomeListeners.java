package com.w2a.listeners;

import java.io.IOException;

import com.w2a.base.Page;
import com.w2a.utilities.Utilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;


public class CustomeListeners extends Page implements ITestListener{

	public void onTestStart(ITestResult result) {
		
		test = repo.startTest(result.getName().toUpperCase());
		
	}

	public void onTestSuccess(ITestResult result) {
		
		test.log(LogStatus.PASS,result.getName().toUpperCase()+" PASS");
		repo.endTest(test);
		repo.flush();
		
	}

	public void onTestFailure(ITestResult result) {
		
	//ReportNG	
		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		Reporter.log("<br>");
		Reporter.log("Click to see Screenshot");
		Reporter.log("<a target=\"_BLANK\" href="+Utilities.screenshotName+">screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<img src = "+Utilities.screenshotName+"  width=300 height = 300><a target=\"_BLANK\" href="+Utilities.screenshotName+"></a></img>");
		
	
	//ExtentReports
		
		test.log(LogStatus.FAIL,result.getName().toUpperCase()+" FAILED"+" with exception: " + result.getThrowable());
		test.log(LogStatus.FAIL,test.addScreenCapture(Utilities.screenshotName));
		repo.endTest(test);
		repo.flush();
		
		
		
	}

	public void onTestSkipped(ITestResult result) {
		
		test.log(LogStatus.SKIP, "Skipping the test " +result.getName() + "As the Skip test Mode is NO" );
		repo.endTest(test);
		repo.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
