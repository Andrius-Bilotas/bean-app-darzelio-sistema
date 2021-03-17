package edutests;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generictestmethods.GenericTestMethods;
import utilities.WaitUtils;

public class TestEduAddNewKindergarten extends GenericTestMethods {

	/**
	 * Test scenario: This is a test for successful new kindergarten addition by the
	 * admin.
	 * 
	 * Preconditions: Edu must be registered by admin. Edu must be logged in
	 * 
	 * Steps: All the required fields must be entered. Unique kindergarten name must
	 * be used.
	 * 
	 * Assertion: Checks if created kindergarten name is in the list
	 */

	@Test

	public void successfulNewKindergartenAddition() {
		registerEdu();
		login();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Pridėti darželį")));
		eduHomePage.clickButtonAddNewKindergarten();
		addNewKindergarten.inputKindergartenName(kindergartenName); 
		addNewKindergarten.inputKindergartenAddress(kindergartenAddress);
		addNewKindergarten.inputSpotsInFirstAgeGroup(new Random().nextInt(50));
		addNewKindergarten.inputSpotsInSecondAgeGroup(new Random().nextInt(50));
		addNewKindergarten.clickButtonRegister();
		WaitUtils.waitAndAcceptAlert(driver);

		List<WebElement> rows = driver.findElements(By.cssSelector("#content > div > div > table > tbody"));
		String name = "";

		for (int i = 0; i < rows.size(); i++) {
			String tempName = rows.get(i).findElement(By.cssSelector("td:nth-child(2)")).getText();
			if (tempName.equals(kindergartenName)) {
				name = tempName;
				break;
			}
		}
		assertEquals("Kindergarten name was not found", kindergartenName, name);
	}
}
