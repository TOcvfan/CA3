package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import deploy.DeploymentConfiguration;
import entity.User;
import facades.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demoadmin")
@RolesAllowed("Admin")
public class Admin {
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser parser = new JsonParser();
    UserFacade uf = new UserFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.puName));
    private static List<User> userList;
    private static JsonArray list = new JsonArray();
    User user = new User();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
        return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated ADMINS\",\n"
                + "\"serverTime\": \"" + now + "\"}";
    }

    @DELETE
    @Path("user/{userName}")
    @Produces("application/json")
    public String deleteUser(@PathParam("userName") String content) {
        JsonObject json = parser.parse(content).getAsJsonObject();
        String userName = json.get("userName").getAsString();
        
        JsonObject response = new JsonObject();
        response.addProperty("useName", userName);
        
        uf.deleteUser(userName);
        
        
        return gson.toJson(response);
    }
    @GET
    @Produces("application/json")
    @Path("/all")
    public String getAllPersons() {

        userList = uf.getUsers();

        Gson gson = new Gson();

        JsonObject response = new JsonObject();
        
        for (int i = 1; i < userList.size(); i++) {
            JsonObject main = new JsonObject();

            try {
                
                user = userList.get(i);

                main.addProperty("userName", user.getUserName());
                main.addProperty("password", user.getPassword());
                
                list.add(main);

            } catch (Exception e) {
            }
        }
        return gson.toJson(list);
    }


}
