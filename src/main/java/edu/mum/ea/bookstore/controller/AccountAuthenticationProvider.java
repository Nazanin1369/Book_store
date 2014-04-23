/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.controller;

import edu.mum.ea.bookstore.domain.Account;
import edu.mum.ea.bookstore.service.AccountService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author dipesh
 */
public class AccountAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountService accountService;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String username = a.getName();
        String password = a.getCredentials().toString();
        try {
            Account account = this.accountService.login(username, password);

        } catch (edu.mum.ea.bookstore.service.AuthenticationException ex) {
            Logger.getLogger(AccountAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        // TODO : Session works
        return a;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

}
