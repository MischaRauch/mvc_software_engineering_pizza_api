import netscape.javascript.JSObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.*;

public class Client2 {

    public static void main (String [] args){
    }

    public static void getMenu (){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/api/v1/pizza")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                //.thenApply(Client2::parse)
                .thenAccept(System.out::println)
                .join();
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
            System.out.println(toppings + " " + pizza_id + " " + vegetarian);
        }
        return null;
    }

}
