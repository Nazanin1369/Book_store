/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.dao;

import edu.mum.ea.bookstore.domain.Book;
import edu.mum.ea.bookstore.domain.Category;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nazanin
 */
@Repository("bookSearch")
@Transactional(propagation=Propagation.MANDATORY)
public class SearchDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Transactional(propagation=Propagation.SUPPORTS)
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SearchDao(){}
    
    private void doIndex() throws InterruptedException {

        FullTextSession fullTextSession;
        fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        fullTextSession.createIndexer().startAndWait();
        fullTextSession.close();
    }

    private List<Book> searchForBook(String queryString) {

        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());

        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields("title", "author").matching(queryString).createQuery();

        // wrap Lucene query in a javax.persistence.Query
        org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Book.class);
        List<Book> bookList = fullTextQuery.list();
        fullTextSession.close();

        return bookList;
    }
    
     private List<Book> searchForCategory(String queryString) {

        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());

        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Category.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields("name").matching(queryString).createQuery();

        // wrap Lucene query in a javax.persistence.Query
        org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Category.class);
        List<Book> categoryList = fullTextQuery.list();
        fullTextSession.close();

        return categoryList;
    }
}
