package com.mechconnect.backend.dto;

import com.mechconnect.backend.entity.enums.ServiceType;

public class NearbyMechanicCardResponseDto {
	private Long mechanicId;
    private String firstName;
    private String lastName;
    private ServiceType specialization;
    private String serviceLocation;
    private String yearsOfExperience;
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
	public ServiceType getSpecialization() {
		return specialization;
	}
	public void setSpecialization(ServiceType specialization) {
		this.specialization = specialization;
	}
	public String getServiceLocation() {
		return serviceLocation;
	}
	public void setServiceLocation(String serviceLocation) {
		this.serviceLocation = serviceLocation;
	}
	public String getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	
	
	
    
    
    
}
