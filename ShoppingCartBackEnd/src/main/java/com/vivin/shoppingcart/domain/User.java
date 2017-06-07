package com.vivin.shoppingcart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity // to map the data base table
@Table(name = "User") // if the table name and domain class name is different
@Component // context.scan("com.niit") --will scan the pacakge and create
			// singleton instances
public class User {

	// we have define all properties for all the fields in table.

	// we have mention which one is primary key
	@Id
	private String id;

	// if the field name in table and property name in
	// class is
	// different, then we required specify column name
	// Otherwise no need specify
	private String name;

	private String password;

	private String contact;

	private String Role;

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	private String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}