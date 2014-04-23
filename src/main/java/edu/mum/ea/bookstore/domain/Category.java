/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
 *
 * @author Nazanin
 */
@Entity
@Cacheable
@Cache(region = "categories", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//This annotation tells hibernate search that this class has to be indexed
@Indexed
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // This is optional if @Id is present, hibernate search needs untokenized id to ensure index is unique
    @DocumentId
    private Long id;

    @Basic(optional = false)
    // This annotation tells that this field has to be indexed and also analyzed
    //(break the long sentence and ignore common words), store tells if this field
    // will be part of Index, by Store.Yesit means it will be part of Index,
    //so that query will be faster, downside is that size of Index increases
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
