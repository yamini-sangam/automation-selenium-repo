package demoautomation1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.Select;

public class Salesforce1 {

	public static void main(String[] args) throws Exception {
		WebDriverManager.edgedriver().setup();

		EdgeOptions options = new EdgeOptions();

		// Disable notifications
		options.addArguments("--disable-notifications");

		WebDriver driver = new EdgeDriver(options);
		
		driver.get("https://login.salesforce.com/?locale=in");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("sdunna@miraclesoft.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Ramyadunna@7");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='slds-r5']")).click();
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement inputElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(),'Search apps and items...')]/following::div/input[@aria-autocomplete='list']")));

		driver.findElement(By.xpath("//label[contains(text(),'Search apps and items...')]/following::div/input[@aria-autocomplete=\"list\"]"))
		.sendKeys("Leads", Keys.ENTER);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//b[normalize-space()='Leads']")).click();
        Thread.sleep(1000);
        
        Select s = new Select(driver.findElement(By.xpath("//button[@title='Select a List View: Leads']")));
        s.selectByVisibleText("All open leads");
        Thread.sleep(1000);

		driver.findElement(By.xpath("//div[@class='slds-context-bar__label-action slds-p-left--none slds-p-right--x-small']//lightning-primitive-icon[@size='xx-small']//*[name()='svg']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Recently Viewed')]/preceding::h1")).click();
		Thread.sleep(1000);
		WebElement closeButton = driver.findElement(By.cssSelector("button[data-action='close']"));
		closeButton.click();
//		driver.findElement(By.xpath("//b[normalize-space()='Leads']")).click();
		
		
		
		
		driver.quit(); // Close the browser session
		}

	
	
}
