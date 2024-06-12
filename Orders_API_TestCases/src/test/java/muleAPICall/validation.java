package muleAPICall;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

public class validation {

    public static void main(String[] args) {
        try {
            // Make the API call
            String apiUrl = "http://localhost:8081/helloWorld";  // Replace with your actual API endpoint
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Get the response
            int responseCode = con.getResponseCode();
            if (responseCode == 200) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the response
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Validate the response
                boolean successful = jsonResponse.getBoolean("successful");
                if (successful) {
                    JSONArray items = jsonResponse.getJSONArray("items");
                    if (items.length() > 0) {
                        JSONObject item = items.getJSONObject(0);
                        JSONObject payload = item.getJSONObject("payload");
                        boolean success = payload.getBoolean("success");

                        // Validate the success scenario
                        if (success) {
                            System.out.println("Success scenario validated successfully!");
                        } else {
                            System.out.println("Success scenario validation failed!");
                        }
                    } else {
                        System.out.println("No items found in the response.");
                    }
                } else {
                    // Validate the failure scenario
                    System.out.println("Failure scenario validated successfully!");
                }

            } else {
                System.out.println("GET request failed. Response code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
