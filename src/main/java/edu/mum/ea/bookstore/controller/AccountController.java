/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.controller;

import edu.mum.ea.bookstore.domain.Account;
import edu.mum.ea.bookstore.domain.support.DataInitializer;
import edu.mum.ea.bookstore.service.AccountService;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Nazanin
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    
    @RequestMapping(value="registration", method = RequestMethod.POST)
    public String register(String r_firstName, String r_lastName, String r_dateOfBirth,String r_email,
                           String r_username, String r_password){
      
        accountService.save( r_firstName,  r_lastName,  r_dateOfBirth, r_email,r_username,  r_password);
        return "account/success";
    }
            
    
}
