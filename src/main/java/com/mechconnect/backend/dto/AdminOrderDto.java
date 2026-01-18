package com.mechconnect.backend.dto;

import com.mechconnect.backend.entity.enums.ServiceMode;

public class AdminOrderDto {
	  private Long orderId;
	    private String orderNumber;

	    private String status;

	    private String serviceType;
	    private String packageName;
	    private ServiceMode serviceMode;
	    private String serviceDate;
	    private String serviceTime;

	    private String vehicleMake;
	    private String vehicleModel;
	    private String vehicleRegistrationNumber;

	    private Long customerId;
	    private String customerName;

	    private Long mechanicId;
	    private String mechanicName;
		public Long getOrderId() {
			return orderId;
		}
		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}
		public String getOrderNumber() {
			return orderNumber;
		}
		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getServiceType() {
			return serviceType;
		}
		public void setServiceType(String serviceType) {
			this.serviceType = serviceType;
		}
		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}
		
		
		public ServiceMode getServiceMode() {
			return serviceMode;
		}
		public void setServiceMode(ServiceMode serviceMode) {
			this.serviceMode = serviceMode;
		}
		public String getServiceDate() {
			return serviceDate;
		}
		public void setServiceDate(String serviceDate) {
			this.serviceDate = serviceDate;
		}
		public String getServiceTime() {
			return serviceTime;
		}
		public void setServiceTime(String serviceTime) {
			this.serviceTime = serviceTime;
		}
		public String getVehicleMake() {
			return vehicleMake;
		}
		public void setVehicleMake(String vehicleMake) {
			this.vehicleMake = vehicleMake;
		}
		public String getVehicleModel() {
			return vehicleModel;
		}
		public void setVehicleModel(String vehicleModel) {
			this.vehicleModel = vehicleModel;
		}
		public String getVehicleRegistrationNumber() {
			return vehicleRegistrationNumber;
		}
		public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
			this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		}
		public Long getCustomerId() {
			return customerId;
		}
		public void setCustomerId(Long customerId) {
			this.customerId = customerId;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public Long getMechanicId() {
			return mechanicId;
		}
		public void setMechanicId(Long mechanicId) {
			this.mechanicId = mechanicId;
		}
		public String getMechanicName() {
			return mechanicName;
		}
		public void setMechanicName(String mechanicName) {
			this.mechanicName = mechanicName;
		}

    
	    
	    
}
