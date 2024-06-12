package Framework.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
	public static ExtentReports getReportObject()
	{
		//We need to know 2 classes when we are dealing with ExtentReports: 
		//ExtentReports and ExtentSparkReporter
		//ExtentSparkReporter:Is use for configuration as seen below
		String path=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);//it requires the path of the report as argument
		reporter.config().setReportName("Web Automation Report");
		reporter.config().setDocumentTitle("Test Results");
		//ExtentReports: Is the main class
		ExtentReports reports=new ExtentReports();
		reports.attachReporter(reporter); 
		reports.setSystemInfo("Tester", "Ketan Chauhan");
		return reports;
	}

}
