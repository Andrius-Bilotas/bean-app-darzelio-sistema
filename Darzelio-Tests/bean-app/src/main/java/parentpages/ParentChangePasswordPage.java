package parentpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class ParentChangePasswordPage extends AbstractPage {

	/**
	 * Page where parent changes his user password
	 */

	// input fields
	@FindBy(xpath = "//*[@name=\"oldPassword\"]")
	private WebElement inputOldPassword;

	@FindBy(xpath = "//*[@name=\"newPassword\"]")
	private WebElement inputNewPassword;

	@FindBy(xpath = "//*[@name=\"confirmPassword\"]")
	private WebElement inputConfirmPassword;

	// buttons
	@FindBy(xpath = "//*[@class=\"btn btn-success\"]")
	private WebElement buttonSave;

	public ParentChangePasswordPage(WebDriver driver) {
		super(driver);

	}

	public void inputOldPassword(String oldPassword) {
		inputOldPassword.sendKeys(oldPassword);
	}

	public void inputNewPassword(String newPassword) {
		inputNewPassword.sendKeys(newPassword);
	}

	public void inputConfirmPassword(String newPassword) {
		inputConfirmPassword.sendKeys(newPassword);
	}

	public void clickButtonSave() {
		buttonSave.click();
	}
}
