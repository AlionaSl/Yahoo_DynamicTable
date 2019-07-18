package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import base.DriverInstance;
import utilities.TestUtil;

public class CustomListeners extends DriverInstance implements ITestListener {

	public void onTestStart(ITestResult result) {
		test = extReports.startTest(result.getName().toUpperCase());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, result.getName().toUpperCase() + " PASS");
		extReports.endTest(test);
		extReports.flush();
	}
	
	public void onTestFailure(ITestResult result) {
//		add screenshot to test-output/html/index.html
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		test.log(LogStatus.FAIL, result.getName().toUpperCase() + " FAILED with exception: " + result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		Reporter.log("Click to see Screenshot");
		Reporter.log("Login successfully executed!");

//		add screenshot to test-output/emailable-report.html
		Reporter.log("<a href=" + TestUtil.screenshotName + ">Screenshot</a>");
//		open screenshot in a new tab	
		Reporter.log("<a target='_blank' href=" + TestUtil.screenshotName + ">Screenshot</a>");
//		display screenshot in the report page
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target='_blank' href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
				+ " height=400 width=400></img></a>");

		extReports.endTest(test);
		extReports.flush();

	}
	
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

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
