package adminpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class AdminUserListPage extends AbstractPage {

	/**
	 * Page where admin sees all the users.
	 */

// links
	@FindBy(linkText = "Vartotojų sąrašas")
	private WebElement linkUserList;

// buttons	
	@FindBy(css = "a.btn")
	private WebElement buttonAddNewUser;

	public AdminUserListPage(WebDriver driver) {
		super(driver);
	}

	public void clickLinkUserList() {
		linkUserList.click();
	}

	public void clickbuttonAddNewUser() {
		buttonAddNewUser.click();
	}


}
