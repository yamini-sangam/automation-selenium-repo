package demoautomation1;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Data_Validation {
    public static void main(String[] args) {
        try {
            // Read JSON files
            JSONArray jsonArray1 = readJSONFile("D:/databaseresponse.json");
            JSONArray jsonArray2 = readJSONFile("D:/salesforce.json");

            // Compare records based on name
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject obj1 = jsonArray1.getJSONObject(i);
                String name1 = obj1.getString("Name");

                for (int j = 0; j < jsonArray2.length(); j++) {
                    JSONObject obj2 = jsonArray2.getJSONObject(j);
                    String name2 = obj2.getString("Name");

                    if (name1.equalsIgnoreCase(name2)) {
                        // Names match, now compare remaining fields
                        System.out.println("Matching record found for: " + name1);
                        compareRemainingFields(obj1, obj2);
                        break; // Break inner loop once match found
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to read JSON file and return JSONArray
    public static JSONArray readJSONFile(String filePath) throws Exception {
        FileReader reader = new FileReader(filePath);
        StringBuilder jsonString = new StringBuilder();
        int character;
        while ((character = reader.read()) != -1) {
            jsonString.append((char) character);
        }
        return new JSONArray(jsonString.toString());
    }

    // Function to compare remaining fields of matching records
    public static void compareRemainingFields(JSONObject obj1, JSONObject obj2) {
        // Compare and print remaining fields here
        List<String> mismatches = new ArrayList<>();

        String phone1 = obj1.optString("Phoneno", "");
        String phone2 = obj2.optString("Phoneno", "");

        // Convert phone numbers to numeric format
        phone1 = convertPhoneNumber(phone1);
        phone2 = convertPhoneNumber(phone2);

        if (!phone1.equals(phone2)) {
            mismatches.add("Phone numbers do not match.");
        }

        String email1 = obj1.optString("Email", "");
        String email2 = obj2.optString("Email", "");
        if (!email1.equalsIgnoreCase(email2)) {
            mismatches.add("Emails do not match.");
        }

        // Compare Gender and Salutation
        String gender1 = obj1.optString("Gender", "");
        String gender2 = getGenderFromSalutation(obj2.optString("Salutation", ""));
        
        if (!gender1.equalsIgnoreCase(gender2)) {
            mismatches.add("Gender does not match.");
        }
        

        // Compare Gender and Salutation
        String address1 = obj1.optString("Address", "");
        String address2 = obj2.optString("Address", "");
        
        if (!address1.equalsIgnoreCase(address2)) {
            mismatches.add("Address does not match.");
        }

        // Compare City and State/Province
        String city1 = obj1.optString("City&State", "").split("&")[0]; // Extract city from "City&State"
        String state1 = obj1.optString("City&State", "").split("&")[1]; // Extract state from "City&State"
        String city2 = obj2.optString("City", "");
        String state2 = obj2.optString("State/Province", "");

        if (!city1.equalsIgnoreCase(city2)) {
            mismatches.add("City does not match.");
        }

        if (!state1.equalsIgnoreCase(state2)) {
            mismatches.add("State/Province does not match.");
        }

        if (!mismatches.isEmpty()) {
            System.out.println("Mismatched details:");
            for (String mismatch : mismatches) {
                System.out.println("- " + mismatch);
            }
        } else {
            System.out.println("All details are matched");
        }
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
