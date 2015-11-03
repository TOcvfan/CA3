package facades;

import entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserFacade {
    
    
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("AngSeedServerPU");
    EntityManager em;
    
    
    
    public UserFacade(){
//        Create new users
//      
//        User john = new User("John", "1234");
//        john.AddRole("Admin");
//        
//        
//        User jane = new User("Jane", "1234");
//        jane.AddRole("User");
//        User doe = new User("Doe", "1243");
//        doe.AddRole("Admin");
//        doe.AddRole("User");
//        
//        em = emf.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(john);
//        em.persist(jane);
//        em.persist(doe);
//        em.getTransaction().commit();
//        em.close();
        
    }
    
    public User getUserByUserId(String userName) {
        
        em = emf.createEntityManager();
        User user = em.find(User.class, userName);
        em.close();
        
        return user;
    }
    
    public void createUser(String userName, String pw){
    
        User user = new User(userName, pw);
        user.AddRole("User");
        
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        
    }
    
    
    /*
     Return the Roles if users could be authenticated, otherwise null
     */

    public List<String> authenticateUser(String userName, String password) {
        
        User user = getUserByUserId(userName);
        
        return user != null && user.getPassword().equals(password) ? user.getRoles() : null;
    }

}
