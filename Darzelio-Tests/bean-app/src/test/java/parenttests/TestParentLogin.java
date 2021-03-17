package parenttests;

import org.junit.Test;


import generictestmethods.GenericTestMethods;

import static org.junit.Assert.*;

public class TestParentLogin extends GenericTestMethods {

	/**
	 * Test scenario:
	 * 
	 * Preconditions: Parent user must be registered by admin Steps: Use Email
	 * address and password created by the admin Assertion: Checks if homepage
	 * Success Message is visible
	 * 
	 * 
	 */
	@Test
	public void successfulParentLogin() {

		registerParent();
		loginPage.openLoginPage();
		loginPage.inputEmail(userEmail);
		loginPage.inputPassword(userName);
		loginPage.clickButtonLogin();
		String successLogInText = parentHomePage.getSuccessText();
		System.out.println(successLogInText);
		assertTrue("Chart does not contain expected text", successLogInText.contains("Tėvas"));
	}

}
