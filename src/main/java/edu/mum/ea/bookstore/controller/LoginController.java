/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.controller;

import edu.mum.ea.bookstore.domain.Account;
import edu.mum.ea.bookstore.domain.Role;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author dipesh
 */

@Controller
@SessionAttributes("user")
public class LoginController {
//    
//    @RequestMapping(value = "/divertUser", method = RequestMethod.GET)
//    public String divert(ModelMap map) {
//        if(!map.containsAttribute("user")) {
//            return "/";
//        }else {
//            Account account = (Account)map.get("user");
//            if(account == null) return "/";
//            
//            if(account.getRoles().contains(Role.ADMIN)){
//                return "/book/bookAdmin";
//            }else if(account.getRoles().contains(Role.CUSTOMER)){
//                return "/book/bookHome";
//            }
//        }
//        return "/";
//    }
    
        
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String openAdminPage() {
        return "/admin";
    }
}
