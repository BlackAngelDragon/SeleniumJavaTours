package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class pageReservation {
	private WebDriver driver;
	private By titleText;
	private By passengersDrop;
	private By departureDrop;
	private By toDrop;
	public pageReservation(WebDriver driver) {
		this.driver=driver;
		titleText = By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/font");
		passengersDrop = By.name("passCount");
		departureDrop = By.name("fromPort");
		toDrop = By.name("toPort");
	}
	public void assertPage() {
		Assert.assertTrue(driver.findElement(titleText).getText().contains("Use our Flight Finder"));
	}
	
	public void selectPassenger(int cant) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement cantidadPasajeros = wait.until(ExpectedConditions.presenceOfElementLocated(passengersDrop));
		Select selectPasajeros = new Select(driver.findElement(passengersDrop));
		selectPasajeros.selectByVisibleText(Integer.toString(cant));
	}
	public void selectOrigen(int indice) {
		Select selectOrigen = new Select(driver.findElement(departureDrop));
		selectOrigen.selectByIndex(indice);
	}
	public void selectToPort(String city) {
		Select selectTo = new Select(driver.findElement(toDrop));
		selectTo.selectByValue(city);
	}
}
