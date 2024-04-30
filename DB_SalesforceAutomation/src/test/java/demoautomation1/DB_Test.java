package demoautomation1;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;

public class DB_Test {
    public static void main(String[] args) {
        String sqlQuery = "SELECT JSON_ARRAYAGG(JSON_OBJECT('Name', Name, 'Gender', Gender, 'Phoneno', Phone, 'Email', Email, 'Address', Address, 'City&State', `City&State`)) AS json_data " +
                          "FROM userregistration";
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/automationtesting", "root", "root")) {
            try (PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
                ResultSet res = stmt.executeQuery();

                if (res.next()) {
                    String jsonData = res.getString("json_data");
                    JSONArray jsonArray = new JSONArray(jsonData);
                    String formattedJson = jsonArray.toString(2);

                    // Write formatted JSON string to a file
                    String filePath = "D:/databaseresponse.json"; // Change the file path as needed
                    try (FileWriter fileWriter = new FileWriter(filePath)) {
                        fileWriter.write(formattedJson);
                        System.out.println("JSON data written to file : " + filePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("No data retrieved from the database.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
