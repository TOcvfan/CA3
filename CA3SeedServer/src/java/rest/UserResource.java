package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import deploy.DeploymentConfiguration;
import facades.CvrFacade;
import facades.UserFacade;
import java.net.HttpURLConnection;
import java.util.regex.Pattern;
import javax.annotation.security.RolesAllowed;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demouser")
@RolesAllowed("User")
public class UserResource {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser parser = new JsonParser();
    UserFacade uf = new UserFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.puName));
    private static JsonArray list = new JsonArray();
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated USERS\"}";
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    public String getCVRs() throws Exception{
        
        Gson gsons = new Gson();
        CvrFacade cvr = new CvrFacade();
        String s = cvr.getCvr();
        
//        String[] parts = s.split("(,\")");
       
//        for (int i = 0; i < parts.length; i++) {
//            JsonObject response = new JsonObject();
//              try {
//                  String[] newPart = parts[i].split(("[:]"));
//                  System.out.println("this is newPart 0: "+newPart[0]);
//                  System.out.println("this is newPart 1: "+newPart[1]);
//            response.addProperty(newPart[0].replace("\"", "").replace("{", ""), newPart[1].replace("\"", ""));
//                  System.out.println("this is response.toString(): "+response.toString());
//            list.add(response);
//            i++;
//            } catch (Exception e) {
//                  System.out.println("UserResource had an error in converting stream.");
//            }
//        }System.out.println("this is my List: "+list.toString());
          
        
        
//        return gsons.toJson(s);
        return s;
        
        
        
        
        
        
        
        
        
//        String[] parts = s.split("[:]");
//        String[] finalP;
//        for (int i = 0; i < parts.length; i++) {
//            finalP (parts[i].split("[:]"));
//        }
//
//        for (int i = 0; i < parts.length; i++) {
//            JsonObject response = new JsonObject();
//              try {
//            response.addProperty(parts[i].replace("\"", "").replace("{", ""), parts[i+1].replace("\"", ""));
//            list.add(response);
//            i++;
//            
//            } catch (Exception e) {
//                  System.out.println("UserResource had an error in converting stream.");
//            }
//        }System.out.println("this is my List: "+list.toString());
//        return gsons.toJson(list);
       
    }
}

//        JsonObject response = new JsonObject();
//        response.addProperty("cvr", parts[0].replace("\"", ""));
//        response.addProperty("name", parts[3].replace("\"", ""));
//        response.addProperty("address", parts[5].replace("\"", ""));
//        response.addProperty("zip", parts[7].replace("\"", ""));
//        System.out.println("this was from UserResource!!!: "+gsons.toJson(response));
//        list.add(response);




//  String serialNo= "004-034556";
//    String[] parts = serialNo.split("-");
//    String string1 = parts[0]; // 004
//    String string2 = parts[1]; // 034556

//                              map.put("cvr", eElement.getElementsByTagName("vat").item(0).getTextContent()); 
//80 				map.put("navn", eElement.getElementsByTagName("name").item(0).getTextContent()); 
//81 				map.put("adresse", eElement.getElementsByTagName("address").item(0).getTextContent()); 
//82 				map.put("postnr", eElement.getElementsByTagName("zipcode").item(0).getTextContent()); 
//83 				map.put("city", eElement.getElementsByTagName("city").item(0).getTextContent()); 
//84 				map.put("beskytet", eElement.getElementsByTagName("protected").item(0).getTextContent()); 
//85 				map.put("startdato", eElement.getElementsByTagName("startdate").item(0).getTextContent()); 
//86 				map.put("bkode", eElement.getElementsByTagName("industrycode").item(0).getTextContent()); 
//87 				map.put("vkode", eElement.getElementsByTagName("companycode").item(0).getTextContent()); 
//88 				map.put("vtekst", eElement.getElementsByTagName("companydesc").item(0).getTextContent()); 
//89 				map.put("apiversion", eElement.getElementsByTagName("version").item(0).getTextContent()); 
