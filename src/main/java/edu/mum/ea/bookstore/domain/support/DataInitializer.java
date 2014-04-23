/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.domain.support;

import edu.mum.ea.bookstore.domain.Account;
import edu.mum.ea.bookstore.domain.Address;
import edu.mum.ea.bookstore.domain.Book;
import edu.mum.ea.bookstore.domain.Category;
import edu.mum.ea.bookstore.domain.Order;
import edu.mum.ea.bookstore.domain.OrderDetail;
import edu.mum.ea.bookstore.domain.Role;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 *
 * @author Nazanin
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class DataInitializer {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.SUPPORTS)
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Autowired
    private TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public DataInitializer() {
    }

    public void setUpData() {
        this.transactionTemplate.execute(new TransactionCallback<Void>() {
            public Void doInTransaction(TransactionStatus status) {
                if (!dataIsAlreadyPresent()) {
                    //Create roles   
                    Role roleCustomer = new Role("ROLE_CUSTOMER");
                    Role roleAdmin = new Role("ROLE_ADMIN");
                    sessionFactory.getCurrentSession().persist(roleCustomer);
                    sessionFactory.getCurrentSession().persist(roleAdmin);

                    List<Role> customerRoles = new ArrayList<Role>();
                    customerRoles.add(roleCustomer);

                    List<Role> adminRoles = new ArrayList<Role>();
                    customerRoles.add(roleAdmin);
                    //Create addresses
                    Address shippingAdress = new Address();
                    shippingAdress.setBoxNumber("2");
                    shippingAdress.setCity("Brussels");
                    shippingAdress.setCountry("BE");
                    shippingAdress.setHouseNumber("1000");
                    shippingAdress.setPostalCode("73633");
                    shippingAdress.setStreet("198 N street");
                    

                    Address billingAdress = new Address();
                    shippingAdress.setBoxNumber("98");
                    shippingAdress.setCity("Paris");
                    shippingAdress.setCountry("France");
                    shippingAdress.setHouseNumber("223");
                    shippingAdress.setPostalCode("8897");
                    shippingAdress.setStreet("Louis");
                    

                    //Create Customer Acoount
                    Account customer = new Account();
                    customer.setFirstName("Jack");
                    customer.setLastName("Roe");
                    customer.setEmailAddress("Jack@yahoo.com");
                    customer.setDateOfBirth(new Date(12 / 11 / 1987));
                    customer.setAddress(billingAdress);
                    customer.setRoles(customerRoles);
                    customer.setUsername("jack");
                    credentials(customer, "jack", "secret");
                    sessionFactory.getCurrentSession().persist(customer);

                    // Create categories
                    Category cat1 = new Category();
                    cat1.setName("IT");
                    sessionFactory.getCurrentSession().persist(cat1);

                    Category cat2 = new Category();
                    cat2.setName("JAVA");
                    
                    sessionFactory.getCurrentSession().persist(cat2);

                    Category cat3 = new Category();
                    cat3.setName("WEB");
                    sessionFactory.getCurrentSession().persist(cat3);

                    //Create Books
                    Book effectiveJava = new Book();
                    effectiveJava.setTitle("Effective Java");
                    effectiveJava.setIsbn("9780321356680");
                    effectiveJava.setDescription("Brings together seventy-eight indispensable programmer's rules of thumb.");
                    effectiveJava.setAuthor("Joshua Bloch");
                    effectiveJava.setYear(2008);
                    effectiveJava.setPrice(31.20);
                    effectiveJava.setCategory(cat2);
                    
                    Book refactoring = new Book();
                    refactoring.setTitle("Refactoring: Improving the Design of Existing Code");
                    refactoring.setIsbn("9780201485677");
                    refactoring.setDescription("Refactoring is about improving the design of existing code. It is the process of "
                            + "changing a software system in such a way that it does not alter the external beha"
                            + "vior of the code, yet improves its internal structure.");
                    refactoring.setAuthor("Martin Fowler");
                    refactoring.setYear(1999);
                    refactoring.setPrice(41.39);
                    refactoring.setCategory(cat1);

                    Book cleanCode = new Book();
                    cleanCode.setTitle("Clean Code: A Handbook of Agile Software Craftsmanship");
                    cleanCode.setIsbn("9780132350884");
                    cleanCode.setDescription("Even bad code can function. But if code isn't clean, it can bring a development organization "
                            + "to its knees. Every year, countless hours and significant resources are lost because of poorly "
                            + "written code. But it doesn't have to be that way.");
                    cleanCode.setAuthor("Robert C. Martin");
                    cleanCode.setYear(2008);
                    cleanCode.setPrice(33.32);
                    cleanCode.setCategory(cat1);
                    sessionFactory.getCurrentSession().persist(effectiveJava);
                    sessionFactory.getCurrentSession().persist(refactoring);
                    sessionFactory.getCurrentSession().persist(cleanCode);
                    
                    //Create an Order
                    Order order1 = new Order();
                    OrderDetail detail1 = new OrderDetail();
                    OrderDetail detail2 = new OrderDetail();
                    order1.setAccount(customer);
                    order1.setBillingAddress(billingAdress);
                    order1.setBillingSameAsShipping(false);
                    order1.setShippingAddress(shippingAdress);
                    order1.setDeliveryDate(new Date());
                    order1.setOrderDate(new Date());
                    detail1.setBook(cleanCode);
                    detail1.setQuantity(2);
                    detail2.setBook(effectiveJava);
                    detail2.setQuantity(1);
                    order1.addOrderDetail(detail1);
                    order1.addOrderDetail(detail2);
                     sessionFactory.getCurrentSession().persist(detail1);
                     sessionFactory.getCurrentSession().persist(detail2);
                     sessionFactory.getCurrentSession().persist(order1);
                }//end of if
                return null;

            }//end of method doTransaction
        });

    }

    @NotTransactional
    public void credentials(Account account, String username, String password) {
        account.setUsername(username);
        //DigestUtils: 
        //Operations to simplify common MessageDigest tasks. This class is immutable and thread-safe.
        //Message digests are secure one-way hash functions that 
        //take arbitrary-sized data and output a fixed-length hash value.
        account.setPassword(DigestUtils.sha256Hex(password + "{" + username + "}"));

    }

    private boolean dataIsAlreadyPresent() {
        // Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
        Long count = (Long) sessionFactory.getCurrentSession().
                createQuery("select count(a.id) from Account a").uniqueResult();
        // tx.commit();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
