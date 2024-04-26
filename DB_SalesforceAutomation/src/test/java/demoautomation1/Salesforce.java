package demoautomation1;

import java.io.FileWriter;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeOptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Salesforce extends Tabledata {

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
Thread.sleep(2000);
ArrayList<String> leads = tabledata(driver);
ArrayList<String> totalrecords = tableDetails(driver, leads);
ArrayList<String> requiredProfiles = NumberofrequiredProfiles(4, totalrecords);

// Create a JSON array to hold all JSON objects
JsonArray jsonArray = new JsonArray();

// Convert each JSON string to a JSON object and add to the JSON array
Gson gson = new GsonBuilder().setPrettyPrinting().create();
for (String profile : requiredProfiles) {
JsonObject jsonObject = gson.fromJson(profile, JsonObject.class);
jsonArray.add(jsonObject);
}


// Print the JSON array with proper indentation
String formattedJson = gson.toJson(jsonArray);
System.out.println(formattedJson);

String filePath = "D:/salesforce.json"; // Change the file path as needed
try (FileWriter fileWriter = new FileWriter(filePath)) {
fileWriter.write(formattedJson);
System.out.println("JSON data written to file: " + filePath);
} catch (Exception e) {
e.printStackTrace();
}

// Close the WebDriver session
driver.quit();
}

}