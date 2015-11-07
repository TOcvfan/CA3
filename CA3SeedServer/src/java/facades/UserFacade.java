package facades;

import entity.User;
import exception.UserNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserFacade {

    private EntityManagerFactory emf;
    
    public UserFacade(EntityManagerFactory e) {
        emf = e;
//        Persistence.generateSchema("CA3SeedServerPU", null);
        
//        User u = new User("user", "test");
//        u.AddRole("User");
//        System.out.println(u);
//        User admin = new User("admin", "test");
//        admin.AddRole("Admin");
//        System.out.println(admin);
//        User both = new User("user_admin", "test");
//        both.AddRole("User");
//        both.AddRole("Admin");
//        System.out.println(both);
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(u);
//        em.persist(admin);
//        em.persist(both);
//        em.getTransaction().commit();
//        em.close();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public User getUserByUserId(String userName) throws UserNotFoundException {
        EntityManager em = getEntityManager();
        try{
        User user = em.find(User.class, userName);
        if(user == null){
            throw new UserNotFoundException("No User found with this username");
        }
        return user;
        } finally {
            em.close();
        }

        
    }

    public void createUser(String userName, String pw) {
        EntityManager em = getEntityManager();
        User user = new User(userName, pw);
        user.AddRole("User");

        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
    }

    public User deleteUser(String userName) throws UserNotFoundException {
        EntityManager em = getEntityManager();
        try {
            User u = em.find(User.class, userName);
            if (u == null) {
                throw new UserNotFoundException("No User found with this username");
            }
            em.getTransaction().begin();
            em.remove(u);
            em.getTransaction().commit();
            return u;
        } finally {
            em.close();
        }
    }

    /*
     Return the Roles if users could be authenticated, otherwise null
     */
    public List<String> authenticateUser(String userName, String password) throws UserNotFoundException {
        
        User user = getUserByUserId(userName);

        return user != null && user.getPassword().equals(password) ? user.getRoles() : null;
    }

    public List<User> getUsers() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("select u from User u").getResultList();
        } finally {
            em.close();
        }
    }
}
