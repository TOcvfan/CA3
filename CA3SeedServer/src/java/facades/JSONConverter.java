package facades;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import java.util.List;

public class JSONConverter {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
  //private static Gson gson = new Gson();

    public static User getUserFromJson(String js) {
//    JsonObject jo = new JsonParser().parse(js).getAsJsonObject();
//    return new Person(jo.get("fName").getAsString(),jo.get("lName").getAsString(),jo.get("phone").getAsString());
        return gson.fromJson(js, User.class);
    }

    public static String getJSONFromUser(User p) {
        return gson.toJson(p);
    }

    public static String getJSONFromUser(List<User> users) {
        return gson.toJson(users);
    }
}