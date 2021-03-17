package parenttests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Random;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import generictestmethods.GenericTestMethods;
import utilities.WaitUtils;

public class TestParentChangesParentDetails extends GenericTestMethods {

	

	/**
	 * Test scenario:
	 * 
	 * Preconditions: 1. Parent user must be registered by admin 2. Parent user must
	 * login 3. Parent user must add parent details 
	 * 
	 * Steps: All the required fields must be entered. Person number must be unique.
	 * Assertion: after successful update parent is brought back to home page
	 */
	
	@Test
	
	public void successfulParentInfoEdit () {
		
     registerParent();
     login();
     addParentDetails();
     WebElement element = driver.findElement(By.xpath("//*[@id=\"parentForm\"]"));
     Actions actions = new Actions(driver);
     actions.moveToElement(element).click().perform();
     editParentInfoPage.inputFirstName(updated + parentName);
		editParentInfoPage.inputEmail(updated + userEmail);
		editParentInfoPage.inputNumberOfKids(new Random().nextInt(10) + 1);
		editParentInfoPage.unCheckHasaDisability();
		editParentInfoPage.clickButtonUpdate();
		WaitUtils.waitAndAcceptAlert(driver);
 
		String successText = parentHomePage.getMiddlePageText();
		assertTrue("Chart does not contain expected text", successText.contains("Vaiko"));

 
	}
}
