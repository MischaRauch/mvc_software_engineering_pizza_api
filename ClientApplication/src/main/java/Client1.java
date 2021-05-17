import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Client1 {
private static HttpURLConnection connection;
    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            URL url = new URL("http://localhost:8080/api/v1/pizza");
            connection = (HttpURLConnection) url.openConnection();

        } catch (MalformedURLException e ){
            e.printStackTrace();
        }
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000); //if after 5 seconds the connection is not reached, is timeout
        connection.setReadTimeout(5000);
        int status = connection.getResponseCode();
        System.out.println(status);

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        //read the response
        if (status > 299){ //it means theres an error
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }

        while ((line = reader.readLine())!= null){
            responseContent.append(line);
        }
        reader.close();
        //System.out.println(responseContent.toString());
        parse (responseContent.toString());
        connection.disconnect();

    }

    public static String parse(String responseBody){
        JSONArray albums = new JSONArray(responseBody);
        for (int i=0; i<albums.length(); i++){
            JSONObject album = albums.getJSONObject(i);
            JSONArray toppings = album.getJSONArray("toppings");
            int pizza_id = album.getInt("pizza_id");
            boolean vegetarian = album.getBoolean("vegetarian");
            double price = album.getDouble("price");
            String name = album.getString("name");
            System.out.println("pizza_id: "  + pizza_id +  " " + pizza_id + " " + vegetarian);
        }
        return null;
    }
}

