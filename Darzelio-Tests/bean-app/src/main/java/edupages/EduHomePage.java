package edupages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class EduHomePage extends AbstractPage {

	/**
	 * Landing page where edu lands when logging in
	 */

	// text
	@FindBy(css = "#root > div > div:nth-child(2) > div > header > div > nav > ul > span")
	private WebElement successText;

	// buttons
	@FindBy (xpath = "//*[@class=\"btn\"]")
	private WebElement buttonLogOut;

	@FindBy(linkText = "Darželių sąrašas")
	private WebElement buttonKindegartenList;

	@FindBy(linkText = "Pridėti darželį")
	private WebElement buttonAddNewKindergarten;

	@FindBy(id = "userData")
	private WebElement buttonUserData;


	public EduHomePage(WebDriver driver) {
		super(driver);
	}

	public String getSuccessText() {
		return successText.getText();
	}

	public void clickButtonLogout() {
		buttonLogOut.click();
	}

	public void clickButtonKindergartenList() {
		buttonKindegartenList.click();
	}

	public void clickButtonAddNewKindergarten() {
		buttonAddNewKindergarten.click();
	}

	public void clickButtonUserData() {
		buttonUserData.click();
	}
}
