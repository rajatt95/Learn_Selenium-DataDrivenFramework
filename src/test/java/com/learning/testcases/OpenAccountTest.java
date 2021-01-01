package com.learning.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.learning.base.TestBase;
import com.learning.utilities.TestUtil;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	// public void openAccountTest(String customer, String currency) throws
	// InterruptedException {
	public void openAccountTest(Hashtable<String, String> data) throws InterruptedException {

		// RunMode - Column name in the sheet
		if (!data.get("RunMode").equals("Y")) {
			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		click("openaccount_xpath");
		Thread.sleep(1000);

		// customer, currency - Columns in the Excel sheet

		select("customer_css", data.get("customer"));
		select("currency_css", data.get("currency"));
		click("process_xpath");

		Thread.sleep(1000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains("Account created successfully with account Number"),
				"Customer account is not created");
		alert.accept();
		Thread.sleep(1000);

	}

}
