/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.mum.ea.bookstore.domain.Account;
import edu.mum.ea.bookstore.domain.Order;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Hibernate based {@link OrderRepository} implementation.
 *
 * @author Nazanin
 *
 *
 */
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Order save(Order order) {
		// The order is always a transient object, since we are creating an
        // order, so normally persist is sufficient.
        // However, the Account, Book and Category are objects that already
        // exist and are in detached state.
        // Persisting these objects (indirectly via the cascading) will trigger
        // an exception.
        // By calling merge we can save transient objects and re-attach detached
        // objects automatically.
        this.sessionFactory.getCurrentSession().saveOrUpdate(order);
        return order;

    }

    @Override
    public List<Order> findByAccount(Account account) {
        String hql = "select o from Order o where o.account=:account";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("account", account);
        return query.list();
    }

    @Override
    public Order findById(long id) {
        return (Order)this.sessionFactory.getCurrentSession().get(Order.class, id);
    }

}
