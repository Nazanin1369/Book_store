/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.controller;

import edu.mum.ea.bookstore.domain.Account;
import edu.mum.ea.bookstore.domain.Role;
import edu.mum.ea.bookstore.service.AccountService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * @author dipesh
 */
@Component
public class AccountAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountService accountService;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String username = a.getName();
        String password = a.getCredentials().toString();
        Account account = null;
        try {
            account = this.accountService.login(username, password);
            if (account == null) {
                return null;
            }
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for (Role role : account.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            }
            return new UsernamePasswordAuthenticationToken(a.getName(), a.getCredentials(), authorities);

        } catch (edu.mum.ea.bookstore.service.AuthenticationException ex) {
            Logger.getLogger(AccountAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

}
