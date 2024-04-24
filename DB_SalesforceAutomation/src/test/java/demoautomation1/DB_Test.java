package demoautomation1;

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
                    jsonObj.put("name", res.getString(1));
                    jsonObj.put("email", res.getString(2));
                    jsonObj.put("phoneno", res.getString(3));
                    jsonObj.put("gender", res.getString(4));
                    jsonObj.put("address", res.getString(5));
                    jsonObj.put("state&city", res.getString(6));
                    jsonArray.put(jsonObj);
                }

                // Convert JSON array to JSON string
                String jsonOutput = jsonArray.toString(2);
                System.out.println(jsonOutput);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
