package com.learning.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.learning.base.TestBase;
import com.learning.utilities.MonitoringMail;
import com.learning.utilities.TestConfig;
import com.learning.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestBase implements ITestListener, ISuiteListener {

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult arg0) {

		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}

		test.log(LogStatus.FAIL, arg0.getName().toUpperCase() + " - FAIL and the exception : " + arg0.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		Reporter.log("Capturing Screenshot.......");
		// Setting property for ReportNG report
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		// Reporter.log("<a href=\"d:\\error_toSend.jpg\">Screenshot</a>");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot in new Window</a>");
		Reporter.log("<br>");
		// Adding Thumbnail in the report
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img height=200 width=200 src="
				+ TestUtil.screenshotName + ">");

		rep.endTest(test);
		rep.flush();

	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

		test.log(LogStatus.SKIP, arg0.getName().toUpperCase() + " - SKIP");
		rep.endTest(test);
		rep.flush();

	}

	public void onTestStart(ITestResult arg0) {
		String testCaseName = arg0.getName();
		test = rep.startTest(testCaseName.toUpperCase());

		// Run modes - Y
		if (!TestUtil.isTestRunnable(testCaseName, excel)) {
			throw new SkipException("Skipping the test : " + testCaseName.toUpperCase() + " - as it is marked as No");
		}

	}

	public void onTestSuccess(ITestResult arg0) {

		test.log(LogStatus.PASS, arg0.getName().toUpperCase() + " - PASS");
		rep.endTest(test);
		rep.flush();
	}

	public void onFinish(ISuite arg0) {

		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		MonitoringMail mail = new MonitoringMail();
		String messageBody = null;
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/DataDrivenLiveProject/Extent_Reports/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(messageBody);

		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
