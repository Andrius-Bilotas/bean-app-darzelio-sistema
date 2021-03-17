package parentpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.AbstractPage;

public class ParentAddParentDetailsPage extends AbstractPage {

	/**
	 * Page where parent inputs his main parent details and confirms them
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
	
	@FindBy(xpath = "//*[@name=\"agree\"]")
	private WebElement checkAgreeWithConditions;

	@FindBy(xpath = "//*[@name=\"agreeWihRules\"]")
	private WebElement checkAgreeWithRules;
	

	// buttons
	@FindBy(xpath = "//*[@type=\"submit\"]")
	private WebElement buttonContinue;

	public ParentAddParentDetailsPage(WebDriver driver) {
		super(driver);

	}

	public void inputFirstName(String firstName) {
		inputFirstName.sendKeys(firstName);
	}

	public void inputLastName(String lastName) {
		inputLastName.sendKeys(lastName);
	}

	public void inputEmail(String email) {
		inputEmail.sendKeys(email);
	}

	public void inputPhone(String phone) {
		inputPhone.sendKeys(phone);
	}

	public void inputPersonalCode(String personalCode) {
		inputPersonalCode.sendKeys(personalCode);
	}

	public void inputCity(String city) {
		inputCity.sendKeys(city);
	}

	public void inputStreet(String street) {
		inputStreet.sendKeys(street);
	}

	public void inputHouseNumber(int houseNumber) {
		inputHouseNumber.sendKeys(String.valueOf(houseNumber));
	}

	public void inputFlatNumber(int flatNumber) {
		inputFlatNumber.sendKeys(String.valueOf(flatNumber));
	}

	public void inputStudyingInstitution(String studyingInstitution) {
		inputStudyingInstitution.sendKeys(studyingInstitution);
	}

	public void checkStudying() {
		checkStudying.click();
	}

	public void inputNumberOfKids(int numberOfKids) {
		inputNumberOfKids.sendKeys(String.valueOf(numberOfKids));
	}

	public void checkHasaDisability() {
		checkHasaDisability.click();
	}

	public void checkDeclaredResidenceSameAsLiving() {
		checkDeclaredResidenceSameAsLiving.click();
	}

	public void inputDeclaredCity(String city) {
		inputDeclaredCity.sendKeys(city);
	}
	public void checkAgreeWithConditions() {
		checkAgreeWithConditions.click();
	}

	public void checkAgreeWithRules() {
		checkAgreeWithRules.click();
	}

	public void inputDeclaredStreet(String street) {
		inputDeclaredStreet.sendKeys(street);
	}

	public void inputDeclaredHouseNumber(String houseNumber) {
		inputDeclaredHouseNumber.sendKeys(houseNumber);
	}

	public void inputDeclaredFlatNumber(String flatNumber) {
		inputDeclaredFlatNumber.sendKeys(flatNumber);

	}

	public void clickButtonContinue() {
		buttonContinue.click();
//		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS) ;
	}
}
