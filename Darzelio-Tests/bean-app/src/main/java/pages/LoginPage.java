package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

	/**
	 * Main Login page where user enters his login credentials and is redirected to
	 * his home page
	 */

	// input fields
	@FindBy(id = "InputEmail")
	private WebElement inputEmail;

	@FindBy(id = "InputPassword")
	private WebElement inputPassword;

	// buttons
	@FindBy(xpath = "//*[@type=\"submit\"]")
	private WebElement buttonLogin;
	
	@FindBy (linkText = "Naujo vartotojo registracija")
	private WebElement buttonRegister;

	// login page url
	private String loginUrl = "http://akademijait.vtmc.lt:8181/bean-app/";

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void openLoginPage() {
		driver.get(loginUrl);
	}

	public void inputEmail(String email) {
		inputEmail.sendKeys(email);
	}

	public void inputPassword(String password) {
		inputPassword.sendKeys(password);
	}

	public void clickButtonLogin() {
		buttonLogin.click();
	}
	public void clickButtonRegister() {
		buttonRegister.click();
	}

}
