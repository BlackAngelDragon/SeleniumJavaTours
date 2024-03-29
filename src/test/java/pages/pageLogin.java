package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class pageLogin {
	private WebDriver driver;
	private By userField;
	private By passwordField;
	private By loginButton;
	private By fields;
	public pageLogin(WebDriver driver) {
		this.driver = driver;
		userField = By.name("userName");
		passwordField = By.name("password");
		loginButton = By.name("login");
		fields = By.tagName("input");
	}
	public void login(String user,String pass) {
		driver.findElement(userField).sendKeys(user);
		driver.findElement(passwordField).sendKeys(pass);
		driver.findElement(loginButton).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	public void fieldsLogin(String user,String pass) {
		//driver.manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);
		List<WebElement> loginFields = driver.findElements(fields);
		loginFields.get(1).sendKeys(user);
		loginFields.get(2).sendKeys(pass);
		//driver.manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);
	}
	public void verifyFields() {
		List<WebElement> loginFields = driver.findElements(fields);
		System.out.println(loginFields.size());
		Assert.assertTrue(loginFields.size()==5);
	}
}