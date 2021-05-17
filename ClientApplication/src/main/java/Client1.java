import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Client1 {
    private static HttpURLConnection connection;
    public static void main(String[] args) throws IOException, InterruptedException {
        URL url = new URL("http://localhost:8080/api/v1/order");
        String reqBody2 = null;
        String reqBody =  "{\"customer_id\": 7625, \n" +
                "\"payment_type\": \"card\", \n" +
                "\"takeaway\": false,\n" +
                "\"note\": \"Extra mischas sausage please :)\", \n" +
                "\"delivery_address\": { \n" +
                "    \"street\": \"capucinenjaj\",\n" +
                "\t\t\"city\": \"maas\",\n" +
                "\t\t\"country\": \"NL\",\n" +
                "\t\t\"zipcode\": \"324-B\"\n" +
                "} , \n" +
                "\"pizzas\":[  { \n" +
                "    \"pizza_id\":\"1\"\n" +
                "    }\n" +
                "]\n" +
                "  }";

        String typeOfRequest = "POST" ;
        clientManagement(reqBody, url, typeOfRequest);

    }


    //typeOfRequest needs to have "" and capytal letters
    public static void clientManagement (String reqBody, URL url, String typeOfRequest) throws IOException {
        try {
            // URL url = new URL("http://localhost:8080/api/v1/pizza");
            connection = (HttpURLConnection) url.openConnection();

        } catch (MalformedURLException e ){
            e.printStackTrace();
        }
        connection.setRequestMethod(typeOfRequest);
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "applicaton/json");
        connection.setDoOutput(true);
        connection.setConnectTimeout(5000); //if after 5 seconds the connection is not reached, is timeout
        connection.setReadTimeout(5000);

        System.out.println("my status : " + connection);

        if (reqBody != null ){

            StringBuffer response = new StringBuffer();
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = reqBody.getBytes("utf-8");
                os.write(input, 0, input.length);

                /**DataOutputStream out = new  DataOutputStream(os);
                 out.writeBytes(reqBody);
                 out.flush();
                 out.close();
                 */
            }catch (Exception e){
                System.out.println("Line 65");
                e.printStackTrace();
            }

        }
        connection.connect();
        int status = connection.getResponseCode();
        System.out.println(status);


        //READING INFO GIVEN BY SERVER

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();


        //read the response
        if (status > 299){ //it means theres an error
            // reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            System.out.println("there was an error"+connection.getErrorStream());
            // while ((line = reader.readLine())!= null){
            //     responseContent.append(line);
            // }
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine())!= null){
                responseContent.append(line);
            }
        }

        //  reader.close();
        System.out.println(responseContent.toString());
        //parse (responseContent.toString());
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

