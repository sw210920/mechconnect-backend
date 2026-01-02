package com.mechconnect.backend.dto;

import com.mechconnect.backend.entity.Customer;
import com.mechconnect.backend.entity.Mechanic;
import com.mechconnect.backend.entity.enums.RequestStatus;
import com.mechconnect.backend.entity.enums.ServiceMode;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class MechanicFetchPendingRequestsDto {

	
	

	    private Long requestId;

	   

	    private Customer customer;

	    private Mechanic mechanic;
	    
	    
	    private String vehicleYear;
	   
	    private String serviceMode;    

	    private String serviceAddress;        
	    
	  
		private String customerName;
	    private String serviceType;
	    
	    
	    private String serviceLocation;
	    private String packageName;
	   
	    private String time;
	    
	   
	    private String ServiceDate;

	   
	    private String make;
	    private String model;
	    private String registrationNumber;

	   

	   
	    private String status;



		public Long getRequestId() {
			return requestId;
		}



		public void setRequestId(Long requestId) {
			this.requestId = requestId;
		}



		public Customer getCustomer() {
			return customer;
		}



		public void setCustomer(Customer customer) {
			this.customer = customer;
		}



		public Mechanic getMechanic() {
			return mechanic;
		}



		public void setMechanic(Mechanic mechanic) {
			this.mechanic = mechanic;
		}



		public String getVehicleYear() {
			return vehicleYear;
		}



		public void setVehicleYear(String vehicleYear) {
			this.vehicleYear = vehicleYear;
		}


		



		public String getServiceMode() {
			return serviceMode;
		}



		public void setServiceMode(String serviceMode) {
			this.serviceMode = serviceMode;
		}



		public String getServiceAddress() {
			return serviceAddress;
		}



		public void setServiceAddress(String serviceAddress) {
			this.serviceAddress = serviceAddress;
		}



		public String getCustomerName() {
			return customerName;
		}



		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}



		public String getServiceType() {
			return serviceType;
		}



		public void setServiceType(String serviceType) {
			this.serviceType = serviceType;
		}



		public String getServiceLocation() {
			return serviceLocation;
		}



		public void setServiceLocation(String serviceLocation) {
			this.serviceLocation = serviceLocation;
		}



		public String getPackageName() {
			return packageName;
		}



		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}



		public String getTime() {
			return time;
		}



		public void setTime(String time) {
			this.time = time;
		}



		public String getServiceDate() {
			return ServiceDate;
		}



		public void setServiceDate(String serviceDate) {
			ServiceDate = serviceDate;
		}



		public String getMake() {
			return make;
		}



		public void setMake(String make) {
			this.make = make;
		}



		public String getModel() {
			return model;
		}



		public void setModel(String model) {
			this.model = model;
		}



		public String getRegistrationNumber() {
			return registrationNumber;
		}



		public void setRegistrationNumber(String registrationNumber) {
			this.registrationNumber = registrationNumber;
		}



		public String getStatus() {
			return status;
		}



		public void setStatus(String status) {
			this.status = status;
		}


		


	
}
