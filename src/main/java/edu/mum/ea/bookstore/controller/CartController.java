/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author dipesh
 */

@Controller
@SessionAttributes("cart")
@RequestMapping("/cart")
public class CartController {
    Map<Integer, Integer> cart;    

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addParam(int id, int qty, ModelMap map) {
        if(!map.containsAttribute("cart")) {
            cart = new HashMap<Integer, Integer>();
        }else {
            cart = (Map)map.get("cart");
        }
        cart.put(id, qty);
        map.put("cart", cart);
        return "cart";
    }
    
    @RequestMapping(value = "checkout", method = RequestMethod.POST)
    public String addParam(ModelMap map) {
        
        return "/book/";
    }    
}
