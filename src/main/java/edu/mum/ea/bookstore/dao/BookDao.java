/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.dao;

import java.util.List;

import edu.mum.ea.bookstore.domain.Book;
import edu.mum.ea.bookstore.domain.BookSearchCriteria;
import edu.mum.ea.bookstore.domain.Category;

/**
 * Repository for working with {@link Book} domain objects
 *
 * @author Nazanin
 *
 */
public interface BookDao {

    Book findById(long id);

    List<Book> findByCategory(Category category);

    List<Book> findRandom(int count);

    List<Book> findBooks(BookSearchCriteria bookSearchCriteria);

    void storeBook(Book book);
    
    void deleteBook(Book book);
    
    void updateBook(Book book);

}
