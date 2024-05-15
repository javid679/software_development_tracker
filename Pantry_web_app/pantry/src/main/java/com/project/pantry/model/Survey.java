package com.project.pantry.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="survey")
public class Survey {
	
	@Id    // making userID as primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	String experience;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	String email;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getEnthinicity() {
		return ethnicity;
	}
	public void setEnthinicity(String enthinicity) {
		this.ethnicity = enthinicity;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMemeberliving() {
		return members;
	}
	public void setMemeberliving(String memeberliving) {
		this.members = memeberliving;
	}
	String age;
	String ethnicity;
	String college;
	String location;
	String members;
	
}
