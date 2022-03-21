package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Contact;
import ru.geekbrains.entity.Customer;
import ru.geekbrains.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main07 {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();
//        em.getTransaction().begin();
//
//        Customer customer = new Customer(null, "Dmitriy");
//        customer.addProduct(new Product(null, "Home", 1000000, customer));
//        customer.addProduct(new Product(null, "Mobile phone", 90000, customer));
//        customer.addProduct(new Product(null, "HDD disk", 5000, customer));
//
//        em.persist(customer);
//        em.getTransaction().commit();
//        em.createQuery("select p from Product p join fetch p.customer", Product.class).getResultList();
        //Customer customer = em.find(Customer.class, 1L);
//        Customer customer = em.createQuery("select c from Customer c join fetch c.products where c.id = :id", Customer.class)
//                .setParameter("id", 1L)
//                .getSingleResult();
//        System.out.println(customer);
//        customer.getProducts().forEach(System.out::println);
//        Product product = em.find(Product.class, 1L);
//        System.out.println(product);
        em.close();
    }
}
