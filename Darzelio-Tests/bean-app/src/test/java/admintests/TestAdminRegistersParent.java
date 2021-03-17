package admintests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import adminpages.AdminAddNewUserPage;
import adminpages.AdminHomePage;
import generictestmethods.GenericTestMethods;
import utilities.WaitUtils;

public class TestAdminRegistersParent extends GenericTestMethods {
	private AdminAddNewUserPage addNewUser = new AdminAddNewUserPage(driver);
	private AdminHomePage adminHomePage = new AdminHomePage(driver);

	/**
	 * Test scenario: This is a test for successful user (parent) creation by the
	 * admin.
	 * 
	 * Preconditions: Admin must login
	 * 
	 * Steps: All the required fields must be entered. Unique email address must be
	 * used.
	 * 
	 * Assertion:Checks if user name appeared in the list
	 */

	@Test

	public void successfulParentRegistrationByAdmin() {

		adminLogin();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#root > div > div:nth-child(2) > div > div > aside > nav > ul > li:nth-child(2) > a")));
		adminHomePage.clickButtonUserList();
		adminUserList.clickbuttonAddNewUser();
		addNewUser.inputFirstName(userName);
		addNewUser.inputLastName(userName);
		addNewUser.inputEmail(userEmail);
		addNewUser.selectRole(1);
		addNewUser.clickbuttonRegister();
		WaitUtils.waitAndAcceptAlert(driver);

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("td:nth-child(4)")));

		List<WebElement> rows = driver.findElements(By.cssSelector("#content > div > div > div > table > tbody"));
		String email = "";
		for (int i = 0; i < rows.size(); i++) {
			String tempName = rows.get(i).findElement(By.cssSelector("td:nth-child(4)")).getText();
			if (tempName.equals(userEmail)) {
				email = tempName;
				break;
			}
		}
		System.out.println(email);
		assertEquals("User email was not found", userEmail, email);
	}
}
