package edupages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class EduChangePasswordPage extends AbstractPage {

	/**
	 * Page where admin inputs new the user details and confirms the change
	 */

	// input fields
	@FindBy(xpath = "//*[@name=\"oldPassword\"]")
	private WebElement inputOldPassword;

	@FindBy(xpath = "//*[@name=\"newPassword\"]")
	private WebElement inputNewPassword;

	@FindBy(xpath = "//*[@name=\"confirmPassword\"]")
	private WebElement inputConfirmPassword;

	// button
	@FindBy(xpath = "//*[@class=\"btn btn-success\"]")
	private WebElement buttonSave;

	public EduChangePasswordPage(WebDriver driver) {
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
