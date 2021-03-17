package parenttests;

import java.util.Random;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import generictestmethods.GenericTestMethods;
import parentpages.ParentHomePage;
import utilities.WaitUtils;

public class TestParentAddChildDetails extends GenericTestMethods {

	private ParentHomePage parentHomePage = new ParentHomePage(driver);

	/**
	 * Test scenario:
	 * 
	 * Preconditions:
	 * 1. Parent user must be registered by admin
	 * 2. Parent user must login
	 * 3. Parent user must add parent details
	 * 
	 * Steps:
	 * All the required fields must be entered to successfully register a child.
	 * Person number must be unique.
	 * 
	 * Assertion: after successful registration user should see a button 'Continue"
	 */
	
	@Test

	public void successfulChildDetailsSubmitionByParent() {
		registerParent();
		login();
		addParentDetails();
		WebElement element = parentHomePage.buttonChildForm;
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
		
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder(5);
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String randomEnding = sb.toString();
		childName = childName + randomEnding;

		parentHomePage.clickButtonChildForm();
		addChildInfo.inputChildFirstName(childName);
		addChildInfo.inputChildLastName(childName);
		addChildInfo.inputChildPersonCode(String.format("59%09d", new Random().nextInt(10000000)));
		addChildInfo.inputChildCity(randomText);
		addChildInfo.inputChildStreet(randomText);
		addChildInfo.inputChildHouseNumber(new Random().nextInt(100) + 1);
		addChildInfo.inputChildFlatNumber(new Random().nextInt(50) + 1);
		addChildInfo.checkAdopted();
		addChildInfo.checkAddSecondParent();
		addChildInfo.inputSecondParentFirstName(parentName);
		addChildInfo.inputSecondParentLastName(parentName);
		addChildInfo.inputSecondParentEmail(userEmail);
		addChildInfo.inputSecondParentPhone(phoneNumber);
		addChildInfo.inputSecondParentPersonCode(String.format("49%09d", new Random().nextInt(10000000)));
		addChildInfo.inputSecondParentCity(randomText);
		addChildInfo.inputSecondParentStreet(randomText);
		addChildInfo.inputSecondParentHouseNumber(new Random().nextInt(100) + 1);
		addChildInfo.inputSecondParentFlatNumber(new Random().nextInt(50) + 1);
		addChildInfo.inputSecondParentNumberOfKids(new Random().nextInt(10) + 1);
		addChildInfo.checkSecondParentStudying();
		addChildInfo.inputSecondParentStudyingInstitution(randomText);
		addChildInfo.checkSecondParentHasDisability();
		addChildInfo.checkSecondParentDeclaredResidenceSameAsLiving();
		addChildInfo.clickButtonSaveAndContinue();
		WaitUtils.waitAndAcceptAlert(driver);

		String successText = driver.findElement(By.xpath("//*[@class=\"btn next\"]")).getText();
		assertTrue("Chart does not contain expected text", successText.contains("Pridėti"));

	}
}