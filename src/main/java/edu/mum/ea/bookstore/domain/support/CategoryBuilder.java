/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.domain.support;

import org.springframework.stereotype.Component;

import edu.mum.ea.bookstore.domain.Category;

/**
 * Builds {@link Category} domain objects
 * 
 * @author Nazanin
 * 
 * 
 */
@Component
public class CategoryBuilder extends EntityBuilder<Category> {

	@Override
	void initProduct() {
	}

	@Override
	Category assembleProduct() {
		return this.product;
	}

	public CategoryBuilder name(String name) {
		this.product = new Category(name);
		return this;
	}

}

