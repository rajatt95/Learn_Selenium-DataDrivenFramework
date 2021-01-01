package com.learning.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.learning.base.TestBase;

public class BankManagerLoginTest extends TestBase {

	@Test
	public void bankManagerLoginTest() throws InterruptedException, IOException {

		log.debug("Inside Login Test");

		//1 way of using Soft Assertion concept
		verifyEquals(driver.getTitle(), "Title");

		click("bmlBtn");

		/*
		 * driver.findElement(By.xpath(OR.getProperty("bmlBtn"))).click();
		 */
		/*
		 * System.out.println("OR.getProperty(\"addCustBtn\"): " +
		 * OR.getProperty("addCustBtn")); System.out.
		 * println("isElementPresent(By.xpath(OR.getProperty(\"addCustBtn\"))): " +
		 * isElementPresent(By.xpath(OR.getProperty("addCustBtn"))));
		 */

		Thread.sleep(2000);
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("addCustBtn"))));

		log.debug("Login Test successfully executed");
		Reporter.log("Reporter.log() - Login Test successfully executed");

		Assert.fail("Bank Manager Login failed");

	}
}
