package beans;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Response;
import model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@ManagedBean(name = "userRestBean", eager = true)
@SessionScoped
public class UserRestBean {
    private final String restResourceUrl = "http://localhost:8080/ecomRest_war/api/users";
    private final ObjectMapper mapper = new ObjectMapper();

    private HttpClient client;

    public UserRestBean() {
        client = HttpClient.newHttpClient();
    }

    public List<User> getUsers() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(restResourceUrl))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String result = response.body();
            List<User> users = mapper.readValue(result, new TypeReference<List<User>>(){});
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response addUser(User user) {
        try {
            String json = mapper.writeValueAsString(user);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(restResourceUrl + "/add"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String result = response.body();
            return mapper.readValue(result, Response.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response deleteUser(int id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(restResourceUrl + "/" + id + "/delete"))
                    .DELETE()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String result = response.body();
            return mapper.readValue(result, Response.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response updateUser(User u) {
        try {
            String json = mapper.writeValueAsString(u);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(restResourceUrl + "/update"))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String result = response.body();
            return mapper.readValue(result, Response.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

