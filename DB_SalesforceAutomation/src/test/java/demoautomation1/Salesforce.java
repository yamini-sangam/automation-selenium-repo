package demoautomation1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Salesforce {

	public static void main(String[] args) throws Exception {

	//	WebDriver driver= null;
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
		//driver =new ChromeDriver();
		
         driver.get("https://login.salesforce.com/?locale=in");
		
        driver.manage().window().maximize();
	          
	          driver.findElement(By.xpath("//input[@id='username']")).sendKeys(" sdunna@miraclesoft.com");
				 Thread.sleep(1000);
				
				 driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Ramyadunna@7");
				 Thread.sleep(1000);
				  
				 driver.findElement(By.xpath("//input[@id='Login']")).click();
				 Thread.sleep(1000);
				 

				 
				 driver.findElement(By.xpath("//div[@class='slds-r5']")).click();
				 Thread.sleep(1000);
				 
				 driver.findElement(By.xpath("//label[contains(text(),'Search apps and items...')]/following::div/input[@aria-autocomplete=\"list\"]")).sendKeys("Leads",Keys.ENTER);
				 Thread.sleep(1000);
				 // driver.findElement(By.xpath("//a[@href=\"/lightning/o/Lead/home\"]")).click();
				 Thread.sleep(1000);
				 driver.findElement(By.xpath("//span[contains(text(),'Recently Viewed')]/preceding::h1")).click();
				 Thread.sleep(1000);
				 WebElement closeButton = driver.findElement(By.cssSelector("button[data-action='close']")); // Example CSS selector
				 closeButton.click();

	      	

				 
				 
				
				 
				
				 
				 
				
		
	}

}
