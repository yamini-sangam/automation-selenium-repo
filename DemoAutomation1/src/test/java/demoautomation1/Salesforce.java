package demoautomation1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Salesforce {

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
        WebElement searchInput = driver.findElement(By.xpath("//label[contains(text(),'Search apps and items...')]/following::div/input[@aria-autocomplete=\"list\"]"));
        searchInput.sendKeys("Leads");
        Thread.sleep(1000);
        searchInput.sendKeys(Keys.ENTER); // Press Enter after sending the text
        Thread.sleep(2000); // Add some wait time for the search results to load
        driver.findElement(By.xpath("//span[contains(text(),'Recently Viewed')]/preceding::h1")).click();
        Thread.sleep(1000);
        WebElement closeButton = driver.findElement(By.cssSelector("button[data-action='close']"));
        closeButton.click();
        driver.quit(); 
    }

}
