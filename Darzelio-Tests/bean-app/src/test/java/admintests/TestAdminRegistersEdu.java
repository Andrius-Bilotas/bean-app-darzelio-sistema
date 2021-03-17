package admintests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generictestmethods.GenericTestMethods;
import utilities.WaitUtils;

public class TestAdminRegistersEdu extends GenericTestMethods {

	/**
	 * Test scenario: This is a test for successful user (edu) creation by the
	 * admin.
	 * 
	 * Preconditions: Admin must login
	 * 
	 * Steps: All the required fields must be entered. Unique email address must be
	 * used.
	 * 
	 * Assertion:Checks if user name appeared in the list
	 * 
	 */

	@Test
	public void successfulEduRegistrationByAdmin() {

		adminLogin();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#root > div > div:nth-child(2) > div > div > aside > nav > ul > li:nth-child(2) > a")));
        adminHomePage.clickButtonUserList();

		adminUserList.clickbuttonAddNewUser();
		addNewUser.inputFirstName(userName);
		addNewUser.inputLastName(userName);
		addNewUser.inputEmail(userEmail);
		addNewUser.selectRole(2);
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
		assertEquals("User email was not found", userEmail, email);


	}
}
