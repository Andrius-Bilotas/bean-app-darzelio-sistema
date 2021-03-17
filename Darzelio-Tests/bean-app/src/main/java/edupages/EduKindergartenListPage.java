package edupages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class EduKindergartenListPage extends AbstractPage {

	/**
	 * Page where edu sees the list of all kindergartens and can see the button
	 * redirecting to add new kindergarten page
	 */

	// buttons
	@FindBy(linkText = "Pridėti naują darželį")
	private WebElement clickButtonAddNewKindergarten;

	public EduKindergartenListPage(WebDriver driver) {
		super(driver);
	}

	public void clickButtonAddNewKindergarten() {
		clickButtonAddNewKindergarten.click();
	}
}
