package edutests;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import edupages.EduChangePasswordPage;
import edupages.EduUserDetailsPage;
import generictestmethods.GenericTestMethods;


public class TestEduChangesUserPassword extends GenericTestMethods {

	private EduUserDetailsPage userDetails = new EduUserDetailsPage(driver);
	private EduChangePasswordPage changePassword = new EduChangePasswordPage(driver);
	private String newPassword = "Testass" + new Random().nextInt(100);

	/**
	 * Test scenario: this is a test of successful user password change
	 * 
	 * Preconditions: 1. Edu user must be registered by admin 2. Edu user must login
	 * 
	 * Steps: All the required fields must be entered. New password is submitted,
	 * which must consist of one capital letter, one symbol, 7 digits in length.
	 * Assertion: logout and login with the newly updated password
	 */

	@Test

	public void successfulPasswordChangeByEdu() {
		registerEdu();
		login();
		eduHomePage.clickButtonUserData();
		userDetails.clickButtonChangePassword();
		changePassword.inputOldPassword(userName);
		changePassword.inputNewPassword(newPassword);
		changePassword.inputConfirmPassword(newPassword);
		changePassword.clickButtonSave();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		eduHomePage.clickButtonLogout();
		loginPage.inputEmail(userEmail);
		loginPage.inputPassword(newPassword);
		loginPage.clickButtonLogin();
		String successLogInText = eduHomePage.getSuccessText();
		assertTrue("Chart does not contain expected text", successLogInText.contains("Švietimo"));

	}
}
