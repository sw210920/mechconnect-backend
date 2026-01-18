package com.mechconnect.backend.dto;

public class AdminCustomerDto {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobailNumber;
    private String address;
    
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	public String getMobailNumber() {
		return mobailNumber;
	}
	public void setMobailNumber(String mobailNumber) {
		this.mobailNumber = mobailNumber;
	}
	
	

    
    
}
