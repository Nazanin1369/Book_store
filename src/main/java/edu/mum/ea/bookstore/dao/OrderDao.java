/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.dao;

import java.util.List;

import edu.mum.ea.bookstore.domain.Account;
import edu.mum.ea.bookstore.domain.Order;

/**
 * Repository for working with {@link Order} domain objects
 *
 * @author Nazanin
 *
 */
public interface OrderDao {

    /**
     * Find the {@link Order} for the given id.
     *
     * @param id id of the order to find.
     * @return the order belonging to the id.
     */
    public Order findById(long id);

    /**
     * Save the order in the database.
     */
    public Order save(Order order);

    /**
     * Find the orders for the given {@link Account}.
     *
     * @param customer the account
     * @return list of orders for the account, never <code>null</code>
     */
    public List<Order> findByAccount(Account account);

}
