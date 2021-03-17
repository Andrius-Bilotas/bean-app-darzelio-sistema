package parenttests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


import generictestmethods.GenericTestMethods;

import parentpages.ParentUpdateUserEmailPage;
import parentpages.ParentUserDetailsPage;
import utilities.WaitUtils;

public class TestParentChangesUserEmail extends GenericTestMethods {
	private ParentUserDetailsPage userDetails = new ParentUserDetailsPage(driver);
	private ParentUpdateUserEmailPage updateUserDetails = new ParentUpdateUserEmailPage(driver);


	/**
	 * Test scenario:this is a test of successful user details change.
	 * 
	 * Preconditions: 1. Parent user must be registered by admin 2. Parent user must
	 * login
	 * 
	 * Steps: All the required fields must be entered. Updated email is entered.
	 * Assertion: logout and login with the newly updated email
	 */
	@Test

	public void successfulUserDetailsChange() {

		registerParent();
		login();
		parentHomePage.clickButtonUserData();
		userDetails.clickButtonChangeEmail();
		updateUserDetails.inputEmail(updated + userEmail);
		updateUserDetails.clickButtonSave();
		WaitUtils.waitAndAcceptAlert(driver);
		loginPage.inputEmail(updated + userEmail);
		loginPage.inputPassword(userName);
		loginPage.clickButtonLogin();
		String successLogInText = parentHomePage.getSuccessText();
		assertTrue("Chart does not contain expected text", successLogInText.contains("Tėvas"));

	}
}
