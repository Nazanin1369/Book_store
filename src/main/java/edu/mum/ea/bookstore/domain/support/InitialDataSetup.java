/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.domain.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import edu.mum.ea.bookstore.domain.Account;
import edu.mum.ea.bookstore.domain.Book;
import edu.mum.ea.bookstore.domain.Category;
import edu.mum.ea.bookstore.domain.Order;
import edu.mum.ea.bookstore.domain.Permission;
import edu.mum.ea.bookstore.domain.Role;
import edu.mum.ea.bookstore.domain.support.EntityBuilder.SessionFactoryBuilderManager;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Sets up initial data so the application can be used straight away. The data
 * setup is executed in a separate transaction, and committed when the
 * {@link #setupData()} method returns
 *
 * @author Nazanin
 *
 *
 */
@Component
public class InitialDataSetup {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public InitialDataSetup() {
    }

    private Permission permissionAddCategories = new Permission("PERM_ADD_CATEGORIES");
    private Permission permissionAddBooks = new Permission("PERM_ADD_BOOKS");
    private Permission permissionCreateOrders = new Permission("PERM_CREATE_ORDER");

    private Role roleCustomer = new Role("ROLE_CUSTOMER");
    private Role roleAdmin = new Role("ROLE_ADMIN");
    private Role roleAuthor = new Role("ROLE_AUTHOR");

    private Account johnDoe;
    private Category category;

    public void initialize() {
        SessionFactoryBuilderManager.setSessionFactory(this.sessionFactory);
        if (dataIsAlreadyPresent()) {

            // Create accounts
            johnDoe = new AccountBuilder() {
                {
                    address("Brussels", "1000", "Nieuwstraat", "1", "A", "BE");
                    email("foo@test.com");
                    credentials("jd", "secret");
                    name("John", "Doe");
                    roleWithPermissions(InitialDataSetup.this.roleCustomer,
                            InitialDataSetup.this.permissionCreateOrders);
                }
            }.build();

            new AccountBuilder() {
                {
                    address("Antwerp", "2000", "Meir", "1", "A", "BE");
                    email("bar@test.com");
                    credentials("admin", "secret");
                    name("Super", "User");
                    roleWithPermissions(InitialDataSetup.this.roleAdmin,
                            InitialDataSetup.this.permissionAddBooks,
                            InitialDataSetup.this.permissionAddCategories);
                }
            }.build();

            new AccountBuilder() {
                {
                    address("Gent", "9000", "Abdijlaan", "1", "A", "BE");
                    email("baz@test.com");
                    credentials("author", "secret");
                    name("Some", "Author");
                    roleWithPermissions(InitialDataSetup.this.roleAuthor,
                            InitialDataSetup.this.permissionAddBooks);
                }
            }.build();

                // Create categories
            category = new CategoryBuilder() {
                {
                    name("IT");
                }
            }.build();

            new CategoryBuilder() {
                {
                    name("Java");
                }
            }.build();

            new CategoryBuilder() {
                {
                    name("Web");
                }
            }.build();

            // Create different books
            List<Order> orders = new ArrayList<Order>();
            final Book effectiveJava = new BookBuilder() {
                {
                    title("Effective Java");
                    isbn("9780321356680");
                    description("Brings together seventy-eight indispensable programmer's rules of thumb.");
                    author("Joshua Bloch");
                    year(2008);
                    price("31.20");
                    category(InitialDataSetup.this.category);
                }
            }.build();

            final Book refactoring = new BookBuilder() {
                {
                    title("Refactoring: Improving the Design of Existing Code");
                    isbn("9780201485677");
                    description("Refactoring is about improving the design of existing code. It is the process of "
                            + "changing a software system in such a way that it does not alter the external beha"
                            + "vior of the code, yet improves its internal structure.");
                    author("Martin Fowler");
                    year(1999);
                    price("41.39");
                    category(InitialDataSetup.this.category);
                }
            }.build();

            final Book cleanCode = new BookBuilder() {
                {
                    title("Clean Code: A Handbook of Agile Software Craftsmanship");
                    isbn("9780132350884");
                    description("Even bad code can function. But if code isn't clean, it can bring a development organization "
                            + "to its knees. Every year, countless hours and significant resources are lost because of poorly "
                            + "written code. But it doesn't have to be that way.");
                    author("Robert C. Martin");
                    year(2008);
                    price("33.32");
                    category(InitialDataSetup.this.category);
                }
            }.build();

            final Book agileSoftware = new BookBuilder() {
                {
                    title("Agile Software Development, Principles, Patterns, and Practices");
                    isbn("9780135974445");
                    description("A unique collection of the latest software development methods. Includes OOD, UML, Design Patterns, Agile and XP methods with a "
                            + "detailed description of a complete software design for reusable programs in C++ and Java.");
                    author("Robert C. Martin");
                    year(2002);
                    price("54.61");
                    category(InitialDataSetup.this.category);
                }
            }.build();

            final Book practicalApiDesign = new BookBuilder() {
                {
                    title("Practical API Design: Confessions of a Java Framework Architect");
                    isbn("9781430209737");
                    description("The definitive guide to API design, this book will be required reading for all "
                            + "designers and engineers involved with the development, testing, and maintenance of APIs.");
                    author("Jaroslav Tulach");
                    year(2008);
                    price("56.01");
                    category(InitialDataSetup.this.category);
                }
            }.build();

                    // For the first three books we create a separate order for each book.
            // For the final two books we create a single order but add two books to them
            orders.add(new OrderBuilder() {
                {
                    addBook(effectiveJava, 1);
                    deliveryDate(new Date());
                    orderDate(new Date());
                    account(InitialDataSetup.this.johnDoe);
                }
            }.build());

            orders.add(new OrderBuilder() {
                {
                    addBook(refactoring, 1);
                    deliveryDate(new Date());
                    orderDate(new Date());
                    account(InitialDataSetup.this.johnDoe);
                }
            }.build());

            orders.add(new OrderBuilder() {
                {
                    addBook(cleanCode, 1);
                    deliveryDate(new Date());
                    orderDate(new Date());
                    account(InitialDataSetup.this.johnDoe);
                }
            }.build());

            orders.add(new OrderBuilder() {
                {
                    addBook(agileSoftware, 1);
                    addBook(practicalApiDesign, 1);
                    deliveryDate(new Date());
                    orderDate(new Date());
                    account(InitialDataSetup.this.johnDoe);
                }
            }.build());

        }//end of if
        SessionFactoryBuilderManager.clearSessionFactory();
    }

    private boolean dataIsAlreadyPresent() {

        return (Long) InitialDataSetup.this.sessionFactory.getCurrentSession().
                createQuery("select count(a.id) from Account a").uniqueResult() > 0;
    }

}
