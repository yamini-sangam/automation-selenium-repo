package demoautomation1;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

public class DB_Test {
    public static void main(String[] args) throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/automationtesting", "root", "root")) {
            String sql = "select * from userregistration";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet res = stmt.executeQuery(sql);

                JSONArray jsonArray = new JSONArray();
                while (res.next()) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("Name", res.getString("Name"));
                    jsonObj.put("Gender", res.getString("Gender"));
                    jsonObj.put("Phoneno", res.getString("Phone"));
                    jsonObj.put("Email", res.getString("Email"));
                    jsonObj.put("Address", res.getString("Address"));
                    jsonObj.put("City&State", res.getString("City&State"));
                    jsonArray.put(jsonObj);
                }

                // Convert JSON array to JSON string
                String jsonOutput = jsonArray.toString(2);
                System.out.println(jsonOutput);

                // Write JSON string to a file
                String filePath = "D:/databaseresponse.json"; // Change the file path as needed
                try (FileWriter fileWriter = new FileWriter(filePath)) {
                    fileWriter.write(jsonOutput);
                    System.out.println("JSON data written to file: " + filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
