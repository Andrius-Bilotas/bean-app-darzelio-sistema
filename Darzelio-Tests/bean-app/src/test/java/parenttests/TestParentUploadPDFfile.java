package parenttests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.openqa.selenium.By;

import generictestmethods.GenericTestMethods;
import parentpages.ParentUploadPdfPage;
import utilities.WaitUtils;

public class TestParentUploadPDFfile extends GenericTestMethods {


	private ParentUploadPdfPage uploadPdf = new ParentUploadPdfPage(driver);
	private String filePath = "C:\\Failas.pdf";

	/**
	 * Test scenario: This is a test for successful PDF upload scenario.
	 * 
	 * Preconditions: 1. Parent user must be registered by admin 2. At least 1
	 * kindergarten must be registered by EDU 3. Parent user must login 4. Parent
	 * user must add parent details 5. Parent user must add child details
	 *
	 * Steps: 1. Child must be selected from the dropdown 2. Select a PDF format
	 * file, less than 10mb in size
	 * 
	 * Assertion:
	 */
	@Test

	public void successfulPDFupload() {
		registerParent();
		login();
		addParentDetails();
		addChildDetails();
		parentHomePage.clickButtonUploadPdf();
		uploadPdf.selectChild(childName + " " + childName);
		uploadPdf.selectFile(filePath);
		uploadPdf.clickButtonSubmit();
		WaitUtils.waitAndAcceptAlert(driver);
		driver.findElement(By.cssSelector("#myModal > div > div > div.modal-header > button > span")).click();
        parentHomePage.clickButtonDownloadPdf();
        String text = driver.findElement(By.linkText("Atsisiųsti vaiko pažymą")).getText();
        assertTrue("Pdf download link not found", text.contains("Atsisiųsti"));
	}
}
