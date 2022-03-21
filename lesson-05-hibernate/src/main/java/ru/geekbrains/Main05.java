package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main05 {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        //INSERT
        em.getTransaction().begin();
        List.of(
                new User(null, "user2", 18),
                new User(null, "Alex", 24),
                new User(null, "Ivan", 57),
                new User(null, "Petr", 34),
                new User(null, "Julia", 27),
                new User(null, "Max", 33)
        ).forEach (em::persist);
        em.getTransaction().commit();

        //SELECT

//        User user = em.find(User.class, 1L);
//        System.out.println(user);

        //HQL, JPQL
//        List<User> users = em.createQuery("select u from User u where u.age > :age", User.class)
//                .setParameter("age", 25)
//                .getResultList();
//        System.out.println(users);
//
//        Long countUsers = em.createNamedQuery("countUsers", Long.class).getSingleResult();
//        System.out.println(countUsers);
//
//        users = em.createNativeQuery("SELECT * from Users", User.class)
//                        .getResultList();
//        System.out.println(users);

        // UPDATE
//        em.getTransaction().begin();
//        em.createQuery("update User set age = 22 where id = 1")
//                .executeUpdate();

//        User user = em.find(User.class, 2L);
//        user.setAge(27);

//        User user = new User(null, "user1", 64);
//        em.merge(user);
//        em.getTransaction().commit();

        //DELETE
        //em.getTransaction().begin();
       // User user = em.find(User.class, 7L);
       // User user = em.getReference(User.class, 6L);
       // em.remove(user);
        //em.getTransaction().commit();


        em.close();

    }
}
