package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
	protected WebDriver driver;

	/**
	 * Abstract page with the driver, to be extended by general pages
	 */
	
	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
