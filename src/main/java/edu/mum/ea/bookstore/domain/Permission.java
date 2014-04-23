/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Nazanin
 * 
 * 
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "permission" }) })
public class Permission implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String permission;

	Permission() {
		// Form ORM
	}

	public Permission(String permission) {
		this.permission = permission;
	}

	public Long getId() {
		return id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
