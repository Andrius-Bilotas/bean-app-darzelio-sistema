package parentpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import pages.AbstractPage;

public class ParentAddKindergartenSubmitionFormPage extends AbstractPage {

	/**
	 * Page where parent selects the kindergartens and submits the application for
	 * his child
	 */

	// dropdown selections
	@FindBy(id = "selectChild")
	private WebElement selectChild;

	@FindBy(id = "selectKindergarten1")
	private WebElement selectKindergarten1;

	@FindBy(id = "selectKindergarten2")
	private WebElement selectKindergarten2;

	@FindBy(id = "selectKindergarten3")
	private WebElement selectKindergarten3;

	@FindBy(id = "selectKindergarten4")
	private WebElement selectKindergarten4;

	@FindBy(id = "selectKindergarten5")
	private WebElement selectKindergarten5;

	// buttons
	@FindBy(xpath = "//*[@class=\"mr-4 btn\"]")
	private WebElement buttonSubmit;

	public ParentAddKindergartenSubmitionFormPage(WebDriver driver) {
		super(driver);

	}

	public void selectChild(String text) {
		Select dropdown = new Select(selectChild);
		dropdown.selectByVisibleText(text);
	}

	public void selectKindergarten1(int index) {
		Select dropdown = new Select(selectKindergarten1);
		dropdown.getOptions().get(index).click();
	}

	public void selectKindergarten2(int index) {
		Select dropdown = new Select(selectKindergarten2);
		dropdown.getOptions().get(index).click();
	}

	public void selectKindergarten3(int index) {
		Select dropdown = new Select(selectKindergarten3);
		dropdown.getOptions().get(index).click();
	}

	public void selectKindergarten4(int index) {
		Select dropdown = new Select(selectKindergarten4);
		dropdown.getOptions().get(index).click();
	}

	public void selectKindergarten5(int index) {
		Select dropdown = new Select(selectKindergarten5);
		dropdown.getOptions().get(index).click();
	}

	public void clickButtonSubmit() {
		buttonSubmit.click();
	}
}
