package com.project.pantry.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	private static final long OTP_VALID_DURATION = 10 * 60 * 1000;
	@Id    // making userID as primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	private String name;
	private String password;
	private String email;
	private Date createdAt;
	private String loginToken;
	private int isEmailVerified;
	private String otp;
	private Date otp_requesttime;
	public String getForgot_otp() {
		return forgot_otp;
	}
	public void setForgot_otp(String forgot_otp) {
		this.forgot_otp = forgot_otp;
	}
	private String forgot_otp;

	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Date getOtp_requesttime() {
		return otp_requesttime;
	}
	public void setOtp_requesttime(Date otp_requesttime) {
		this.otp_requesttime = otp_requesttime;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return name;
	}
	public void setUsername(String username) {
		this.name = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getLoginToken() {
		return loginToken;
	}
	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}
	public int isEmailVerified() {
		return isEmailVerified;
	}
	public void setEmailVerified(int isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}
	

}
