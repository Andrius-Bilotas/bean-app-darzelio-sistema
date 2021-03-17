package edutests;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import generictestmethods.GenericTestMethods;
import utilities.WaitUtils;

public class TestEduDeleteKindergarten extends GenericTestMethods {

	/**
	 * Test scenario: this is a test of successful kindergarten deletion by edu.
	 * 
	 * Preconditions: Edu must be registered by admin. Edu must be logged in.
	 * Kindergarten must be added in the system
	 * 
	 * Steps: Confirm the deletion of the selected kindergarten.
	 * 
	 * Assertion:
	 */

	@Test

	public void successfulKindergartenDeletion() {

		registerEdu();
		login();
		addNewKindergarten();
		List<WebElement> rows = driver.findElements(By.cssSelector("#content > div > div > table > tbody"));

		for (int i = 0; i < rows.size(); i++) {
			String tempName = rows.get(i).findElement(By.cssSelector("td:nth-child(2)")).getText();
			if (tempName.equals(kindergartenName)) {
				rows.get(i).findElement(By.cssSelector("td:nth-child(7)")).click();
				break;
			}
		}
		for (int i = 0; i < rows.size(); i++) {
			String tempNametwo = rows.get(i).findElement(By.cssSelector("td:nth-child(8) > div > div > div > div > h5"))
					.getText();
			if (tempNametwo.equals(kindergartenName)) {
				rows.get(i).findElement(
						By.cssSelector("td:nth-child(8) > div > div > div > div:nth-child(3)> button.btn.btn-danger"))
						.click();
				break;
			}
		}
		WaitUtils.waitAndAcceptAlert(driver);
		// truksta asserto
	}
}
