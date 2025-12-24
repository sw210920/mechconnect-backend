package com.mechconnect.backend.dto;

/**
 * CustomerRegistrationRequest
 *
 * Part of the MechConnect backend application.
 * Responsible for handling dto related logic.
 */


public class CustomerRegistrationRequest {
public String firstName;
	
	public String lastName;
	
	public String email;
	
	public String Password;
	
	public String MobailNumber;
	
	public String Address;

	public String getMobailNumber() {
		return MobailNumber;
	}

	public void setMobailNumber(String mobailNumber) {
		MobailNumber = mobailNumber;
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

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getpassword() {
		
		return Password;
	}
	public void setpassword(String password) {
		Password = password;
	}

	
	
	
   	
}
