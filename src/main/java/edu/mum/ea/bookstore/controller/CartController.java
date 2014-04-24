/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.controller;

import edu.mum.ea.bookstore.domain.Account;
import edu.mum.ea.bookstore.domain.Address;
import edu.mum.ea.bookstore.domain.Book;
import edu.mum.ea.bookstore.domain.Cart;
import edu.mum.ea.bookstore.domain.Order;
import edu.mum.ea.bookstore.domain.OrderDetail;
import edu.mum.ea.bookstore.service.AccountService;
import edu.mum.ea.bookstore.service.BookstoreService;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author dipesh
 */
@Controller
@SessionAttributes(value = {"cart", "account"})
@RequestMapping("/cart")
@Transactional
public class CartController {

    @Resource
    private BookstoreService bookService;

    @Resource
    private AccountService accountService;
    
    @Autowired
    private SessionFactory sessionFactory;
    @Transactional(propagation=Propagation.SUPPORTS)
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    private Cart cart;
    private Account account;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addParam(int id, int qty, ModelMap map) {
        if (!map.containsAttribute("cart")) {
            cart = new Cart();
        } else {
            cart = (Cart) map.get("cart");
        }

        if (!map.containsAttribute("account")) {
            // TODO: FIX
            String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            account = accountService.getAccount(userName);
            //account = accountService.getAccount("jack");
        } else {
            account = (Account) map.get("account");
        }

        // first remove the book if it exists.
        for (int i = 0; i < 1; i++) {
            Book book = bookService.findBook(id);
            cart.removeBook(book);
        }
        
        for (int i = 0; i < qty; i++) {
            Book book = bookService.findBook(id);
            cart.addBook(book);
        }

        map.put("cart", cart);
        return "cart";
    }

    @RequestMapping(value = "checkout", method = RequestMethod.POST)
    public String checkout(ModelMap map) {
        Order order = bookService.createOrder(cart, account);

        // Dummy Data
        //Create addresses
        Address shippingAdress = new Address();
        shippingAdress.setBoxNumber("2");
        shippingAdress.setCity("Brussels");
        shippingAdress.setCountry("BE");
        shippingAdress.setHouseNumber("1000");
        shippingAdress.setPostalCode("73633");
        shippingAdress.setStreet("198 N street");

        Address billingAdress = new Address();
        shippingAdress.setBoxNumber("98");
        shippingAdress.setCity("Paris");
        shippingAdress.setCountry("France");
        shippingAdress.setHouseNumber("223");
        shippingAdress.setPostalCode("8897");
        shippingAdress.setStreet("Louis");

        for (Map.Entry<Book, Integer> entry : cart.getBooks().entrySet()) {
            Book book = entry.getKey();
            Integer qty = entry.getValue();
            
            OrderDetail detail = new OrderDetail();
            detail.setBook(book);
            detail.setQuantity(qty);
            
            order.setBillingAddress(billingAdress);
            order.setBillingSameAsShipping(false);
            order.setShippingAddress(shippingAdress);
            order.setDeliveryDate(new Date());
            order.setOrderDate(new Date());

            // TODO: Find what's not working
//        sessionFactory.getCurrentSession().persist(detail);
//        sessionFactory.getCurrentSession().persist(order);
            
//        sessionFactory.getCurrentSession().merge(detail);
//        sessionFactory.getCurrentSession().merge(order);

            bookService.store(order);            
        }
        return "redirect:/book/";
    }
}
