

package facades;

import static com.nimbusds.jose.Payload.Origin.JSON;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CvrFacade {
    
    public CvrFacade(){
    }
    
    // HTTP GET request
	public String getCvr() throws Exception {
	    
        String stringBuilder = "http://cvrapi.dk/api?vat=3167%208021&country=dk";        
        URL url = new URL(stringBuilder);
 
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
//        con.setRequestProperty("Accept-Charset", "UTF-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");

        StringBuffer response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String line;
                response = new StringBuffer();
                while ((line = in.readLine()) != null) {
                    response.append(line);
            }
        }
           
        return response.toString();
                
	}
}
