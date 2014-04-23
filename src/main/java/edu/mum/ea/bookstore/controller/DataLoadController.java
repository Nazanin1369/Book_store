/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.controller;

import edu.mum.ea.bookstore.domain.support.DataInitializer;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;


/**
 * This class loads the initial data into the database. {@DataInitializer} class
 * @author Nazanin
 */
@Controller
@Transactional
public class DataLoadController {
    @Autowired
   private DataInitializer loader;
    
    public void setLoader(DataInitializer loader) {
        this.loader = loader;
    }
    public DataLoadController(){
        
    }
    @PostConstruct
    public void initialize(){
        loader.setUpData();
    }

}
