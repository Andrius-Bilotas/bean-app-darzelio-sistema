package parentpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class ParentAddChildDetailsPage extends AbstractPage {

	/**
	 * Page where parent inputs new child details and adds the child to the system
	 */

	// input fields
	@FindBy(xpath = "//*[@name=\"firstname\"]")
	private WebElement inputFirstName;

	@FindBy(xpath = "//*[@name=\"lastname\"]")
	private WebElement inputLastName;

	@FindBy(xpath = "//*[@name=\"birthdate\"]")
	private WebElement inputBirthDate;

	@FindBy(xpath = "//*[@name=\"personalCode\"]")
	private WebElement inputPersonCode;

	@FindBy(xpath = "//*[@name=\"city\"]")
	private WebElement inputCity;

	@FindBy(xpath = "//*[@name=\"street\"]")
	private WebElement inputStreet;

	@FindBy(xpath = "//*[@name=\"houseNumber\"]")
	private WebElement inputHouseNumber;

	@FindBy(xpath = "//*[@name=\"flatNumber\"]")
	private WebElement inputFlatNumber;

	@FindBy(xpath = "//*[@name=\"secondParentFirstname\"]")
	private WebElement inputSecondParentFirstName;

	@FindBy(xpath = "//*[@name=\"secondParentLastname\"]")
	private WebElement inputSecondParentLastName;

	@FindBy(xpath = "//*[@name=\"secondParentEmail\"]")
	private WebElement inputSecondParentEmail;

	@FindBy(xpath = "//*[@name=\"secondParentPhone\"]")
	private WebElement inputSecondParentPhone;

	@FindBy(xpath = "//*[@name=\"secondParentPersonalCode\"]")
	private WebElement inputSecondParentPersonCode;

	@FindBy(xpath = "//*[@name=\"secondParentCity\"]")
	private WebElement inputSecondParentCity;

	@FindBy(xpath = "//*[@name=\"secondParentStreet\"]")
	private WebElement inputSecondParentStreet;

	@FindBy(xpath = "//*[@name=\"secondParentHouseNumber\"]")
	private WebElement inputSecondParentHouseNumber;

	@FindBy(xpath = "//*[@name=\"secondParentFlatNumber\"]")
	private WebElement inputSecondParentFlatNumber;

	@FindBy(xpath = "//*[@name=\"secondParentNumberOfKids\"]")
	private WebElement inputSecondParentNumberOfKids;

	@FindBy(xpath = "//*[@name=\"secondParentDeclaredCity\"]")
	private WebElement inputSecondParentDeclaredCity;

	@FindBy(xpath = "//*[@name=\"secondParentDeclaredStreet\"]")
	private WebElement inputSecondParentDeclaredStreet;

	@FindBy(xpath = "//*[@name=\"secondParentDeclaredHouseNumber\"]")
	private WebElement inputSecondParentDeclaredHouseNumber;

	@FindBy(xpath = "//*[@name=\"secondParentDeclaredFlatNumber\"]")
	private WebElement inputSecondParentDeclaredFlatNumber;

	@FindBy(xpath = "//*[@name=\"secondParentStudyingInstitution\"]")
	private WebElement inputSecondParentStudyingInstitution;

	// checkboxes
	@FindBy(xpath = "//*[@name=\"adopted\"]")
	private WebElement checkAdopted;

	@FindBy(xpath = "//*[@name=\"secondParent\"]")
	private WebElement checkSecondParent;

	@FindBy(xpath = "//*[@name=\"secondParentStudying\"]")
	private WebElement checkSecondParentStudying;

	@FindBy(xpath = "//*[@name=\"secondParentHasDisability\"]")
	private WebElement checkSecondParentHasDisability;

	@FindBy(xpath = "//*[@name=\"secondParentDeclaredResidenceSameAsLiving\"]")
	private WebElement checkSecondParentDeclaredResidenceSameAsLiving;

	// buttons
	@FindBy(xpath = "//*[@type=\"submit\"]")
	private WebElement buttonSaveAndContinue;

	// text
	@FindBy(css = "#content > div > div > div > div > h3")
	private WebElement successText;

	public ParentAddChildDetailsPage(WebDriver driver) {
		super(driver);

	}

	public void inputChildFirstName(String firstName) {
		inputFirstName.sendKeys(firstName);
	}

	public void inputChildLastName(String lastName) {
		inputLastName.sendKeys(lastName);
	}

	public void inputChildBirthDate(String birthDate) {
		inputBirthDate.clear();
		inputBirthDate.sendKeys(birthDate);
	}

	public void inputChildPersonCode(String personCode) {
		inputPersonCode.sendKeys(personCode);
	}

	public void inputChildCity(String city) {
		inputCity.clear();
		inputCity.sendKeys(city);
	}

	public void inputChildStreet(String street) {
		inputStreet.clear();
		inputStreet.sendKeys(street);
	}

	public void inputChildHouseNumber(int houseNumber) {
		inputHouseNumber.sendKeys(String.valueOf(houseNumber));
	}

	public void inputChildFlatNumber(int flatNumber) {
		inputFlatNumber.sendKeys(String.valueOf(flatNumber));
	}

	public void checkAdopted() {
		checkAdopted.click();
	}

	public void checkAddSecondParent() {
		checkSecondParent.click();
	}

	public void inputSecondParentFirstName(String firstName) {
		inputSecondParentFirstName.sendKeys(firstName);
	}

	public void inputSecondParentLastName(String lastName) {
		inputSecondParentLastName.sendKeys(lastName);
	}

	public void inputSecondParentEmail(String email) {
		inputSecondParentEmail.sendKeys(email);
	}

	public void inputSecondParentPhone(String phone) {
		inputSecondParentPhone.sendKeys(phone);
	}

	public void inputSecondParentPersonCode(String personCode) {
		inputSecondParentPersonCode.sendKeys(personCode);
	}

	public void inputSecondParentCity(String city) {
		inputSecondParentCity.sendKeys(city);
	}

	public void inputSecondParentStreet(String street) {
		inputSecondParentStreet.sendKeys(street);
	}

	public void inputSecondParentHouseNumber(int houseNumber) {
		inputSecondParentHouseNumber.sendKeys(String.valueOf(houseNumber));
	}

	public void inputSecondParentFlatNumber(int flatNumber) {
		inputSecondParentFlatNumber.sendKeys(String.valueOf(flatNumber));
	}

	public void inputSecondParentNumberOfKids(int number) {
		inputSecondParentNumberOfKids.sendKeys(String.valueOf(number));
	}

	public void checkSecondParentStudying() {
		checkSecondParentStudying.click();
	}

	public void inputSecondParentStudyingInstitution(String institution) {
		inputSecondParentStudyingInstitution.sendKeys(institution);
	}

	public void checkSecondParentHasDisability() {
		checkSecondParentHasDisability.click();
	}

	public void checkSecondParentDeclaredResidenceSameAsLiving() {
		checkSecondParentDeclaredResidenceSameAsLiving.click();
	}

	public void inputSecondParentDeclaredCity(String city) {
		inputSecondParentDeclaredCity.sendKeys(city);
	}

	public void inputSecondParentDeclaredStreet(String street) {
		inputSecondParentDeclaredStreet.sendKeys(street);
	}

	public void inputSecondParentDeclaredHouseNumber(String houseNumber) {
		inputSecondParentDeclaredHouseNumber.sendKeys(houseNumber);
	}

	public void inputSecondParentDeclaredFlatNumber(int flatNumber) {
		inputSecondParentDeclaredFlatNumber.sendKeys(String.valueOf(flatNumber));
	}

	public void clickButtonSaveAndContinue() {
		buttonSaveAndContinue.click();
	}

	public String getSuccessText() {
		return successText.getText();
	}

}