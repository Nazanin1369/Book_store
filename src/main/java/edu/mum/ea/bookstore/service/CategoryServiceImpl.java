/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.ea.bookstore.domain.Category;
import edu.mum.ea.bookstore.dao.CategoryDao;

/**
 * @see CategoryService
 * 
 * @author Nazanin
 *
 */
@Service("categoryService")
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Category findById(long id) {
		return categoryDao.findById(id);
	}

	@Override
	public List<Category> findAll() {
		return this.categoryDao.findAll();
	}

	@Override
	public void addCategory(Category category) {
		categoryDao.storeCategory(category);
	}
}

