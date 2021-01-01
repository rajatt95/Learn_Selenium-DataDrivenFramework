package com.learning.rough;

import java.util.Date;

public class TestTimeStamp {

	public static void main(String[] args) {
		Date date = new Date();

		String screenshotName = date.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		System.out.println(screenshotName);
	}
}
