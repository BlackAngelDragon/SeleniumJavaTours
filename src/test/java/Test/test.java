package Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import helpers.ScreenShooter;
import helpers.WebDriverManager;
import pages.pageLogin;
import pages.pageLogon;
import pages.pageReservation;

public class test {
	private WebDriver driver;
	ArrayList<String> tabs;
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("http://www.newtours.demoaut.com/");
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor)driver;
		String googleWindow = "window.open('http://www.google.com')";
		javaScriptExecutor.executeScript(googleWindow);
		tabs = new ArrayList<String> (driver.getWindowHandles());
		}
	@Test
	public void pruebaUno() {
		WebDriverManager.setWindowSize(driver, "fullscreen");
		driver.switchTo().window(tabs.get(1)).navigate().to("http://www.youtube.com/user/Draculinio");
		driver.switchTo().window(tabs.get(0));
		pageLogon PageLogon = new pageLogon(driver);
		pageLogin PageLogin = new pageLogin(driver);
		PageLogin.login("user", "user");
		PageLogon.assertLogonPage();
	}
	@Test 
	public void pruebaDos() {
		WebDriverManager.setWindowSize(400,400,driver);
		pageReservation PageReservation = new pageReservation(driver);
		pageLogin PageLogin = new pageLogin(driver);
		PageLogin.login("mercury", "mercury");
		PageReservation.assertPage();
		
		}
	@Test
	public void pruebaTres() {
		pageReservation PageReservation = new pageReservation(driver);
		pageLogin PageLogin = new pageLogin(driver);
		PageLogin.login("mercury", "mercury");
		PageReservation.selectPassenger(1);
		PageReservation.selectOrigen(1);
		PageReservation.selectToPort("London");
	}
	@Test
	public void pruebaLoginByFields() {
		pageLogin PageLogin = new pageLogin(driver);
		PageLogin.fieldsLogin("user", "user");
	}
	@Test 
	public void prubaCantidadDeCampos() {
		pageLogin PageLogin = new pageLogin(driver);
		PageLogin.verifyFields();
	}
	@AfterMethod
	public void teardown(ITestResult result) {
		if(!result.isSuccess()) {
			ScreenShooter.takeScreenshoot("Error",driver);
		}
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0)).close();
	}
	
}
