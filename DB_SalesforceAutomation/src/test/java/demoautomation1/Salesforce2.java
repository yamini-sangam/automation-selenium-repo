package demoautomation1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeOptions;
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
import java.util.ArrayList;

public class Salesforce2 {


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
Thread.sleep(3000);
searchInput.sendKeys(Keys.ENTER); // Press Enter after sending the text
Thread.sleep(1000);
LeadDetails(driver);
System.out.println("Before opening All Leads First Column values" +LeadDetails(driver));
Thread.sleep(3000);
driver.get("https://miraclesoftwaresystems-f-dev-ed.lightning.force.com/lightning/o/Lead/list?filterName=00B5j000007g8cSEAQ");
System.out.println("after opening All Leads First Column values "+LeadDetails(driver));

//driver.findElement(By.xpath("//a[@title='Printable View']")).click();
//Thread.sleep(3000);
}


public static ArrayList LeadDetails(WebDriver driver ) throws InterruptedException {
ArrayList leadInformation=new ArrayList();
Thread.sleep(5000); // Add some wait time for the search results to load
String name =driver.findElement(By.xpath("(//a[@data-refid=\"recordId\"])[1]")).getText();
Thread.sleep(2000);

String company = driver.findElement(By.xpath("(//span[@data-aura-class=\"uiOutputText\"]/preceding::td)[4]")).getText();
Thread.sleep(2000);

String PhoneNumber= driver.findElement(By.xpath("(//span[@data-aura-class='uiOutputText']/preceding::td)[5]")).getText();
Thread.sleep(2000);

Thread.sleep(2000);
String Email=driver.findElement(By.xpath("(//span[@data-aura-class='uiOutputText']/preceding::td)[7]")).getText();
Thread.sleep(2000);

String leadStatus=driver.findElement(By.xpath("(//span[@data-aura-class='uiOutputText']/preceding::td)[8]")).getText();
Thread.sleep(2000);

String OwnerAlais=driver.findElement(By.xpath("(//span[@data-aura-class='uiOutputText']/preceding::td)[9]")).getText();
Thread.sleep(2000);


leadInformation.add("name of the Lead ="+name);
leadInformation.add("name of the company ="+company);
leadInformation.add("name of the PhoneNumber ="+PhoneNumber);
leadInformation.add("name of the Eamil ="+Email);
leadInformation.add("leadStatus="+leadStatus);
leadInformation.add("OwnerAlais ="+OwnerAlais);


return leadInformation;
}
}
	
	

