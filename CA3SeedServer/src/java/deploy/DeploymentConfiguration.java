package deploy;

import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author TOcvfan
 */
public class DeploymentConfiguration implements ServletContextListener {

    public static String puName = "CA3SeedServerPU"; //USE the RIGHT name here

    public void contextInitialized(ServletContextEvent sce) {
        Map<String, String> env = System.getenv();
        //If we are running in the OPENSHIFT environment change the pu-name
        if (env.keySet().contains("OPENSHIFT_MYSQL_DB_HOST")) {
            puName = "pu_OPENSHIFT";
        }
        
    }
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
