package edupages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class EduAddNewKindergartenPage extends AbstractPage {

	/**
	 * Page where edu inputs details of the kindergarten and adds it to the system
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

	public EduAddNewKindergartenPage(WebDriver driver) {
		super(driver);
	}

	public void inputKindergartenName(String name) {
		inputName.sendKeys(name);
	}

	public void inputKindergartenAddress(String address) {
		inputAddress.sendKeys(address);
	}

	public void inputSpotsInFirstAgeGroup(int spots) {
		inputSpotsInFirstAgeGroup.sendKeys(String.valueOf(spots));
	}

	public void inputSpotsInSecondAgeGroup(int spots) {
		inputSpotsInSecondAgeGroup.sendKeys(String.valueOf(spots));
	}

	public void clickButtonRegister() {
		clickButtonRegister.click();
	}
}
