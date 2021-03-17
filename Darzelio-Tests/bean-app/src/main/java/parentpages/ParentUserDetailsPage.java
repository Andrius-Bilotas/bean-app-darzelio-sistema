package parentpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class ParentUserDetailsPage extends AbstractPage {

	/**
	 * Page where parent selects which user details to edit: details or password
	 */

	// buttons
	@FindBy (xpath = "//*[@id=\"content\"]/div/div/h5[1]/button")
	private WebElement buttonChangeData;
	
	@FindBy (xpath = "//*[@id=\"content\"]/div/div/h5[2]/button")
	private WebElement buttonChangePassword;

	@FindBy (xpath = "//*[@id=\"content\"]/div/div/h5[3]/button")
	private WebElement buttonChangeEmail;
	
	@FindBy (xpath = "//*[@id=\"content\"]/div/div/h5[4]/button")
	private WebElement buttonDeleteAccount;

	public ParentUserDetailsPage(WebDriver driver) {
		super(driver);
	}

	public void clickButtonChangeData() {
		buttonChangeData.click();
	}

	public void clickButtonChangePassword() {
		buttonChangePassword.click();
	}
	public void clickButtonChangeEmail() {
		buttonChangeEmail.click();
	}
	public void clickButtonDeleteAccount() {
		buttonDeleteAccount.click();
	}
}
