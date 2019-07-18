package base;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CommonMethods extends DriverInstance {

	WebElement moveMouse;
	WebElement foundElement;
	List<WebElement> listElements;

	public void moveToElements(String moveTo) {

		Actions actions = new Actions(wd);
		moveMouse = catchElement(moveTo);
		waitingWebDriver();
		actions.moveToElement(moveMouse).perform();
	}

	public void type(String locator, String value) {
		foundElement = catchElement(locator);
		foundElement.click();
		foundElement.clear();
		foundElement.sendKeys(value);
		waitingWebDriver();
	}

	public void clickOnElement(String locator) {
		catchElement(locator).click();
//		test.log(LogStatus.INFO, "Clicking on: " + locator);
	}

	public WebElement catchElement(String locator) {

		if (locator.endsWith("_CSS")) {
			foundElement = wd.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			foundElement = wd.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			foundElement = wd.findElement(By.id(OR.getProperty(locator)));
		}
		waitingWebDriver();
		return foundElement;
	}

	public List<WebElement> catchElements(String locator) {

		if (locator.endsWith("_CSS")) {
			listElements = wd.findElements(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			listElements = wd.findElements(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			listElements = wd.findElements(By.id(OR.getProperty(locator)));
		}
		waitingWebDriver();
		return listElements;
	}

	public ArrayList<String> getDataFromDynamicTable(List<WebElement> rowsTable) {

		ArrayList<String> actualData = new ArrayList<>();

		if (rowsTable != null & !rowsTable.isEmpty()) {
			// Loop will execute for all the rows of the table
			for (int row = 1; row <= rowsTable.size(); row++) {

				String rows_XPATH = OR.getProperty("rowsTable_XPATH") + "[" + row + "]/td[1]/span";
				String cellActualDate = wd.findElement(By.xpath(rows_XPATH)).getText();

				if (cellActualDate.equalsIgnoreCase(config.getProperty("expected_Date"))) {
					List<WebElement> colomnsRow = rowsTable.get(row - 1).findElements(By.tagName("td"));

					// Loop will execute till the last cell of that specific row.
					for (WebElement webElement : colomnsRow) {
						actualData.add(webElement.getText());
					}
				}
			}
		}
		return actualData;
	}
}
