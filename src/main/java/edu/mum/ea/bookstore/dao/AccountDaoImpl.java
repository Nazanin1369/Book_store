/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import edu.mum.ea.bookstore.domain.Account;
import edu.mum.ea.bookstore.domain.Role;
import edu.mum.ea.bookstore.domain.support.DataInitializer;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Hibernate based {@link AccountRepository} implementation.
 *
 * @author Nazanin
 *
 */
@Repository("accountDao")
@Transactional(propagation = Propagation.MANDATORY)
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.SUPPORTS)
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Account findByUsername(String username) {
        String hql = "select c from Account c where c.username=:username";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("username", username);
        List<Account> accounts = query.list();
        return accounts.size() == 1 ? accounts.get(0) : null;
    }

    @Override
    public Account findById(long id) {
        return (Account) this.sessionFactory.getCurrentSession().get(Account.class, id);
    }

    @Override
    public Account save(String firstName, String lastName, String dateOfBirth,
            String email, String username, String password) {
        Account account = new Account();
        DataInitializer in = new DataInitializer();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setDateOfBirth(new Date(dateOfBirth));
        account.setEmailAddress(email);
        in.credentials(account, username, password);
        this.sessionFactory.getCurrentSession().saveOrUpdate(account);
        return account;

    }

}
