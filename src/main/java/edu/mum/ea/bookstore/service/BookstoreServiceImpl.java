/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.service;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.ea.bookstore.domain.Account;
import edu.mum.ea.bookstore.domain.Book;
import edu.mum.ea.bookstore.domain.BookSearchCriteria;
import edu.mum.ea.bookstore.domain.Cart;
import edu.mum.ea.bookstore.domain.Category;
import edu.mum.ea.bookstore.domain.Order;
import edu.mum.ea.bookstore.domain.OrderDetail;
import edu.mum.ea.bookstore.dao.BookDao;
import edu.mum.ea.bookstore.dao.CategoryDao;
import edu.mum.ea.bookstore.dao.OrderDao;

/**
 * @see BookstoreService
 * 
 * @author Nazanin
 *
 */
@Service("bookstoreService")
@Transactional
public class BookstoreServiceImpl implements BookstoreService {

    private static final int RANDOM_BOOKS = 2;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Book> findBooksByCategory(Category category) {
        return this.bookDao.findByCategory(category);
    }

    @Override
    public List<Book> findRandomBooks() {
        return this.bookDao.findRandom(RANDOM_BOOKS);
    }

    @Override
    @Transactional(readOnly = false)
    public Order store(Order order) {
        return this.orderDao.save(order);
    }

    @Override
    public List<Book> findBooks(BookSearchCriteria bookSearchCriteria) {
        return this.bookDao.findBooks(bookSearchCriteria);
    }

    @Override
    public Book findBook(long id) {
        return this.bookDao.findById(id);
    }

    @Override
    public List<Order> findOrdersForAccount(Account account) {
        return this.orderDao.findByAccount(account);
    }

    @Override
    @Transactional(readOnly = false)
    public Order createOrder(Cart cart, Account customer) {
        Order order = new Order(customer);
        for (Entry<Book, Integer> line : cart.getBooks().entrySet()) {
            order.addOrderDetail(new OrderDetail(line.getKey(), line.getValue()));
        }
        order.setOrderDate(new Date());
        return order;
    }

    @Override
    public Order findOrder(long id) {
        return this.orderDao.findById(id);
    }

    @Override
    public List<Category> findAllCategories() {
        return this.categoryDao.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void addBook(Book book) {
        this.bookDao.storeBook(book);

    }
    @Override
    @Transactional(readOnly = false)
    public void deleteBook(Book book){
        this.bookDao.deleteBook(book);
    }
    @Override
    @Transactional(readOnly = false)
    public void updateBook(Book book){
        this.bookDao.updateBook(book);
    }
    
}
