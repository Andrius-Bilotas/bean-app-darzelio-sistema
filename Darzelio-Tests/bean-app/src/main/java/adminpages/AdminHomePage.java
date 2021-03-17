package adminpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class AdminHomePage extends AbstractPage {

	/**
	 * Landing page where admin lands when logging in
	 */

	@FindBy(linkText = "Vartotojų sąrašas")
	public WebElement buttonUserList;
	
	@FindBy (xpath = "//*[@class=\"btn\"]")
	private WebElement buttonLogOut;

	public AdminHomePage(WebDriver driver) {
		super(driver);
	}

	public void clickButtonUserList() {
		buttonUserList.click();
	}
	public void clickButtonLogOut() {
		buttonLogOut.click();
	}
}
