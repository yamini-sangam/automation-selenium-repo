package demoautomation1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class Tabledata {

    public static ArrayList<String> tabledata(WebDriver driver) {
        ArrayList<String> leadName = new ArrayList<>();
        List<WebElement> Leadnames = driver.findElements(By.xpath("//tr/td[@class=\"slds-cell-edit slds-cell-error errorColumn cellContainer\"]/following::th"));
        for (int index = 1; index <= Leadnames.size() - 1; index++) {
            String Leadnametext = driver.findElement(By.xpath("//tr/td[@class=\"slds-cell-edit slds-cell-error errorColumn cellContainer\"]/following::th[" + index + "]")).getText();
            leadName.add(Leadnametext);
            System.out.println();
        }
        return leadName;
    }

    public static ArrayList<String> tableDetails(WebDriver driver, ArrayList<String> Lead) {
        ArrayList<String> totaldata = new ArrayList<>();
        for (String lead : Lead) {
            String CompanyText = driver.findElement(By.xpath("(//a[@data-refid='recordId' and text()='" + lead + "']/following::a)[1]")).getText();
            String State = driver.findElement(By.xpath("(//a[@data-refid='recordId' and text()='" + lead + "']/following::a)[1]/following::td[1]")).getText();
            String Email = driver.findElement(By.xpath("(//a[@data-refid='recordId' and text()='" + lead + "']/following::a)[1]/following::td[2]")).getText();
            String leadStatus = driver.findElement(By.xpath("(//a[@data-refid='recordId' and text()='" + lead + "']/following::a)[1]/following::td[3]")).getText();
            String CreatedDate = driver.findElement(By.xpath("(//a[@data-refid='recordId' and text()='" + lead + "']/following::a)[1]/following::td[4]")).getText();
            String OwnerAlias = driver.findElement(By.xpath("(//a[@data-refid='recordId' and text()='" + lead + "']/following::a)[1]/following::td[5]")).getText();
            String Phone = driver.findElement(By.xpath("(//a[@data-refid='recordId' and text()='" + lead + "']/following::a)[1]/following::td[7]")).getText();
            String Salutation = driver.findElement(By.xpath("(//a[@data-refid='recordId' and text()='" + lead + "']/following::a)[1]/following::td[8]")).getText();
            String Street = driver.findElement(By.xpath("(//a[@data-refid='recordId' and text()='" + lead + "']/following::a)[1]/following::td[9]")).getText();
            String City = driver.findElement(By.xpath("(//a[@data-refid='recordId' and text()='" + lead + "']/following::a)[1]/following::td[10]")).getText();
            String jsonString = "{\"Salutation\":\"" + Salutation + "\",\"Name\":\"" + lead + "\",\"Company\":\"" + CompanyText + "\",\"Address\":\"" + Street + "\",\"City\":\"" + City + "\",\"State/Province\":\"" + State + "\",\"Phoneno\":\"" + Phone + "\",\"Email\":\"" + Email + "\",\"Lead Status\":\"" + leadStatus + "\",\"Created Date\":\"" + CreatedDate + "\",\"Owner Alias\":\"" + OwnerAlias + "\"}";
            totaldata.add(jsonString);
        }
        return totaldata;
    }

    public static ArrayList<String> NumberofrequiredProfiles(int i, ArrayList<String> totalrecords) {
        ArrayList<String> profiles = new ArrayList<>();
        for (int r = 0; r < i && r < totalrecords.size(); r++) {
            profiles.add(totalrecords.get(r));
        }
        return profiles;
    }
}
