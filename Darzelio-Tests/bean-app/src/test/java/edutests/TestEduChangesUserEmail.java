package edutests;

import static org.junit.Assert.*;

import org.junit.Test;


import edupages.EduUpdateUserEmailPage;
import edupages.EduUserDetailsPage;
import generictestmethods.GenericTestMethods;
import utilities.WaitUtils;


public class TestEduChangesUserEmail extends GenericTestMethods {


	private EduUserDetailsPage userDetails = new EduUserDetailsPage(driver);
	private EduUpdateUserEmailPage updateUserDetails = new EduUpdateUserEmailPage(driver);


	/**
	 * Test scenario: this is a test of successful user details change.
	 * 
	 * Preconditions: 1. Edu user must be registered by admin 2. Edu user must login
	 * 
	 * Steps: All the required fields must be entered. Updated email is entered.
	 * Assertion: logout and login with the newly updated email
	 */

	@Test

	public void successfulUserEmailChangeByEdu() {

		registerEdu();
		login();
		eduHomePage.clickButtonUserData();
		userDetails.clickButtonChangeEmail();
		updateUserDetails.inputEmail(updated + userEmail);
		updateUserDetails.clickButtonSave();
		WaitUtils.waitAndAcceptAlert(driver);
		loginPage.inputEmail(updated + userEmail);
		loginPage.inputPassword(userName);
		loginPage.clickButtonLogin();
		String successLogInText = eduHomePage.getSuccessText();
		assertTrue("Chart does not contain expected text", successLogInText.contains("Švietimo"));

	}
}
