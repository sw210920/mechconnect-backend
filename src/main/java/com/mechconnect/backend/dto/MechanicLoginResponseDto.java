package com.mechconnect.backend.dto;

/**
 * MechanicLoginResponseDto
 *
 * Part of the MechConnect backend application.
 * Responsible for handling dto related logic.
 */


public class MechanicLoginResponseDto {
	

	    private Long mechanicId;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String role;
		public Long getMechanicId() {
			return mechanicId;
		}
		public void setMechanicId(Long mechanicId) {
			this.mechanicId = mechanicId;
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
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}

	 
	    
	    
	}

