package admintests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generictestmethods.GenericTestMethods;
import utilities.WaitUtils;


public class TestAdminDeleteUser extends GenericTestMethods {


	/**
	 * Test scenario: This is a test for successful user (parent) deletion by the admin.
	 * 
	 * Preconditions:
	 * User must be already created by the admin
	 * 
	 * Steps: Admin confirms the deletion of one of the selected users
	 * 
	 * Assertion:Trying to login with the deleted account
	 */
	
	@Test
	public void successfulUserDeletionByAdmin() {
		registerParent();
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("td:nth-child(4)")));
		
		List<WebElement> rows = driver
				.findElements(By.cssSelector("#content > div > div > div > table > tbody"));

		for (int i = 0; i < rows.size(); i++) {
			String tempName = rows.get(i).findElement(By.cssSelector("td:nth-child(4)")).getText();
			if (tempName.equals(userEmail)) {
				rows.get(i).findElement(By.cssSelector("td:nth-child(7) > button")).click();
				break;
			}

		}
		for (int i = 0; i < rows.size(); i++) {
			String tempNametwo = rows.get(i).findElement(By.cssSelector("td:nth-child(8) > div > div > div > div > h5"))
					.getText();
			if (tempNametwo.equals(userEmail)) {
				rows.get(i).findElement(
						By.cssSelector("td:nth-child(8) > div > div > div > div:nth-child(3)> button.btn.btn-danger"))
						.click();
				break;
			}
		}
		driver.navigate().refresh();
		adminHomePage.clickButtonLogOut();
		loginPage.openLoginPage();
		loginPage.inputEmail(userEmail);
		loginPage.inputPassword(userName);
		loginPage.clickButtonLogin();
		WebDriverWait waittwo = new WebDriverWait(driver, 5);
		Alert alert = waittwo.until(ExpectedConditions.alertIsPresent());
		String success = alert.getText();
		assertEquals("Success text not found or incorrect", "Slaptažodis ir/arba el.paštas neteisingi!", success);
		WaitUtils.waitAndAcceptAlert(driver);


	}
}
