package parenttests;

import java.util.List;

import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import static org.junit.Assert.*;
import generictestmethods.GenericTestMethods;
import parentpages.ParentEditKindergartenApplicationPage;

import utilities.WaitUtils;

public class TestParentDeleteKindergartenApplication extends GenericTestMethods {


	private ParentEditKindergartenApplicationPage editApplication = new ParentEditKindergartenApplicationPage(driver);
	/**
	 * Test scenario:
	 * 
	 * Preconditions: 1. Parent user must be registered by admin 2. At least 1
	 * kindergarten must be registered by EDU 3. Parent user must login 4. Parent
	 * user must add parent details 5. Parent user must add child details 6. Parent user must submit kindergarten application
	 *
	 * Steps: 1. Go to edit details of the selected child. 2. Click Delete Application
	 * 
	 * Assertion: Checks if kindergarten application does not exist anymore
	 */
	@Test
	
	public void successfulKindergartenApplicationDeletionByParent() {
		
		registerParent();
		login();
		addParentDetails();
		addChildDetails();
		submitKindergartenApplication();
		driver.findElement(By.cssSelector("#myModal > div > div > div.modal-header > button > span")).click();
		parentHomePage.clickButtonEditChildDetails();
		List<WebElement> rows = driver
				.findElements(By.cssSelector("#content > div > div > div:nth-child(2) > table > tbody > tr"));

		for (int i = 0; i < rows.size(); i++) {
			String tempName = rows.get(i).findElement(By.cssSelector("td:nth-child(2)")).getText();
			if (tempName.equals(childName)) {
				rows.get(i).findElement(By.cssSelector("td:nth-child(5) > a")).click();
				break;
			}
		}
		
		Actions actions = new Actions(driver); 
		actions.moveToElement(editApplication.buttonDeleteApplication).perform();
		editApplication.clickButtonDeleteApplication();
		driver.findElement(By.cssSelector("div > div > div > div> div:nth-child(3)> button.btn.btn-danger")).click();
		WaitUtils.waitAndAcceptAlert(driver);
		driver.navigate().refresh();
		
		driver.findElement(By.cssSelector("#content > div > div > div:nth-child(2) > table > tbody > tr > td:nth-child(5) > a")).click();
		
		String text = driver.findElement(By.cssSelector("#content > div > p")).getText();
		assertTrue("Kindergarten found", text.contains("Šiam vaikui"));
		
	}
	
	
	
}
