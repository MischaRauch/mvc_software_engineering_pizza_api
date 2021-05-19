import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client1 {
    private static HttpURLConnection connection;
    public static String serverAdress;
    public static String initalServerAdress;
    public static Scanner s1 = new Scanner(System.in);
    public static InputStreamReader ir = new InputStreamReader(System. in );
    public static BufferedReader br = new BufferedReader(ir);
    public static String typeOfRequest = "GET";
    public static String reqBody;
    public static boolean loop = true;


    public static void main(String[] args) throws IOException, InterruptedException {
        serverAdress = getServerInput();
        initalServerAdress = serverAdress;
        int menuResult = mainMenu();
        if (menuResult == 7) {
            loop = false;
        }

        while (loop) {

            if (menuResult == 2 || menuResult == 3 || menuResult == 4 || menuResult == 6) {
                int id = getID();
                getCompleteInput(menuResult, id);
            } else {
                getCompleteInput(menuResult);
            }
            System.out.println("SERVER ADRESS " + serverAdress);

            URL url = new URL(serverAdress);
            String reqBody2 = null;
            System.out.println("URL ADRESS " + url);


            clientManagement(reqBody, url, typeOfRequest);

            menuResult = mainMenu();
            serverAdress = initalServerAdress;
            if (menuResult == 7) {
                loop = false;
            }
        }

    }


    //typeOfRequest needs to have "" and capytal letters
    public static void clientManagement(String reqBody, URL url, String typeOfRequest) throws IOException {
        try {
            // URL url = new URL("http://localhost:8080/api/v1/pizza");
            connection = (HttpURLConnection) url.openConnection();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        connection.setRequestMethod(typeOfRequest);
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        connection.setConnectTimeout(5000); //if after 5 seconds the connection is not reached, is timeout
        connection.setReadTimeout(5000);

       // System.out.println("my status : " + connection);

        if (reqBody != null) {

            StringBuffer response = new StringBuffer();
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = reqBody.getBytes("utf-8");
                os.write(input, 0, input.length);

                /**DataOutputStream out = new  DataOutputStream(os);
                 out.writeBytes(reqBody);
                 out.flush();
                 out.close();
                 */
            } catch (Exception e) {
                System.out.println("Line 65");
                e.printStackTrace();
            }

        }
        connection.connect();
        int status = connection.getResponseCode();
       // System.out.println(status);


        //READING INFO GIVEN BY SERVER

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        //read the response
        if (status > 299) { //it means theres an error
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
             while ((line = reader.readLine())!= null){
                 responseContent.append(line);
                 responseContent.append(System.getProperty("line.separator"));
             }
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
                responseContent.append(System.getProperty("line.separator"));
            }
        }

        //  reader.close();
        System.out.println(responseContent.toString());
        //parse (responseContent.toString());
        connection.disconnect();

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
        System.out.println("7: exit");
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
    public static void getCompleteInput(int menu, int id){
        if (menu == 2) {
            serverAdress = serverAdress + "/pizza/" + id;
        } else if (menu == 3) {
            serverAdress = serverAdress + "/order/" + id;
        } else if (menu == 4) {
            serverAdress = serverAdress + "/order/deliverytime/" + id;
        } else if (menu == 6) {
            serverAdress = serverAdress + "/order/cancel/" + id;
            typeOfRequest = "PUT";
        }
    }
    public static void getCompleteInput(int menu){
        if (menu == 1) {
            serverAdress = serverAdress + "/pizza";
        } else if (menu == 5) {
            serverAdress = serverAdress + "/order";
            getOrder();
        }
    }
    public static void getOrder() {
        typeOfRequest = "POST";
        System.out.println("Insert your customer id: ");
        int customerID = s1.nextInt();
        System.out.println("Insert your payment Method");
        String paymentMethod = s1.next();
        System.out.println("Takeaway?");
        boolean takeaway = s1.nextBoolean();
        System.out.println("Add a note if needed:");
        String note = s1.next();
        System.out.println("Add your street:");
        String street = s1.next();
        System.out.println("Add your city:");
        String city = s1.next();
        System.out.println("Add your country:");
        String country = s1.next();
        System.out.println("Add your zipCode:");
        String zipCode = s1.next();
        System.out.println("Add the pizza ID:");
        int pizzaID = s1.nextInt();

        reqBody = "{\"customer_id\": "+customerID+", \n" +
                "\"payment_type\": \""+paymentMethod+"\", \n"+
                "\"takeaway\": "+takeaway +", \n"+
                "\"note\": \""+note+"\", \n" +
                "\"delivery_address\": { \n" +
                "    \"street\": \""+street+"\",\n" +
                "\t\t\"city\": \""+city+"\", \n"+
                "\t\t\"country\": \""+country+"\", \n"+
                "\t\t\"zipcode\": \""+zipCode +"\" \n"+
                "} , \n" +
                "\"pizzas\":[  { \n" +
                "    \"pizza_id\":\""+pizzaID +"\" \n"+
                "    }\n" +
                "]\n" +
                "  }";
    }
}

