package parenttests;

import java.util.List;

import org.junit.Test;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;


import generictestmethods.GenericTestMethods;
import parentpages.ParentChangeChildDetailsPage;
import parentpages.ParentHomePage;
import utilities.WaitUtils;

public class TestParentDeleteChild extends GenericTestMethods {
	private ParentHomePage parentHomePage = new ParentHomePage(driver);
	private ParentChangeChildDetailsPage updateChildDetails = new ParentChangeChildDetailsPage(driver);

	/**
	 * Test scenario:
	 * 
	 * Preconditions: 1. Parent user must be registered by admin 2. At least 1
	 * kindergarten must be registered by EDU 3. Parent user must login 4. Parent
	 * user must add parent details 5. Parent user must add child details
	 *
	 * Steps: 1. Go to edit details of the selected child. 2. Click Delete
	 * 
	 * Assertion:
	 */
	@Test

	public void successfulChildDeletion() {

		registerParent();
		login();
		addParentDetails();
		addChildDetails();
		parentHomePage.clickButtonEditChildDetails();

		List<WebElement> rows = driver.findElements(By.cssSelector("#content > div > div > div:nth-child(2) > table > tbody > tr"));

		for (int i = 0; i < rows.size(); i++) {
			String tempName = rows.get(i).findElement(By.cssSelector("td:nth-child(2)")).getText();
			if (tempName.equals(childName)) {
				rows.get(i).findElement(By.cssSelector("td:nth-child(4) > a")).click();
				break;
			}
		}
		updateChildDetails.clickButtonDeleteChildData();
		driver.findElement(By.cssSelector("div > div > div > div> div:nth-child(3)> button.btn.btn-danger")).click();
		WaitUtils.waitAndAcceptAlert(driver);
		driver.navigate().refresh();

	}

}
