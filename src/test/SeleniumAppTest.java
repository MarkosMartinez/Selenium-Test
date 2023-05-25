package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumAppTest {

	public static WebDriver driver;
	
	@BeforeAll
	static void before_all() {
		//firefoxekin
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	
	//Se ejecuta antes de cada @Test
	@BeforeEach
	void before_each() {
		driver.get("https://www.saucedemo.com");
	}
	
	@Test
	@DisplayName("Login con usuario correcto")
	void test1() {
			driver.findElement(By.id("user-name")).sendKeys("standard_user");
			driver.findElement(By.id("password")).sendKeys("secret_sauce");
			driver.findElement(By.id("login-button")).click();
			
			String products = "";
			products = driver.findElement(By.className("title")).getText();
			
			assertEquals("Products", products);
		}
	
	@Test
	@DisplayName("Login con usuario incorrecto")
	void test2() {
			driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
			driver.findElement(By.id("password")).sendKeys("secret_sauce");
			driver.findElement(By.id("login-button")).click();
			
			String mensajeError = "";
			mensajeError = driver.findElement(By.className("error-message-container")).getText();
			
			assertTrue(mensajeError.contains("locked out"));
		}
	
	//Se ejecuta despues de cada tests
	@AfterEach
	void after_each() {
		driver.manage().deleteAllCookies();
	}
}
