package testcases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import assertions.Validations;
import base.CommonMethods;

public class BoeingRateTest extends CommonMethods {

	public Validations validate = new Validations();
	private static final String ERROR_MESSAGE = "Error! Try over!";

	@Test(priority = 1)
	public void navigateTo_BoeingCompPage_Test() {
		log.debug("Inside Navigate to Boeing Company's Page Test");

		clickOnElement("financeChapter_XPATH");
		type("searchField_CSS", config.getProperty("searchItem"));
		moveToElements("resultSearchList_XPATH");

		if (validate.isElementPresent("select_BoeingComp_XPATH")) {
			clickOnElement("select_BoeingComp_XPATH");
		}

//		variant 1
		Assert.assertFalse(validate.validatePageTitle(config.getProperty("h1_boeing")), ERROR_MESSAGE);
//		variant 2	
		Assert.assertTrue(validate.isElementPresent("h1_boeing_XPATH"), ERROR_MESSAGE);

		log.debug("Navigate to Boeing Company's Page Test successfully executed !!!");
	}

	@Test(priority = 2)
	public void BoeingRate_Test() {
		log.debug("Inside Boeing Rate Test");

		clickOnElement("historicalData_XPATH");
		ArrayList<String> actualData = new ArrayList<>();

		if (validate.isElementPresent("rowsTable_XPATH")) {

			List<WebElement> rowsTable = catchElements("rowsTable_XPATH");
			actualData = getDataFromDynamicTable(rowsTable);
		}
		Assert.assertTrue(validate.compareArrayLists(actualData), ERROR_MESSAGE);
	}
}
