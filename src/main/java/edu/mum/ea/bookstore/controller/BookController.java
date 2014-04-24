/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.controller;

import edu.mum.ea.bookstore.dao.SearchDao;
import edu.mum.ea.bookstore.domain.Book;
import edu.mum.ea.bookstore.domain.Category;
import edu.mum.ea.bookstore.service.BookstoreService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author dipesh
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookstoreService bookService;
    @Autowired
    private SearchDao searchDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("books", bookService.findRandomBooks());
        return "book/bookHome";
    }

    @RequestMapping(value = "p", method = RequestMethod.POST)
    public String add(Book book) {
        bookService.addBook(book);
        return "redirect:/book/";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String openAddPage() {
        return "book/addBook";
    }

    @RequestMapping(value = "search_result", method = RequestMethod.GET)
    public String searchByBook(String title, String category, Model model) {
        // TODO: Nazanin
        List<Book> books = new ArrayList<Book>();
        title = title.trim();
        category = category.trim();
        
        try{
            searchDao.doIndex();
            books = searchDao.searchForBook(title, category);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        model.addAttribute("books",books);
        return "book/bookList";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String openSearchPage() {
        return "book/search";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("book", bookService.findBook(id));
        return "book/updateBook";
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("book", bookService.findBook(id));
        return "book/bookDetail";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(Book book, @PathVariable int id) {
        bookService.updateBook(book);
        return "redirect:/book/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable int id) {
        //bookService.deleteBook();
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
