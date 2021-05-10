public class Client {
    public static void main (String [] args){
        URL url = new URL ("http://localhost:8080/api/v1/pizza");
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(new HttpGet(url));
    }
}