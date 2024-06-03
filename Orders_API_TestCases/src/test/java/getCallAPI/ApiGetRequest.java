package getCallAPI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiGetRequest {
    public static void main(String[] args) {
        try {
            // Define the URL for the API endpoint
            String apiUrl = "http://localhost:8081/helloWorld";

            // Create a URL object
            URL url = new URL(apiUrl);
            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // If response code is 200 (HTTP_OK), read the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print the response
                System.out.println("Response: " + response.toString());
            } else {
                System.out.println("GET request failed");
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
