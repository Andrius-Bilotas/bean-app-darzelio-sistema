package parenttests;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import generictestmethods.GenericTestMethods;
import parentpages.ParentAddChildDetailsPage;
import utilities.WaitUtils;

public class TestParentAddParentDetails extends GenericTestMethods {
	private ParentAddChildDetailsPage addChildInfoPage = new ParentAddChildDetailsPage(driver);
	
	/**
	 * Test scenario:
	 * 
	 * Preconditions:
	 * 1. Parent user must be registered by admin
	 * 2. Parent  must login
	 * 
	 * Steps:
	 * All the required fields must be entered.
	 * Person number must be unique.
	 * 
	 * Assertion: after successful registration user is redirected to Vaiku Registracija
	 */
	@Test

	public void successfulParentDetailsSubmitionByParent() {

		registerParent();
		login();
		parentHomePage.clickButtonParentForm();
		addParentInfo.inputFirstName(parentName);
		addParentInfo.inputLastName(parentName);
		addParentInfo.inputEmail(userEmail);
		addParentInfo.inputPhone(phoneNumber);
		addParentInfo.inputPersonalCode(String.format("49%09d", new Random().nextInt(10000000)));
		addParentInfo.inputCity(randomText);
		addParentInfo.inputStreet(randomText);
		addParentInfo.inputHouseNumber(new Random().nextInt(100) + 1);
		addParentInfo.inputFlatNumber(new Random().nextInt(50) + 1);
		addParentInfo.inputNumberOfKids(new Random().nextInt(10) + 1);
		addParentInfo.checkStudying();
		addParentInfo.inputStudyingInstitution(randomText);
		addParentInfo.checkHasaDisability();
		addParentInfo.checkAgreeWithConditions();
		addParentInfo.checkAgreeWithRules();
		addParentInfo.checkDeclaredResidenceSameAsLiving();
		addParentInfo.clickButtonContinue();
		WaitUtils.waitAndAcceptAlert(driver);
		String successLogInText = addChildInfoPage.getSuccessText();
		assertTrue("Chart does not contain expected text", successLogInText.contains("Vaiko duomenų registracija"));

	}
}
