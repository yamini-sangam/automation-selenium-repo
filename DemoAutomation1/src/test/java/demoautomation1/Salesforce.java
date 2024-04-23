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
Thread.sleep(4000);
driver.findElement(By.xpath("//div[@data-aura-rendered-by=\"440:83;a\"]")).click();
Thread.sleep(4000);
WebElement searchInput = driver.findElement(By.xpath("//input[@placeholder=\"Search apps and items...\"]"));
searchInput.sendKeys("Leads");
Thread.sleep(1000);
searchInput.sendKeys(Keys.ENTER); // Press Enter after sending the text
Thread.sleep(3000);
WebElement dropdownButton = driver.findElement(By.cssSelector("button[title='Select a List View: Leads']"));
dropdownButton.click();
Thread.sleep(2000); // Wait for 2 seconds

// Click on the "All Open Leads" option directly
driver.findElement(By.xpath("//span[text()='All Open Leads']")).click();
Thread.sleep(2000);
WebElement actionLink = driver.findElement(By.cssSelector("a[title='Show 3 more actions']"));
actionLink.click();
Thread.sleep(2000);
WebElement printableViewLink = driver.findElement(By.cssSelector("a[title='Printable View']"));
printableViewLink.click();

}
}