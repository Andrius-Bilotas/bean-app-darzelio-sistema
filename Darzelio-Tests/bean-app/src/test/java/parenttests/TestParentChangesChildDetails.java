package parenttests;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import generictestmethods.GenericTestMethods;
import parentpages.ParentChangeChildDetailsPage;
import utilities.WaitUtils;
import static org.junit.Assert.*;

public class TestParentChangesChildDetails extends GenericTestMethods {

	private ParentChangeChildDetailsPage updateChildDetails = new ParentChangeChildDetailsPage(driver);

	/**
	 * Test scenario:
	 * 
	 * Preconditions: 1. Parent user must be registered by admin 2. Parent user must
	 * login 3. Parent user must add parent details 4. Parent user must add child
	 * details
	 * 
	 * Steps: All the required fields must be entered. Person number must be unique.
	 * Assertion: After submittion the change, check if updated name exists in the list.
	 */
	@Test

	public void successfulChildDetailsUpdateByParent() {

		registerParent();
		login();
		addParentDetails();
		addChildDetails();
		parentHomePage.clickButtonEditChildDetails();

		List<WebElement> rows = driver
				.findElements(By.cssSelector("#content > div > div > div:nth-child(2) > table > tbody > tr"));

		for (int i = 0; i < rows.size(); i++) {
			String tempName = rows.get(i).findElement(By.cssSelector("td:nth-child(2)")).getText();
			if (tempName.equals(childName)) {
				rows.get(i).findElement(By.cssSelector("td:nth-child(4) > a")).click();
				break;
			}
		}
		updateChildDetails.inputChildFirstName(childName + updated);
		updateChildDetails.inputChildCity("Vilnius");
		updateChildDetails.inputChildStreet("Kauno");
		updateChildDetails.inputChildHouseNumber(23);
		updateChildDetails.inputChildBirthDate("1999-03-01");
		updateChildDetails.checkSecondParentStudying();
		updateChildDetails.clickButtonUpdate();
		WaitUtils.waitAndAcceptAlert(driver);
		parentHomePage.clickButtonEditChildDetails();

		List<WebElement> rowstwo = driver
				.findElements(By.cssSelector("#content > div > div > div:nth-child(2) > table > tbody > tr"));
		for (int i = 0; i < rowstwo.size(); i++) {
			String tempName = rowstwo.get(i).findElement(By.cssSelector("td:nth-child(2)")).getText();
			if (tempName.equals(childName + updated)) {
				String name = tempName;
				System.out.println(name);
				assertEquals("Name was not found", childName + updated, name);
				break;
			}
		}
		
	
	}
}
