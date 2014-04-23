/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
@Cache(region = "books", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//This annotation tells hibernate search that this class has to be indexed
@Indexed
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // This is optional if @Id is present, hibernate search needs untokenized id to ensure index is unique
    @DocumentId
    private Long id;
    // This annotation tells that this field has to be indexed and also analyzed
    //(break the long sentence and ignore common words), store tells if this field
    // will be part of Index, by Store.Yesit means it will be part of Index,
    //so that query will be faster, downside is that size of Index increases
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String title;
    private String description;
    private BigDecimal price;
    private Integer year;
     @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String author;
    private String Isbn;

    @ManyToOne(optional = false)
    private Category category;

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getIsbn() {
        return this.Isbn;
    }

    public void setIsbn(String isbn) {
        this.Isbn = isbn;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Book other = (Book) object;

        return new EqualsBuilder().append(this.getTitle(), other.getTitle())
                .append(this.getAuthor(), other.getAuthor()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getAuthor()).append(getTitle()).toHashCode();
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.append("title", this.title);
        builder.append("author", this.author);
        builder.append("isbn", this.Isbn);
        return builder.build();
    }
}
