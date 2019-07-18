package assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import base.CommonMethods;
import base.DriverInstance;

public class Validations extends DriverInstance {

	CommonMethods comMeth = new CommonMethods();

	public boolean isElementPresent(String locator) {
		try {
			comMeth.catchElement(locator);
			waitingWebDriver();
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("Element NOT presented :-(");
			return false;
		}
	}

	public boolean validatePageTitle(String expTitle) {
		boolean result = false;
		if (wd.getTitle().equalsIgnoreCase(expTitle))
			result = true;
		return result;
	}

	public boolean compareArrayLists(ArrayList<String> actualData) {

		boolean result = false;

		if (actualData != null & !actualData.isEmpty()) {

			String date = config.getProperty("expected_Date");
			String open = config.getProperty("Open_colomn");
			String high = config.getProperty("High_colomn");
			String low = config.getProperty("Low_colomn");
			String close = config.getProperty("Close_colomn");
			String adj_Close = config.getProperty("Adj_Close_colomn");
			String volume = config.getProperty("Volume_colomn");

			ArrayList<String> expectedData = new ArrayList<>(
					Arrays.asList(date, open, high, low, close, adj_Close, volume));

			if (actualData.size() == expectedData.size() & actualData.equals(actualData)) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}

//	not used in cases
	public boolean validateTextPresentedOnCell(String locator, String expValue) {

		boolean result = false;
		String actualValue = "";
		try {
			comMeth.catchElement(locator).getText();
			waitingWebDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (expValue.equalsIgnoreCase(actualValue)) {
			result = true;
		}
		return result;
	}
}
