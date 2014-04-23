/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.service;

import java.util.List;

import edu.mum.ea.bookstore.domain.Account;
import edu.mum.ea.bookstore.domain.Book;
import edu.mum.ea.bookstore.domain.BookSearchCriteria;
import edu.mum.ea.bookstore.domain.Cart;
import edu.mum.ea.bookstore.domain.Category;
import edu.mum.ea.bookstore.domain.Order;

/**
 * Main entry point for the bookstore.
 * 
 *  @author Nazanin
 * 
 * 
 */
public interface BookstoreService {

    List<Book> findBooksByCategory(Category category);

    Book findBook(long id);

    Order findOrder(long id);

    List<Book> findRandomBooks();

    /**
     * Find the {@link Order}s for a given {@link Customer}.
     * 
     * @param customer the customer
     * @return the list of orders (never <code>null</code>).
     */
    List<Order> findOrdersForAccount(Account account);

    /**
     * Persist or update the given {@link Order} in the database.
     * 
     * @param order the order to store.
     * @return the persisted order.
     */
    Order store(Order order);

    /**
     * Search for {@link Book}s that meet the given {@link BookSearchCriteria}.
     * 
     * @param bookSearchCriteria the search criteria.
     * @return list of books (never <code>null</code>).
     */
    List<Book> findBooks(BookSearchCriteria bookSearchCriteria);

    /**
     * Create an actual {@link Order} for the given {@link Customer} based on the content of their {@link Cart}.
     * 
     * @param cart the cart
     * @param customer the customer
     * @return an {@link Order}
     */
    Order createOrder(Cart cart, Account account);

    /**
     * Find all the categories available.
     * 
     * @return list with all the categories
     */
    List<Category> findAllCategories();

    /**
     * Store a book in the repository.
     * 
     * @param book the book to store.
     */
    void addBook(Book book);
    
    void deleteBook(Book book);
    
    void updateBook(Book book);
}

