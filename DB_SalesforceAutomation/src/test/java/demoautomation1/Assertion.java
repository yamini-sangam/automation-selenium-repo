package demoautomation1;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileReader;

public class Assertion {
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
                        if (compareRemainingFields(obj1, obj2)) {
                            System.out.println("All details are matched");
                        }
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
    public static boolean compareRemainingFields(JSONObject obj1, JSONObject obj2) {
        // Compare and print remaining fields here
        String phone1 = obj1.optString("Phoneno", "");
        String phone2 = obj2.optString("Phoneno", "");

        // Convert phone numbers to numeric format
        phone1 = convertPhoneNumber(phone1);
        phone2 = convertPhoneNumber(phone2);

        if (!phone1.equals(phone2)) {
            System.out.println("Phone numbers do not match.");
            return false;
        }

        String email1 = obj1.optString("Email", "");
        String email2 = obj2.optString("Email", "");
        if (!email1.equalsIgnoreCase(email2)) {
            System.out.println("Emails do not match.");
            return false;
        }

        // Compare Gender and Salutation
        String gender1 = obj1.optString("Gender", "");
        String gender2 = getGenderFromSalutation(obj2.optString("Salutation", ""));
        
        if (!gender1.equalsIgnoreCase(gender2)) {
            System.out.println("Gender does not match.");
            return false;
        }

        // Compare City and State/Province
        String city1 = obj1.optString("City&State", "").split("&")[0]; // Extract city from "City&State"
        String state1 = obj1.optString("City&State", "").split("&")[1]; // Extract state from "City&State"
        String city2 = obj2.optString("City", "");
        String state2 = obj2.optString("State/Province", "");

        if (!city1.equalsIgnoreCase(city2)) {
            System.out.println("City does not match.");
            return false;
        }

        if (!state1.equalsIgnoreCase(state2)) {
            System.out.println("State/Province does not match.");
            return false;
        }

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