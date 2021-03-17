package parentpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class ParentHomePage extends AbstractPage {

	/**
	 * Landing page where parent lands when logging into the system
	 */

	// text
	@FindBy(css = "#root > div > div:nth-child(2) > div > header > div > nav > ul > span")
	private WebElement successText;

	@FindBy(tagName = "h3")
	private WebElement middlePageText;

	// buttons

	@FindBy(css = "#root > div > div > div > header > div > nav > ul > div > button")
	private WebElement buttonLogout;

	@FindBy(xpath = "//*[@id=\"content\"]/div/div/div[2]/div[2]/div[1]/div/div[1]/a")
	public WebElement buttonParentForm;

	@FindBy(css = "#parentForm")
	private WebElement buttonEditParentForm;

	@FindBy(id = "childForm")
	public WebElement buttonChildForm;

	@FindBy(linkText = "Peržiūrėti/Redaguoti vaikų duomenis ir prašymus")
	private WebElement buttonEditChildDetails;

	@FindBy(id = "mainRegForm")
	public WebElement buttonRegisterToKindergartenForm;

	@FindBy(id = "userData")
	private WebElement buttonUserData;

	@FindBy(id = "uploadPdf")
	private WebElement buttonUploadPdf;
	
	@FindBy (linkText = "Atsisiųsti ir/arba ištrinti PDF dokumentus")
	private WebElement buttonDownloadPdf;

	public ParentHomePage(WebDriver driver) {
		super(driver);
	}

	public String getSuccessText() {
		return successText.getText();
	}

	public String getMiddlePageText() {
		return middlePageText.getText();
	}

	public void clickButtonLogout() {
		buttonLogout.click();
	}

	public void clickButtonParentForm() {
		buttonParentForm.click();
	}

	public void clickButtonEditParentForm() {
		buttonEditParentForm.click();
	}

	public void clickButtonChildForm() {
		buttonChildForm.click();
	}

	public void clickButtonRegisterToKindergartenForm() {
		buttonRegisterToKindergartenForm.click();
	}

	public void clickButtonUserData() {
		buttonUserData.click();
	}

	public void clickButtonEditChildDetails() {
		buttonEditChildDetails.click();
	}

	public void clickButtonUploadPdf() {
		buttonUploadPdf.click();
	}
	public void clickButtonDownloadPdf() {
		buttonDownloadPdf.click();
	}
}
