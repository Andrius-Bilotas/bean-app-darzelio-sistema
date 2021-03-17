package parentpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class ParentDeleteAccountPage extends AbstractPage {

	/**
	 * Page where parent confirms the deletion of the account.
	 */

	// buttons

	@FindBy(xpath = "//*[@id=\"content\"]/div/div/div/div/div[1]/button")
	private WebElement buttonDeleteAccountWithoutData;

	public ParentDeleteAccountPage(WebDriver driver) {
		super(driver);
	}

	public void clickButtonDeleteAccount() {
		buttonDeleteAccountWithoutData.click();
	}
}
