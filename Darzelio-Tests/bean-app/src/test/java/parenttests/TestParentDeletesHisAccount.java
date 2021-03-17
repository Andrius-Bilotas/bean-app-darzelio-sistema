package parenttests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generictestmethods.GenericTestMethods;
import parentpages.ParentDeleteAccountPage;
import parentpages.ParentUserDetailsPage;
import utilities.WaitUtils;

public class TestParentDeletesHisAccount extends GenericTestMethods {

	private ParentUserDetailsPage userDetails = new ParentUserDetailsPage(driver);
	private ParentDeleteAccountPage deleteAccount = new ParentDeleteAccountPage(driver);

	/**
	 * Test scenario:this is a test of successful user details change.
	 * 
	 * Preconditions: 1. Parent user must be registered in the system 2. Parent user
	 * must login
	 * 
	 * Steps: Parent clicks to delete his account Assertion: try to login with the
	 * deleted credentials
	 */

	@Test

	public void successfulAccountDeletion() {
		registerParent();
		login();
		parentHomePage.clickButtonUserData();
		userDetails.clickButtonDeleteAccount();
		deleteAccount.clickButtonDeleteAccount();
		driver.findElement(By.xpath("//*[@id=\"staticBackdrop\"]/div/div/div[3]/button[1]")).click();
		WaitUtils.waitAndAcceptAlert(driver);
		loginPage.openLoginPage();
		loginPage.inputEmail(userEmail);
		loginPage.inputPassword(userName);
		loginPage.clickButtonLogin();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		String success = alert.getText();
		assertEquals("Success text not found or incorrect", "Slaptažodis ir/arba el.paštas neteisingi!", success);
		WaitUtils.waitAndAcceptAlert(driver);
	}
}
