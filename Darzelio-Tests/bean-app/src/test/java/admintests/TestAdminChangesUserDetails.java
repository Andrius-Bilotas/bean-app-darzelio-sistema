package admintests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import adminpages.AdminEditUserPage;
import generictestmethods.GenericTestMethods;
import pages.LoginPage;
import parentpages.ParentHomePage;

public class TestAdminChangesUserDetails extends GenericTestMethods {
	private AdminEditUserPage adminEditUser = new AdminEditUserPage(driver);
	private LoginPage loginPage = new LoginPage(driver);
	private ParentHomePage parentHomePage = new ParentHomePage(driver);

	/**
	 * Test scenario: This is a test for successful user (parent) details change by
	 * the admin.
	 * 
	 * Preconditions: User must be already created by the admin
	 * 
	 * Steps: 1. User's email and name are updated
	 * 
	 * Assertion:Logging in as a user with the updated credentials
	 */
	@Test

	public void successfulUserEditionByAdmin() {

		registerParent();

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("td:nth-child(4)")));

		List<WebElement> rows = driver.findElements(By.cssSelector("#content > div > div > div > table > tbody"));

		for (int i = 0; i < rows.size(); i++) {
			String tempName = rows.get(i).findElement(By.cssSelector("td:nth-child(4)")).getText();
			if (tempName.equals(userEmail)) {
				rows.get(i).findElement(By.cssSelector("td:nth-child(6) > a")).click();
				break;
			}
		}

		adminEditUser.editFirstName(updated + userName);
		adminEditUser.editEmail(updated + userEmail);
		adminEditUser.clickButtonSave();
		adminHomePage.clickButtonLogOut();
		loginPage.openLoginPage();
		loginPage.inputEmail(updated + userEmail);
		loginPage.inputPassword(userName);
		loginPage.clickButtonLogin();

		String successLogInText = parentHomePage.getSuccessText();
		System.out.println(successLogInText);
		assertTrue("Chart does not contain expected text", successLogInText.contains("Tėvas"));

	}
}