package com.destina.Dto;



import java.time.LocalDateTime;

import com.destina.model.AccountStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
//    public UserDto(Long id2, String firstName2, String lastName2, String email2, String password2, String mobileNumber2,
//			String address2, String role2, AccountStatus accountStatus2, LocalDateTime createdOn2) {
//		
//	}
	
	
	
    
    public UserDto(Long id, String firstName, String lastName, String email, String password, String mobileNumber,
		String address, String role, AccountStatus accountStatus, LocalDateTime createdOn) {
	
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.password = password;
	this.mobileNumber = mobileNumber;
	this.address = address;
	this.role = role;
	this.accountStatus = accountStatus;
	this.createdOn = createdOn;
}
  public UserDto() {
	
}
//@JsonProperty("id")
  	private Long id;
	//@JsonProperty("firstName")
    private String firstName;
    //@JsonProperty("lastName")
    private String lastName;
    //@JsonProperty("email")
    private String email;
    //@JsonProperty("password")
    private String password;
    //@JsonProperty("mobileNumber")
    private String mobileNumber;
    //@JsonProperty("address")
    private String address;
    //@JsonProperty("role")
    private String role;
    //@JsonProperty("accountStatus")
    private AccountStatus accountStatus;
    //@JsonProperty("createdOn")
    private LocalDateTime createdOn;
    
   
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public AccountStatus getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
    
    

    
}
