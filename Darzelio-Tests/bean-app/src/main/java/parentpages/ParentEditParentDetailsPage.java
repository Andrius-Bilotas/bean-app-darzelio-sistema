package parentpages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class ParentEditParentDetailsPage extends AbstractPage {

	/**
	 * Page where parent can edit his parent details
	 */

	// input fields

	@FindBy(xpath = "//*[@name=\"firstname\"]")
	private WebElement inputFirstName;

	@FindBy(xpath = "//*[@name=\"lastname\"]")
	private WebElement inputLastName;

	@FindBy(xpath = "//*[@name=\"email\"]")
	private WebElement inputEmail;

	@FindBy(xpath = "//*[@name=\"phone\"]")
	private WebElement inputPhone;

	@FindBy(xpath = "//*[@name=\"personalCode\"]")
	private WebElement inputPersonalCode;

	@FindBy(xpath = "//*[@name=\"city\"]")
	private WebElement inputCity;

	@FindBy(xpath = "//*[@name=\"street\"]")
	private WebElement inputStreet;

	@FindBy(xpath = "//*[@name=\"houseNumber\"]")
	private WebElement inputHouseNumber;

	@FindBy(xpath = "//*[@name=\"flatNumber\"]")
	private WebElement inputFlatNumber;

	@FindBy(xpath = "//*[@name=\"numberOfKids\"]")
	private WebElement inputNumberOfKids;

	@FindBy(xpath = "//*[@name=\"declaredStreet\"]")
	private WebElement inputDeclaredStreet;

	@FindBy(xpath = "//*[@name=\"declaredCity\"]")
	private WebElement inputDeclaredCity;

	@FindBy(xpath = "//*[@name=\"declaredHouseNumber\"]")
	private WebElement inputDeclaredHouseNumber;

	@FindBy(xpath = "//*[@name=\"declaredFlatNumber\"]")
	private WebElement inputDeclaredFlatNumber;

	@FindBy(xpath = "//*[@name=\"studyingInstitution\"]")
	private WebElement inputStudyingInstitution;

	// checkboxes
	@FindBy(xpath = "//*[@name=\"studying\"]")
	private WebElement checkStudying;

	@FindBy(xpath = "//*[@name=\"hasDisability\"]")
	private WebElement checkHasaDisability;

	@FindBy(xpath = "//*[@name=\"declaredResidenceSameAsLiving\"]")
	private WebElement checkDeclaredResidenceSameAsLiving;

	// buttons
	@FindBy(css = "#content > div > div > div > form > div:nth-child(16) > button")
	private WebElement buttonUpdate;

	public ParentEditParentDetailsPage(WebDriver driver) {
		super(driver);
	}

	public void inputFirstName(String firstName) {
		inputFirstName.clear();
		inputFirstName.sendKeys(firstName);
	}

	public void inputLastName(String lastName) {
		inputLastName.clear();
		inputLastName.sendKeys(lastName);
	}

	public void inputEmail(String email) {
		inputEmail.clear();
		inputEmail.sendKeys(email);
	}

	public void inputPhone(String phone) {
		inputPhone.clear();
		inputPhone.sendKeys(phone);
	}

	public void inputPersonalCode(String personalCode) {
		inputPersonalCode.clear();
		inputPersonalCode.sendKeys(personalCode);
	}

	public void inputCity(String city) {
		inputCity.clear();
		inputCity.sendKeys(city);
	}

	public void inputStreet(String street) {
		inputStreet.clear();
		inputStreet.sendKeys(street);
	}

	public void inputHouseNumber(int houseNumber) {
		inputHouseNumber.clear();
		inputHouseNumber.sendKeys(String.valueOf(houseNumber));
	}

	public void inputFlatNumber(int flatNumber) {
		inputFlatNumber.clear();
		inputFlatNumber.sendKeys(String.valueOf(flatNumber));
	}

	public void inputStudyingInstitution(String studyingInstitution) {
		inputStudyingInstitution.clear();
		inputStudyingInstitution.sendKeys(studyingInstitution);
	}

	public void unCheckStudying() {
		checkStudying.click();
	}

	public void inputNumberOfKids(int numberOfKids) {
		inputNumberOfKids.clear();
		inputNumberOfKids.sendKeys(String.valueOf(numberOfKids));
	}

	public void unCheckHasaDisability() {
		checkHasaDisability.click();
	}

	public void unCheckDeclaredResidenceSameAsLiving() {
		checkDeclaredResidenceSameAsLiving.click();
	}

	public void clickButtonUpdate() {
		buttonUpdate.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
}
