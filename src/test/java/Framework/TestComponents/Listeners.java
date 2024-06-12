package Framework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Framework.Tests.MainTest;
import Framework.Resources.ExtentReporter;

public class Listeners extends MainTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports reports=ExtentReporter.getReportObject();
	ThreadLocal<ExtentTest> reportTest=new ThreadLocal<ExtentTest>();//thread safe i.e, each test is unique
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test=reports.createTest(result.getMethod().getMethodName());
		reportTest.set(test);//unique thread id and test object it create a map
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		 test.log(Status.PASS, "Test Complete");//It will create a log of it.
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		reportTest.get().fail(result.getThrowable());//This will print the error message.
		//test.log(Status.FAIL, "Test Failed"); 
		//Screenshot\
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = takeScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reportTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		 
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		 
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		 
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
 
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		reports.flush(); 
	}

}
