package parenttests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import generictestmethods.GenericTestMethods;
import parentpages.ParentAddKindergartenSubmitionFormPage;
import utilities.WaitUtils;

public class TestParentSubmitKindergartenApplication extends GenericTestMethods {

	private ParentAddKindergartenSubmitionFormPage submitApplicationToKG = new ParentAddKindergartenSubmitionFormPage(
			driver);

	/**
	 * Test scenario:
	 * 
	 * Preconditions: 1. Parent user must be registered by admin 2. At least 1
	 * kindergarten must be registered by EDU 3. Parent user must login 4. Parent
	 * user must add parent details 5. Parent user must add child details
	 *
	 * Steps: 1. Child must be selected from the dropdown 2. At least pne
	 * kindergarten must be selected from the dropdown
	 * 
	 * Assertion: After submittion the application, checks if parent is redirected
	 * to the home page.
	 */
	@Test

	public void successfulKindergartenApplicationSubmitionByParent() {
		
		registerParent();
		login();
		addParentDetails();
		addChildDetails();
		parentHomePage.clickButtonRegisterToKindergartenForm();
		WebElement element = parentHomePage.buttonRegisterToKindergartenForm;
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
		submitApplicationToKG.selectChild(childName + " " + childName);
		submitApplicationToKG.selectKindergarten1(1);
		submitApplicationToKG.selectKindergarten2(1);
		submitApplicationToKG.selectKindergarten3(1);
		submitApplicationToKG.selectKindergarten4(1);
		submitApplicationToKG.selectKindergarten5(1);
		submitApplicationToKG.clickButtonSubmit();
		WaitUtils.waitAndAcceptAlert(driver);
		String successText = parentHomePage.getMiddlePageText();
		assertTrue("Chart does not contain expected text",
		successText.contains("Vaiko"));
	}

}
