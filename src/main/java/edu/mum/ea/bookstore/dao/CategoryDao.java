/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.bookstore.dao;

import java.util.List;

import edu.mum.ea.bookstore.domain.Category;

/**
 * Repository for working with {@link Category} domain objects
 *
 * @author Nazanin
 *
 *
 */
public interface CategoryDao {

    List<Category> findAll();

    Category findById(long id);

    void storeCategory(Category category);
}
