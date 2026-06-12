package Testing_practice;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import POM_Test.LoginPage;

public class SauceDemoE2E {
	WebDriver driver;
	LoginPage lp;
	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		//options.addArguments("--disable-save-password-bubble");
		Map<String,Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.passsowrd_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com");
		lp = new LoginPage(driver);
		
	}
	@Test(priority=1)
	public void login() {
		lp.login("standard_user", "secret_sauce");
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Swag"));
		System.out.println("login successful");
		
	}
	@Test(priority = 2)
	public void product_details() {
		driver.findElement(By.id("item_4_title_link")).click();
		String product = driver.findElement(By.className("inventory_details_name")).getText();
		System.out.println(product);
		Assert.assertTrue(product.contains("Backpack"));
		driver.navigate().back();
	}
	
	@Test(priority=3)
	public void add_to_cart() {
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
		Assert.assertEquals(cartCount,  "1");
		System.out.println("product added to cart");
	}
	@Test(priority = 4)
	public void delete_cart_item() {
		driver.findElement(By.id("remove-sauce-labs-backpack")).click();
		System.out.println("cart item removed");
	}
	@Test(priority = 5)
	public void filter_button() {
		WebElement filter = driver.findElement(By.className("product_sort_container"));
		filter.click();
		driver.findElement(By.xpath("//option[text()='Price (low to high)']")).click();
		System.out.println("filter applies");
	}
  @Test(priority = 6)
  public void left_menu_button() throws InterruptedException {
	  driver.findElement(By.id("react-burger-menu-btn")).click();
	  Thread.sleep(3000);
	  Assert.assertTrue(driver.findElement(By.id("logout_sidebar_link")).isDisplayed());
//	  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//	  WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
	  System.out.println("Left menu opened");
  }
  @Test(priority = 7)
  public void multiple_search() {
	  int totalProducts = driver.findElements(By.className("inventory_item")).size();
	  System.out.println("Total Products"+totalProducts);
	  Assert.assertTrue(totalProducts>0);
  }
  @AfterClass
  public void closeBrowser() {
	  driver.quit();
	  System.out.println("Execution completed");
  }
}
