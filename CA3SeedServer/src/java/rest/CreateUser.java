/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import deploy.DeploymentConfiguration;
import entity.CVR;
import facades.CvrFacade;
import facades.UserFacade;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Silas
 */
@Path("createUser")
public class CreateUser {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser parser = new JsonParser();
    UserFacade uf = new UserFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.puName));
    private static JsonArray list = new JsonArray();
    private static List<CVR> cvrList;
    CvrFacade call = new CvrFacade();
    CVR cvr = new CVR();
    
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CreateUser
     */
    public CreateUser() {
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String createUser(String content) {
        JsonObject json = parser.parse(content).getAsJsonObject();
        
        
        String userName = json.get("userName").getAsString();
        String pw = json.get("pw").getAsString();
                
                
        uf.createUser(userName, pw);
        
        JsonObject jObj = new JsonObject();
        
        jObj.addProperty("userName", userName);
        jObj.addProperty("pw", pw);
        
        String jsonString = gson.toJson(jObj);
        
        return gson.toJson(jsonString);
        
    }

    /**
     * Retrieves representation of an instance of rest.CreateUser
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CreateUser
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
    }
    @GET
    @Produces("application/xml")
    @Path("/all")
    public String getCVRs() throws Exception{
        if (cvrList == null){
        cvrList = call.getAllGroups();
        Gson gson = new Gson();
        //System.out.println(GroupList.size());
        JsonObject response = new JsonObject();
        for (int i = 1; i < cvrList.size(); i++) {
            JsonObject main = new JsonObject();

            try {
                cvr = cvrList.get(i);

                main.addProperty("CVR", cvr.getCvr());
                main.addProperty("name", cvr.getName());
                main.addProperty("address", cvr.getAddress());
                main.addProperty("zip", cvr.getZip());
                main.addProperty("city", cvr.getCity());
                list.add(main);

            } catch (Exception e) {
            }
        }
        }
        //System.out.println(list.toString());
        return list.toString();
    }
}
