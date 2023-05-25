package controlador;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumApp {
	
	public static WebDriver driver;

	public static void main(String[] args) {
		
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://www.saucedemo.com");
		
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		/*String texto = driver.findElement(By.className("error-message-container")).getText();
		System.out.println(texto);*/
		
		String texto = driver.findElement(By.className("app_logo")).getText();
		
		if(texto.equals("Swag Labs")) {
			System.out.println("Logueado correctamente");
		}

		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/*driver.quit();*/ //Para que no lo cierre comentarlo.
		
	}

}
