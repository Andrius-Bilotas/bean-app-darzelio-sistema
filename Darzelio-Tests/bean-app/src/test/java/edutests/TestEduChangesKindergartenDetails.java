package edutests;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import edupages.EduEditKindergartenDetailsPage;
import generictestmethods.GenericTestMethods;
import utilities.WaitUtils;

public class TestEduChangesKindergartenDetails extends GenericTestMethods {
	private EduEditKindergartenDetailsPage editKindergarten = new EduEditKindergartenDetailsPage(driver);
	private String updatedKindergartenName = "UPDATED " + kindergartenName;

	/**
	 * Test scenario: This is a test for successful kindergarten details update by
	 * the edu.
	 * 
	 * Preconditions: Edu must be registered by admin. Edu must be logged in.
	 * Kindergarten must be added in the system
	 * 
	 * 
	 * Steps: All the required fields must be entered. Unique kindergarten name must
	 * be used.
	 * 
	 * Assertion:Checks if updated kindergarten name is visible in the list
	 */

	@Test

	public void successfulKindergartenDetailsEdit() {
		registerEdu();
		login();
		addNewKindergarten();
		eduHomePage.clickButtonKindergartenList();
		List<WebElement> rows = driver.findElements(By.cssSelector("#content > div > div > table > tbody"));

		for (int i = 0; i < rows.size(); i++) {
			String tempName = rows.get(i).findElement(By.cssSelector("td:nth-child(2)")).getText();
			if (tempName.equals(kindergartenName)) {
				rows.get(i).findElement(By.cssSelector("td:nth-child(6) > a")).click();
				break;
			}

		}
		editKindergarten.inputKindergartenName(updatedKindergartenName);
		editKindergarten.inputSpotsInFirstAgeGroup(new Random().nextInt(50));
		editKindergarten.clickButtonRegister();
		WaitUtils.waitAndAcceptAlert(driver);

		List<WebElement> rowstwo = driver.findElements(By.cssSelector("#content > div > div > table > tbody"));
		String name = "";
		for (int i = 0; i < rowstwo.size(); i++) {
			String tempName = rowstwo.get(i).findElement(By.cssSelector("td:nth-child(2)")).getText();
			if (tempName.equals(updatedKindergartenName)) {
				name = tempName;
				break;
			}

		}

		assertEquals("Updated kindergarten name was not found", updatedKindergartenName, name);
	}
}
