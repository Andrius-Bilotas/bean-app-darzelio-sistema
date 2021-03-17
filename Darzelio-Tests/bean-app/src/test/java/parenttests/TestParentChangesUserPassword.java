package parenttests;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import generictestmethods.GenericTestMethods;

import parentpages.ParentChangePasswordPage;
import parentpages.ParentUserDetailsPage;
import utilities.WaitUtils;

public class TestParentChangesUserPassword extends GenericTestMethods {

	
	private ParentUserDetailsPage userDetails = new ParentUserDetailsPage(driver);
	private ParentChangePasswordPage changePassword = new ParentChangePasswordPage(driver);
	private String newPassword = "Testass" + new Random().nextInt(100);

	/**
	 * Test scenario: this is a test of successful user password change
	 * 
	 * Preconditions: 1. Parent user must be registered by admin 2. Parent user must
	 * login
	 * 
	 * Steps: All the required fields must be entered. New password is submitted,
	 * which must consist of one capital letter, one symbol, 7 digits in length.
	 * Assertion: logout and login with the newly updated password
	 */

	@Test

	public void successfulPasswordChange() {
		registerParent();
		login();
		parentHomePage.clickButtonUserData();
		userDetails.clickButtonChangePassword();
		changePassword.inputOldPassword(userName);
		changePassword.inputNewPassword(newPassword);
		changePassword.inputConfirmPassword(newPassword);
		changePassword.clickButtonSave();
		WaitUtils.waitAndAcceptAlert(driver);
		parentHomePage.clickButtonLogout();
		loginPage.inputEmail(userEmail);
		loginPage.inputPassword(newPassword);
		loginPage.clickButtonLogin();
		String successLogInText = parentHomePage.getSuccessText();
		assertTrue("Chart does not contain expected text", successLogInText.contains("Tėvas"));

	}
}
