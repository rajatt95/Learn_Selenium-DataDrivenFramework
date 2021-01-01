package com.learning.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.learning.base.TestBase;
import com.learning.utilities.TestUtil;

public class AddCustomerTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	/*
	 * public void addCustomerTest(String firstName, String lastName, String
	 * postCode, String runMode)
	 */public void addCustomerTest(Hashtable<String, String> data) throws InterruptedException {

		// RunMode - Column name in the sheet
		if (!data.get("RunMode").equals("Y")) {
			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}

		/*
		 * if (!runMode.equalsIgnoreCase("Y")) { throw new
		 * SkipException("Skipping the test case as the run mode for data set is No"); }
		 */
		click("addCustBtn");
		Thread.sleep(1000);

		// firstName, lastName, postCode - Columns in Excel sheet

		type("firstname", data.get("firstName"));
		type("lastname", data.get("lastName"));
		type("postcode", data.get("postCode"));

		/*
		 * type("firstname", firstName); type("lastname", lastName); type("postcode",
		 * postCode);
		 */click("addBtn");

		/*
		 * driver.findElement(By.xpath(OR.getProperty("addCustBtn"))).click();
		 * Thread.sleep(1000);
		 * driver.findElement(By.xpath(OR.getProperty("firstname"))).sendKeys(firstName)
		 * ;
		 * driver.findElement(By.xpath(OR.getProperty("lastname"))).sendKeys(lastName);
		 * driver.findElement(By.xpath(OR.getProperty("postcode"))).sendKeys(postCode);
		 * driver.findElement(By.xpath(OR.getProperty("addBtn"))).click();
		 */
		Thread.sleep(1000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains("Customer added successfully"), "Customer is not added");
		alert.accept();
		Thread.sleep(1000);

	}

}
