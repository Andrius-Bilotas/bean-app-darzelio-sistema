package parentpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class ParentEditKindergartenApplicationPage extends AbstractPage {

	/**
	 * Page where parent can edit already submited kindergarten application and also
	 * delete it
	 */

	// buttons

	@FindBy(id = "deleteChildApplication")
	public WebElement buttonDeleteApplication;

	public ParentEditKindergartenApplicationPage(WebDriver driver) {
		super(driver);
	}

	public void clickButtonDeleteApplication() {
		buttonDeleteApplication.click();
	}
}
