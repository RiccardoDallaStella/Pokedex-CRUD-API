import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {
    private HttpClient Client;
    private String Endpoint;
    private String APIKey;

    public API(String Endpoint){
        Client = HttpClient.newHttpClient();
        this.Endpoint = Endpoint;
    }

    public API(String Endpoint, String APIKey){
        Client = HttpClient.newHttpClient();
        this.APIKey = APIKey;
        this.Endpoint = Endpoint + "?api_key=" + APIKey + "&q=";
    }

    public Pokemon GETPokemonRequest(String name){
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

    public GIF GETGifRequest(String name){
        try{
            HttpRequest request = HttpRequest.newBuilder(new URI(Endpoint + name)).GET().build();

            HttpResponse<String> response = Client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            return gson.fromJson(response.body(), GIF.class);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
