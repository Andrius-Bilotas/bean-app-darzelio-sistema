package parentpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class ParentUpdateUserEmailPage extends AbstractPage {

	/**
	 * Page where parent can edit his user details
	 */

	// input fields
	@FindBy(xpath = "//*[@name=\"firstname\"]")
	private WebElement inputFirstName;

	@FindBy(xpath = "//*[@name=\"lastname\"]")
	private WebElement inputLastName;

	@FindBy(xpath = "//*[@name=\"email\"]")
	private WebElement inputEmail;

	// checkbox
	@FindBy(xpath = "//*[@name=\"markedForDeletion\"]")
	private WebElement checkForDeletion;

	// button
	@FindBy(xpath = "//*[@class=\"btn btn-success\"]")
	private WebElement buttonSave;

	public ParentUpdateUserEmailPage(WebDriver driver) {
		super(driver);
	}

	public void inputFirstName(String firstName) {
		inputFirstName.clear();
		inputFirstName.sendKeys(firstName);
	}

	public void inputLastName(String lastName) {
		inputLastName.clear();
		inputLastName.sendKeys(lastName);
	}

	public void inputEmail(String email) {
		inputEmail.clear();
		inputEmail.sendKeys(email);
	}

	public void clickButtonSave() {
		buttonSave.click();
	}

	public void checkForDeletion() {
		checkForDeletion.click();
	}
}
