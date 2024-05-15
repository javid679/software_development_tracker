package com.project.pantry.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="products")
public class Products {
	@Id
	long id;
	long cat_id;
	String name,added_on;
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	int qty;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	String img;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdded_on() {
		return added_on;
	}
	public void setAdded_on(String added_on) {
		this.added_on = added_on;
	}
	public long getCategory_id() {
		return cat_id;
	}
	public void setCategory_id(long cat_id) {
		this.cat_id = cat_id;
	}
	
}