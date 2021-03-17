package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends AbstractPage{

	/**
	 * Page where parent registers himself
	 */
	
	
	// input fields
	@FindBy(name = "firstname")
	private WebElement inputFirstName;

	@FindBy(name = "lastname")
	private WebElement inputLastName;
	
	@FindBy(name = "email")
	private WebElement inputEmail;
	
	//buttons
	@FindBy(xpath="//*[@id=\"root\"]/div/div[2]/div/form/div[5]/button")
	private WebElement buttonRegister;
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	public void inputFirstName(String firstname) {
		inputFirstName.sendKeys(firstname);
	}
	public void inputLastName(String lastname) {
		inputLastName.sendKeys(lastname);
	}
	public void inputEmail(String email) {
		inputEmail.sendKeys(email);
	}
	public void clickButtonRegister() {
		buttonRegister.click();
	}
}
