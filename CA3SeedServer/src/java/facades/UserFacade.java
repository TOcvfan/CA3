package facades;

import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class UserFacade {

    private EntityManagerFactory emf;

    public UserFacade(EntityManagerFactory e) {
        emf = e;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public User getUserByUserId(String userName) {
        EntityManager em = getEntityManager();
        em = emf.createEntityManager();
        User user = em.find(User.class, userName);
        em.close();

        return user;
    }

    public void createUser(String userName, String pw) {
        EntityManager em = getEntityManager();
        User user = new User(userName, pw);
        user.AddRole("User");

        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();

    }

    public User deleteUser(String userName) {
        EntityManager em = getEntityManager();
        try {
            User u = em.find(User.class, userName);
            if (u == null) {
                //throw new PersonNotFoundException("No Person found with provided id");
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
    public List<String> authenticateUser(String userName, String password) {

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
