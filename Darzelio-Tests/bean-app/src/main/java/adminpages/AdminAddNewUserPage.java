package adminpages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import pages.AbstractPage;

public class AdminAddNewUserPage extends AbstractPage {

	/**
	 * Page where admin inputs details of new user and confirms the creation
	 */

// buttons

	@FindBy(xpath = "//*[@type=\"submit\"]")
	private WebElement buttonRegister;

// form input

	@FindBy(xpath = "//*[@name=\"firstname\"]")
	private WebElement inputFirstName;

	@FindBy(xpath = "//*[@name=\"lastname\"]")
	private WebElement inputLastName;

	@FindBy(xpath = "//*[@name=\"email\"]")
	private WebElement inputEmail;

// dropdown
	@FindBy(name = "role")
	private WebElement selectRole;

// text
	@FindBy(tagName = "h5")
	private WebElement successText;

	public AdminAddNewUserPage(WebDriver driver) {
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

	public void selectRole(int index) {
		Select dropdown = new Select(selectRole);
		dropdown.getOptions().get(index).click();
	}

	public void clickbuttonRegister() {
		buttonRegister.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // reikia normalesni waita idet

	}

	public String getSuccessText() {
		return successText.getText();
	}
}
