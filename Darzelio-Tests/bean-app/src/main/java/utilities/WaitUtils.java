package utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	/**
	 * This is a wait for accepting the confirmation alert
	 */
	
	public static void waitAndAcceptAlert(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}
}
