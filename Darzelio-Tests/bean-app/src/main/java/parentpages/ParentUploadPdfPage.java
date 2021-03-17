package parentpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import pages.AbstractPage;

public class ParentUploadPdfPage extends AbstractPage {

	/**
	 * Page where parent can upload the PDF document associated with his child
	 */

	// dropdown selection
	@FindBy(id = "selectChild")
	private WebElement selectChild;

	// upload

	@FindBy(id = "pdf")
	private WebElement selectFile;

	// button

	@FindBy(xpath = "//*[@type=\"submit\"]")
	private WebElement buttonSubmit;

	public ParentUploadPdfPage(WebDriver driver) {
		super(driver);
	}

	public void selectChild(String text) {
		Select dropdown = new Select(selectChild);
		dropdown.selectByVisibleText(text);
	}

	public void selectFile(String filePath) {
		selectFile.sendKeys(filePath);
	}

	public void clickButtonSubmit() {
		buttonSubmit.click();
	}
}
