package com.project.pantry.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="category")//have to add
public class Category {
	@Id
	long id;
	String name;
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
	
}