/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.controller;

import edu.mum.ea.bookstore.domain.Book;
import edu.mum.ea.bookstore.service.BookstoreService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author dipesh
 */
@Controller
@RequestMapping("/book")
public class BookController {

    //@Resource
    //private IBookDao bookDao;
    @Resource
    private BookstoreService bookService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAll(Model model) {
               //model.addAttribute("books", bookDao.getAll());

        return "book/bookList";
    }

    @RequestMapping(value = "p", method = RequestMethod.POST)
    public String add(Book book) {
        //bookDao.add(book);
        //bookService.addBook(Book book)
        return "redirect:/book/";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String openPage() {
        return "book/addBook";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String get(@PathVariable int id, Model model) {
        //model.addAttribute("book", bookDao.get(id));
        return "book/bookDetail";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(Book book, @PathVariable int id) {
        //bookDao.update(id, book);
        //bookService.updateBook(Book book)
        return "redirect:/book/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable int id) {
        //bookDao.delete(id);
        //bookService.deleteBook(Book book)
        return "redirect:/book/";
    }

    @ExceptionHandler(value = NoSuchResourceException.class)
    public ModelAndView handle(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.getModel().put("e", e);
        mv.setViewName("noSuchResource");
        return mv;
    }
}
