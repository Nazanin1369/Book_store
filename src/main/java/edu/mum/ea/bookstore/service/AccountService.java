/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.service;


import edu.mum.ea.bookstore.domain.Account;

/**
 * Contract for services that work with an {@link Account}.
 * 
 *  @author Nazanin
 * 
 *
 */
public interface AccountService {

    Account save(Account account);

    /**
     * Handles the login logic. If the {@link Account} can be retrieved and the password is correct we get the
     * {@link Account}. In all other cases we get a {@link AuthenticationException}.
     * @param username the username
     * @param password the password
     * @return the account
     * @throws AuthenticationException if account not found or incorrect password
     */
    Account login(String username, String password) throws AuthenticationException;

    Account getAccount(String username);
}

