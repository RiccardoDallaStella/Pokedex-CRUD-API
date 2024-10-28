import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {
    private HttpClient Client;
    private String Endpoint;

    public API(String Endpoint){
        Client = HttpClient.newHttpClient();
        this.Endpoint = Endpoint;
    }

    public Pokemon GETRequest(String name){
        try{
            HttpRequest request = HttpRequest.newBuilder(new URI(Endpoint + name)).GET().build();

            HttpResponse<String> response = Client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            return gson.fromJson(response.body(), Pokemon.class);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
