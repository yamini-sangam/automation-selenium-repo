package demoautomation1;

import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_Ng_Demo {
    
    @Test
    public void testDataValidation() {
        // Load JSON data from files
        JSONArray jsonArray1 = readJSONFile("D:/databaseresponse.json");
        JSONArray jsonArray2 = readJSONFile("D:/salesforce.json");

        // Compare records and perform validations
        boolean matchFound = false;
        try {
            matchFound = compareData(jsonArray1, jsonArray2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Assertion for data validation
        Assert.assertTrue(matchFound, "Data validation failed: Details do not match.");
    }

    // Function to read JSON file and return JSONArray
    public static JSONArray readJSONFile(String filePath) {
        JSONArray jsonArray = null;
        try {
            FileReader reader = new FileReader(filePath);
            StringBuilder jsonString = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                jsonString.append((char) character);
            }
            jsonArray = new JSONArray(jsonString.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    // Function to compare data from two JSONArrays
    public static boolean compareData(JSONArray jsonArray1, JSONArray jsonArray2) throws Exception {
        boolean matchFound = false;
        for (int i = 0; i < jsonArray1.length(); i++) {
            JSONObject obj1 = jsonArray1.getJSONObject(i);
            String name1 = obj1.getString("Name");

            for (int j = 0; j < jsonArray2.length(); j++) {
                JSONObject obj2 = jsonArray2.getJSONObject(j);
                String name2 = obj2.getString("Name");

                if (name1.equalsIgnoreCase(name2)) {
                    // Names match, perform validations
                    matchFound = compareRemainingFields(obj1, obj2);
                    break; // Break inner loop once match found
                }
            }
        }
        return matchFound;
    }

    // Function to compare remaining fields of matching records
    public static boolean compareRemainingFields(JSONObject obj1, JSONObject obj2) {
   
        Assert.assertEquals(obj1.optString("Email", ""), obj2.optString("Email", ""), "Emails do not match.");

        String phone1 = convertPhoneNumber(obj1.optString("Phoneno", ""));
        String phone2 = convertPhoneNumber(obj2.optString("Phoneno", ""));
        Assert.assertEquals(phone1, phone2, "Phone numbers do not match.");
        
        Assert.assertEquals(obj1.optString("Address", ""), obj2.optString("Address", ""), "Addresses do not match.");
        
        String gender2 = getGenderFromSalutation(obj2.optString("Salutation", ""));
        Assert.assertEquals(obj1.optString("Gender", ""), gender2, "Addresses do not match.");
        
        String city1 = obj1.optString("City&State", "").split("&")[0]; // Extract city from "City&State"
        String state1 = obj1.optString("City&State", "").split("&")[1]; // Extract state from "City&State"
        String city2 = obj2.optString("City", "");
        String state2 = obj2.optString("State/Province", "");

        Assert.assertEquals(city1, city2, "Cities do not match.");
        Assert.assertEquals(state1, state2, "States/Provinces do not match.");

        return true; // All details match
    }

    // Utility method to convert phone number format
    public static String convertPhoneNumber(String phoneNumber) {
        // Remove non-numeric characters
        return phoneNumber.replaceAll("[^\\d]", "");
    }
    // Utility method to get gender from salutation
    public static String getGenderFromSalutation(String salutation) {
        if (salutation.equalsIgnoreCase("Mr.")) {
            return "Male";
        } else if (salutation.equalsIgnoreCase("Ms.")) {
            return "Female";
        } else {
            return "";
        }
    }
}