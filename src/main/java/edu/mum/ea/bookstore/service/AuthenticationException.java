/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.bookstore.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when username or password are incorrect
 * 
 * @author Nazanin
 * 
 * 
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AuthenticationException extends Exception {

	private String code;

	public AuthenticationException(String message, String code) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
