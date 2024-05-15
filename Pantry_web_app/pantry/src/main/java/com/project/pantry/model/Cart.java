package com.project.pantry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	long id;
	@JsonIgnore
	
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id")
	Products product;
	//Long product_id;
	int qty;

String email;
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

@Column(updatable=false, insertable=false)
	String added_date;
	
	@Transient
	String productName;
	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getAdded_date() {
		return added_date;
	}
	public void setAdded_date(String added_date) {
		this.added_date = added_date;
	}
	 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}

	public String getProductName() {
		return product.getName();
	}
}