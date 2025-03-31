package com.logincashplus.models;

public class User {
	private int id;
	private String email;
	 private String password;
	 private String name;
	 private String verificationCode;
	 private boolean isVerified;

	  public User(int id, String email, String password,  String name, String verificationCode, boolean isVerified) {
		  this.id = id;
	        this.name = name;
	        this.email = email;
	        this.password = password;
	        this.verificationCode = verificationCode;
	        this.isVerified = isVerified;
	  }
	  public int getId() {return id; }
	  public void setId(int id) {this.id = id;}
	  
	  public String getName() {return name;}
	  public void setName(String name) { this.name = name; }
	  
	  public String getEmail() {return email;}
	  public void setEmail(String email) {this.email = email;}
	  
	  public String getPassword() {return password;}
	  public void setPassword(String password) {this.password = password;}
	  
	  public String getVerificationCode() {return verificationCode;}
	  public void setVerificationCode(String verificationCode) {this.verificationCode = verificationCode;}
	  
	  public boolean isVerified() { return isVerified; }
	  public void setVerified(boolean isVerified) { this.isVerified = isVerified; }
	
	 
	  
	  
	  
}