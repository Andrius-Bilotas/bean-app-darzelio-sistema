package generictestmethods;

import java.util.Random;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import adminpages.AdminAddNewUserPage;
import adminpages.AdminHomePage;
import adminpages.AdminUserListPage;
import basetest.BaseTest;
import edupages.EduAddNewKindergartenPage;
import edupages.EduHomePage;
import edupages.EduKindergartenListPage;
import pages.LoginPage;
import parentpages.ParentAddChildDetailsPage;
import parentpages.ParentAddKindergartenSubmitionFormPage;
import parentpages.ParentAddParentDetailsPage;
import parentpages.ParentEditParentDetailsPage;
import parentpages.ParentHomePage;
import utilities.WaitUtils;

public class GenericTestMethods extends BaseTest {

	/**
	 * In this class all the generic methods are stored which can be used by
	 * multiple tests
	 */

	public AdminHomePage adminHomePage = new AdminHomePage(driver);
	public AdminUserListPage adminUserList = new AdminUserListPage(driver);
	public AdminAddNewUserPage addNewUser = new AdminAddNewUserPage(driver);
	public LoginPage loginPage = new LoginPage(driver);
	public ParentHomePage parentHomePage = new ParentHomePage(driver);
	public ParentAddParentDetailsPage addParentInfo = new ParentAddParentDetailsPage(driver);
	public ParentEditParentDetailsPage editParentInfoPage = new ParentEditParentDetailsPage(driver);
	public ParentAddChildDetailsPage addChildInfo = new ParentAddChildDetailsPage(driver);
	public ParentAddKindergartenSubmitionFormPage submitApplicationToKG = new ParentAddKindergartenSubmitionFormPage(
			driver);
	public EduKindergartenListPage kindergartenList = new EduKindergartenListPage(driver);
	public EduAddNewKindergartenPage addNewKindergarten = new EduAddNewKindergartenPage(driver);
	public EduHomePage eduHomePage = new EduHomePage(driver);
	public String userName = "Testas";
	public String userEmail = "test_" + new Random().nextInt(10000) + "@gmail.com";
	public String parentName = "Tevas";
	public String phoneNumber = String.format("+3706%07d", new Random().nextInt(10000000));
	public String childPersonCode = String.format("59%09d", new Random().nextInt(10000000));
	public String randomText = "Tekstas";
	public String kindergartenName = "DARZELIS "+ new Random().nextInt(1000);
	public String kindergartenAddress = "Gatve 1";
	public String updated = "updated";
	public String childName = "Vaikas";

	public void adminLogin() {
		
		loginPage.inputEmail("admin@test.lt");
		loginPage.inputPassword("admin");
		loginPage.clickButtonLogin();
	}

	public void registerParent() {
		adminLogin();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#root > div > div:nth-child(2) > div > div > aside > nav > ul > li:nth-child(2) > a")));
        adminHomePage.clickButtonUserList();
		adminUserList.clickbuttonAddNewUser();
		addNewUser.inputFirstName(userName);
		addNewUser.inputLastName(userName);
		addNewUser.inputEmail(userEmail);
		addNewUser.selectRole(1);
		addNewUser.clickbuttonRegister();
		WaitUtils.waitAndAcceptAlert(driver);

	}

	public void registerEdu() {

		adminLogin();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#root > div > div:nth-child(2) > div > div > aside > nav > ul > li:nth-child(2) > a")));
        adminHomePage.clickButtonUserList();
		adminUserList.clickbuttonAddNewUser();
		addNewUser.inputFirstName(userName);
		addNewUser.inputLastName(userName);
		addNewUser.inputEmail(userEmail);
		addNewUser.selectRole(2);
		addNewUser.clickbuttonRegister();
		WaitUtils.waitAndAcceptAlert(driver);

	}

	public void login() {
		loginPage.openLoginPage();
		loginPage.inputEmail(userEmail);
		loginPage.inputPassword(userName);
		loginPage.clickButtonLogin();
		driver.findElement(By.cssSelector("#myModal > div > div > div.modal-header > button > span")).click();
	}

	public void addParentDetails() {

		parentHomePage.clickButtonParentForm();
		addParentInfo.inputFirstName(parentName);
		addParentInfo.inputLastName(parentName);
		addParentInfo.inputEmail(userEmail);
		addParentInfo.inputPhone(phoneNumber);
		addParentInfo.inputPersonalCode(String.format("49%09d", new Random().nextInt(10000000)));
		addParentInfo.inputCity(randomText);
		addParentInfo.inputStreet(randomText);
		addParentInfo.inputHouseNumber(new Random().nextInt(100) + 1);
		addParentInfo.inputFlatNumber(new Random().nextInt(50) + 1);
		addParentInfo.inputNumberOfKids(new Random().nextInt(10) + 1);
		addParentInfo.checkStudying();
		addParentInfo.inputStudyingInstitution(randomText);
		addParentInfo.checkHasaDisability();
		addParentInfo.checkAgreeWithConditions();
		addParentInfo.checkAgreeWithRules();
		addParentInfo.checkDeclaredResidenceSameAsLiving();
		addParentInfo.clickButtonContinue();
		WaitUtils.waitAndAcceptAlert(driver);

	}

	public void editParentDetails() {

		editParentInfoPage.inputFirstName(updated + parentName);
		editParentInfoPage.inputEmail(updated + userEmail);
		editParentInfoPage.inputNumberOfKids(new Random().nextInt(10) + 1);
		editParentInfoPage.unCheckHasaDisability();
		editParentInfoPage.clickButtonUpdate();
		WaitUtils.waitAndAcceptAlert(driver);

	}

	public void addChildDetails() {

		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder(5);
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String randomEnding = sb.toString();
		childName = childName + randomEnding;

		parentHomePage.clickButtonChildForm();
		addChildInfo.inputChildFirstName(childName);
		addChildInfo.inputChildLastName(childName);
		addChildInfo.inputChildPersonCode(String.format("59%09d", new Random().nextInt(10000000)));
		addChildInfo.inputChildCity(randomText);
		addChildInfo.inputChildStreet(randomText);
		addChildInfo.inputChildHouseNumber(new Random().nextInt(100) + 1);
		addChildInfo.inputChildFlatNumber(new Random().nextInt(50) + 1);
		addChildInfo.checkAdopted();
		addChildInfo.checkAddSecondParent();
		addChildInfo.inputSecondParentFirstName(parentName);
		addChildInfo.inputSecondParentLastName(parentName);
		addChildInfo.inputSecondParentEmail(userEmail);
		addChildInfo.inputSecondParentPhone(phoneNumber);
		addChildInfo.inputSecondParentPersonCode(String.format("49%09d", new Random().nextInt(10000000)));
		addChildInfo.inputSecondParentCity(randomText);
		addChildInfo.inputSecondParentStreet(randomText);
		addChildInfo.inputSecondParentHouseNumber(new Random().nextInt(100) + 1);
		addChildInfo.inputSecondParentFlatNumber(new Random().nextInt(50) + 1);
		addChildInfo.inputSecondParentNumberOfKids(new Random().nextInt(10) + 1);
		addChildInfo.checkSecondParentStudying();
		addChildInfo.inputSecondParentStudyingInstitution(randomText);
		addChildInfo.checkSecondParentHasDisability();
		addChildInfo.checkSecondParentDeclaredResidenceSameAsLiving();
		addChildInfo.clickButtonSaveAndContinue();
		WaitUtils.waitAndAcceptAlert(driver);

	}

	public void addNewKindergarten() {

		eduHomePage.clickButtonAddNewKindergarten();
		addNewKindergarten.inputKindergartenName(kindergartenName);																				// darzu
		addNewKindergarten.inputKindergartenAddress(kindergartenAddress);
		addNewKindergarten.inputSpotsInFirstAgeGroup(new Random().nextInt(50));
		addNewKindergarten.inputSpotsInSecondAgeGroup(new Random().nextInt(50));
		addNewKindergarten.clickButtonRegister();
		WaitUtils.waitAndAcceptAlert(driver);

	}

	public void submitKindergartenApplication() {

		parentHomePage.clickButtonRegisterToKindergartenForm();
		WebElement element = parentHomePage.buttonRegisterToKindergartenForm;
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
		submitApplicationToKG.selectChild(childName + " " + childName);
		submitApplicationToKG.selectKindergarten1(1);
		submitApplicationToKG.selectKindergarten2(2);
		submitApplicationToKG.selectKindergarten3(3);
		submitApplicationToKG.selectKindergarten4(4);
		submitApplicationToKG.selectKindergarten5(5);
		submitApplicationToKG.clickButtonSubmit();
		WaitUtils.waitAndAcceptAlert(driver);

	}

}
