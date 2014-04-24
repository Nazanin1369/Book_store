/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.dao;

import edu.mum.ea.bookstore.domain.Account;

/**
 * Repository for working with {@link Account} domain objects
 *
 * @author Nazanin
 *
 *
 */
public interface AccountDao {

    Account findByUsername(String username);

    Account findById(long id);

    Account save(String firstName, String lastName,String dateOfBirth,
                String email, String username, String password);

}
