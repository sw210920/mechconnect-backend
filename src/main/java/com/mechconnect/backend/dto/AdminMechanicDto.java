package com.mechconnect.backend.dto;

public class AdminMechanicDto {

		  private Long mechanicId;
	  private String firstName;
	  private String lastName;
	  private String MobailNumber;
	  private String email;
	  private String address;
	  private String serviceLocation;
	    private String yearsOfExperience;
	    private String specialization;
	    private String certifications;
	    
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
		public String getMobailNumber() {
			return MobailNumber;
		}
		public void setMobailNumber(String mobailNumber) {
			MobailNumber = mobailNumber;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
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
		public String getSpecialization() {
			return specialization;
		}
		public void setSpecialization(String specialization) {
			this.specialization = specialization;
		}
	   
	   
		  public String getCertifications() {
				return certifications;
			}
			public void setCertifications(String certifications) {
				this.certifications = certifications;
			}

		
}
