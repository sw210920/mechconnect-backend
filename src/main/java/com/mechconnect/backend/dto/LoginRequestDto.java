package com.mechconnect.backend.dto;

/**
 * LoginRequestDto
 *
 * Part of the MechConnect backend application.
 * Responsible for handling dto related logic.
 */


public class LoginRequestDto {

	private String email;
    private String password;
	
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getpassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
    
    

