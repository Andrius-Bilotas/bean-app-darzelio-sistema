package edupages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class EduUserDetailsPage extends AbstractPage {

	/**
	 * Page where edu selects what he wants to edit: his details or password
	 */
	
	//buttons
	@FindBy (xpath = "//*[@id=\"content\"]/div/div/h5[1]/button")
	private WebElement buttonChangeData;
	
	@FindBy (xpath = "//*[@id=\"content\"]/div/div/h5[2]/button")
	private WebElement buttonChangePassword;

	@FindBy (xpath = "//*[@id=\"content\"]/div/div/h5[3]/button")
	private WebElement buttonChangeEmail;
	
	public EduUserDetailsPage(WebDriver driver) {
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
}
