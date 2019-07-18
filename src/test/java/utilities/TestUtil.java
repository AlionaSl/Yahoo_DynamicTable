package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.DriverInstance;

public class TestUtil extends DriverInstance {

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);

		Date date = new Date();
		screenshotName = "error_" + date.toString().replace(":", "_").replaceAll(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));
	}

}
