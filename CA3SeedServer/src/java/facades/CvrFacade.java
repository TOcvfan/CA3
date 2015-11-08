

package facades;

import static com.nimbusds.jose.Payload.Origin.JSON;
import entity.CVR;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CvrFacade implements Callable<CVR>{
    private final static List<CVR> cvrs = new ArrayList();
    public int count = 0;
    
    public CvrFacade(){
    }
    
    // HTTP GET request
//	public String getCvr() throws Exception {
	    
//        String stringBuilder = "http://cvrapi.dk/api?vat=3167%208021&country=dk";        
//        URL url = new URL(stringBuilder);
// 
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
////        con.setRequestProperty("Accept-Charset", "UTF-8");
//        con.setRequestProperty("Accept", "application/json");
//        con.setRequestProperty("Content-Type", "application/json");
//
//        StringBuffer response;
//            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
//                String line;
//                response = new StringBuffer();
//                while ((line = in.readLine()) != null) {
//                    response.append(line);
//            }
//        }
//           
//        return response.toString();
//                
//	}

    @Override
    public CVR call() throws Exception {
        CVR g = new CVR();

        Document doc = Jsoup.connect(urls.get(count)).get();
        Elements cvr = doc.select("#vat");
        Elements name = doc.select("#name");
        Elements address = doc.select("#address");
        Elements zip = doc.select("#zipcode");
        Elements city = doc.select("#city");
        g.setCvr(cvr.text());
        g.setName(name.text());
        g.setAddress(address.text());
        g.setZip(zip.text());
        g.setCity(city.text());

        count++;

        return g;

    }

    

    public List<CVR> getAllGroups() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Callable<CVR> callable = new CvrFacade();
        //create a list to hold the Future object associated with Callable
        List<Future<CVR>> list = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            Future<CVR> future = executor.submit(callable);

            list.add(future);
}
        for (Future<CVR> fut : list) {
            try {
        
                cvrs.add(fut.get());
                
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            
            
            //shut down the executor service now
            
        }
        executor.shutdown();
        return cvrs;
    }

    public static List<String> urls = new ArrayList<String>() {
        {

            //Class A
            add("http://cvrapi.dk/api?vat=3167%208021&country=dk");
        }
    };
}
