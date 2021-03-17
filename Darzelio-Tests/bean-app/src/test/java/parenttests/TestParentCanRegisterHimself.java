package parenttests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generictestmethods.GenericTestMethods;
import pages.RegistrationPage;
import utilities.WaitUtils;

public class TestParentCanRegisterHimself extends GenericTestMethods {

	private RegistrationPage registrationPage = new RegistrationPage(driver);

	/**
	 * Test scenario:
	 * 
	 * Preconditions: Parent user must not be registered in the system Steps: Email
	 * address must be unique. Password is equal to first user name Assertion: New
	 * created user logs in and Checks if homepage Success Message is visible
	 * 
	 * 
	 */

	@Test

	public void SuccessfulParentRegistrationByParent() {

		loginPage.openLoginPage();
		loginPage.clickButtonRegister();
		registrationPage.inputFirstName(userName);
		registrationPage.inputLastName(userName);
		registrationPage.inputEmail(userEmail);
		registrationPage.clickButtonRegister();
		WaitUtils.waitAndAcceptAlert(driver);
		loginPage.inputEmail(userEmail);
		loginPage.inputPassword(userName);
		loginPage.clickButtonLogin();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h3")));
		String successLogInText = parentHomePage.getMiddlePageText();
		System.out.println(successLogInText);
		assertTrue("Chart does not contain expected text", successLogInText.contains("Vaiko registracijos"));
	}

}
