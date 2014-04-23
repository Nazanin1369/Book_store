/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import edu.mum.ea.bookstore.domain.Book;
import edu.mum.ea.bookstore.domain.BookSearchCriteria;
import edu.mum.ea.bookstore.domain.Category;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Hibernate implementation for the {@link BookRepository}.
 *
 * @author Nazanin
 */
@Repository("bookDao")
public class BookDaoImpl implements BookDao {

    @Autowired
    //@Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public Book findById(long id) {
        return (Book) this.sessionFactory.getCurrentSession().get(Book.class, id);
    }

    @Override
    public List<Book> findByCategory(Category category) {
        String hql = "select b from Book b where b.category=:category";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("category", category);
        return query.list();
    }

    @Override
    public List<Book> findRandom(int count) {
        String hql = "select b from Book b order by rand()";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setMaxResults(count);
        return query.list();
    }

    @Override
    public List<Book> findBooks(BookSearchCriteria bookSearchCriteria) {
        Assert.notNull(bookSearchCriteria, "Search Criteria are required!");
        /*    Criteria criteria =  this.sessionFactory.getCurrentSession().createCriteria(Book.class);
         List<Predicate> predicates = new ArrayList<Predicate>();
         if (StringUtils.hasText(bookSearchCriteria.getTitle())) {
         String title = bookSearchCriteria.getTitle().toUpperCase();
         criteria.add(Expression.eq(builder.upper(book.<String> get("title")), "%" + title + "%"));
         }

         if (bookSearchCriteria.getCategory() != null) {
         Category category = (Category)this.sessionFactory.getCurrentSession().get(Category.class,bookSearchCriteria.getCategory().getId());
         predicates.add(builder.equal(book.<Category> get("category"), category));
         }

         if (!predicates.isEmpty()) {
         query.where(predicates.toArray(new Predicate[predicates.size()]));
         }

         query.orderBy(builder.asc(book.get("title")));
         return this.entityManager.createQuery(query).getResultList();*/
        return new ArrayList<Book>();

    }

    @Override
    public void storeBook(Book book) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(book);
    }
    
    @Override
    public void deleteBook(Book book){
        this.sessionFactory.getCurrentSession().delete(book);
    }
    
    @Override
    public void updateBook(Book book){
        this.sessionFactory.getCurrentSession().saveOrUpdate(book);
    }
}
