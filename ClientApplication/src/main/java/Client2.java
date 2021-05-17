import netscape.javascript.JSObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import org.json.*;

public class Client2 {
    public static Scanner s1 = new Scanner(System.in);
    public static String serverAdress;

    public static void main (String [] args){
        serverAdress = getServerInput();
        HttpClient client = HttpClient.newHttpClient();
        int menuResult = mainMenu();
        if (menuResult == 2 || menuResult == 3 || menuResult == 4 || menuResult == 6) {
            int id = getID();
            getCompleteInput(menuResult,id);
        }
        else {
            getCompleteInput(menuResult);
        }
        System.out.println("SERVER ADRESS "+serverAdress);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverAdress)).build();
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
    public static String getServerInput() {
        System.out.println("Input the server adress to connect the Client: ");
        String input = s1.nextLine();
        return input;
    }
    public static int mainMenu() {
        System.out.println("\n Choose one of the following actions: ");
        System.out.println("Insert the corelated number");
        System.out.println("1: get /Pizza");
        System.out.println("2: get /Pizza/{pizza_id}");
        System.out.println("3: get /Order/{customer_id}");
        System.out.println("4: get /Order/deliverytime/{order_id}");
        System.out.println("5: post /Order");
        System.out.println("6: put /Order/cancel/{order_id}");
        try {
            int choosed = s1.nextInt();
            return choosed;
        } catch (Exception e) {
            System.out.println("next time choose a int...");
        }
        return 0;
    }
    public static int getID() {
        System.out.println("\n Insert the ID");
        try {
            int id = s1.nextInt();
            return id;
        } catch (Exception e) {
            System.out.println("next time choose a int...");
        }
        return 0;
    }
    public static void getCompleteInput(int menu) {
        if (menu == 1) {
            serverAdress = serverAdress + "/pizza";
        }
        else if (menu == 6) {

        }
    }
    public static void getCompleteInput(int menu, int id) {
        if (menu == 2) {
            serverAdress = serverAdress + "/pizza/"+id;
        }
        else if (menu == 3) {
            serverAdress = serverAdress + "/order/"+id;
        }
        else if (menu == 4) {
            serverAdress = serverAdress + "/order/deliverytime/"+id;
        }
        else if (menu == 6) {
            serverAdress = serverAdress + "/order/cancel/"+id;
        }
    }

}
