package edupages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class EduEditKindergartenDetailsPage extends AbstractPage {

	/**
	 * Page where admin inputs updated kindergarten details and confirms the change
	 */

	// input fields
	@FindBy(xpath = "//*[@name=\"name\"]")
	private WebElement inputName;

	@FindBy(xpath = "//*[@name=\"address\"]")
	private WebElement inputAddress;

	@FindBy(xpath = "//*[@name=\"spotsInFirstAgeGroup\"]")
	private WebElement inputSpotsInFirstAgeGroup;

	@FindBy(xpath = "//*[@name=\"spotsInSecondAgeGroup\"]")
	private WebElement inputSpotsInSecondAgeGroup;

	// buttons
	@FindBy(xpath = "//*[@class=\"btn btn-success\"]")
	private WebElement clickButtonRegister;

	public EduEditKindergartenDetailsPage(WebDriver driver) {
		super(driver);
	}

	public void inputKindergartenName(String name) {
		inputName.clear();
		inputName.sendKeys(name);
	}

	public void inputKindergartenAddress(String address) {
		inputAddress.clear();
		inputAddress.sendKeys(address);
	}

	public void inputSpotsInFirstAgeGroup(int spots) {
		inputSpotsInFirstAgeGroup.clear();
		inputSpotsInFirstAgeGroup.sendKeys(String.valueOf(spots));
	}

	public void inputSpotsInSecondAgeGroup(int spots) {
		inputSpotsInSecondAgeGroup.clear();
		inputSpotsInSecondAgeGroup.sendKeys(String.valueOf(spots));
	}

	public void clickButtonRegister() {
		clickButtonRegister.click();
	}
}
