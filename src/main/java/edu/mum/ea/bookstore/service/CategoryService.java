/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.service;

import java.util.List;

import edu.mum.ea.bookstore.domain.Category;

/**
 * Contract for services that work with an {@link Category}.
 * @author Nazanin
 * 
 */
public interface CategoryService {

	Category findById(long id);

	List<Category> findAll();

	void addCategory(Category category);

}
