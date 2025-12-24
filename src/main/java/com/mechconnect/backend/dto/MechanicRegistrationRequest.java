package com.mechconnect.backend.dto;

/**
 * MechanicRegistrationRequest
 *
 * Part of the MechConnect backend application.
 * Responsible for handling dto related logic.
 */


public class MechanicRegistrationRequest {
public String firstName;
	
	public String lastName;
	
	public String email;
	
	public String password;
	
	public String MobailNumber;
	
	public String YearsOfExperience;
	
	public String Specialization;
	
	public String  ServiceLocation;
	
	public  String Certifications;
	
	public 	String Bio;

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

	public String getpassword() {
		return password;
	}

	public String getYearsOfExperience() {
		return YearsOfExperience;
	}

	public void setYearsOfExperience(String yearsOfExperience) {
		YearsOfExperience = yearsOfExperience;
	}

	public String getSpecialization() {
		return Specialization;
	}

	public void setSpecialization(String specialization) {
		Specialization = specialization;
	}

	public String getServiceLocation() {
		return ServiceLocation;
	}

	public void setServiceLocation(String serviceLocation) {
		ServiceLocation = serviceLocation;
	}

	public String getCertifications() {
		return Certifications;
	}

	public void setCertifications(String certifications) {
		Certifications = certifications;
	}

	public String getBio() {
		return Bio;
	}

	public void setBio(String bio) {
		Bio = bio;
	}


}
