package edutests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import edupages.EduHomePage;
import generictestmethods.GenericTestMethods;
import pages.LoginPage;

public class TestEduLogin extends GenericTestMethods {
	private EduHomePage eduHomePage = new EduHomePage(driver);
	private LoginPage loginPage = new LoginPage(driver);

	/**
	 * Test scenario:
	 * 
	 * Preconditions: Edu user must be registered by admin
	 * 
	 * Steps: Use Email address and password created by the admin
	 * 
	 * Assertion: Checks if homepage Success Message is visible
	 * 
	 */

	@Test

	public void successfulEduLogin() {

		registerEdu();
		loginPage.openLoginPage();
		loginPage.inputEmail(userEmail);
		loginPage.inputPassword(userName);
		loginPage.clickButtonLogin();
		driver.findElement(By.cssSelector("#myModal > div > div > div.modal-header > button > span")).click();
		String successLogInText = eduHomePage.getSuccessText();
		assertTrue("Chart does not contain expected text", successLogInText.contains("Švietimo"));
	}

}
