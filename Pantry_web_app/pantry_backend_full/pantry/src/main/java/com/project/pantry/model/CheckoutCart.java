package com.project.pantry.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="checkout_cart")
public class CheckoutCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String order_id;
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	String email;
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id")
	Products product;	
	//long ;
	int qty;
	@Column(updatable=false, insertable=false)
	String order_date;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	
	
}


