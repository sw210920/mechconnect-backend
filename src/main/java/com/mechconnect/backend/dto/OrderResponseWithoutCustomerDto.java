package com.mechconnect.backend.dto;

/**
 * OrderResponseWithoutCustomerDto
 *
 * Part of the MechConnect backend application.
 * Responsible for handling dto related logic.
 */



public class OrderResponseWithoutCustomerDto{

	public Long OrderId;
	public String orderNumber;
	public Long customerId;
	public String VehicleMake;
	public String VehicleModel;
	public String VehicleYear;
	public String VehicleRegistrationNumber;
	public String VehicaleType;
	

	
	public String getVehicaleType() {
		return VehicaleType;
	}
	public void setVehicaleType(String vehicaleType) {
		VehicaleType = vehicaleType;
	}
	public String getVehicleMake() {
		return VehicleMake;
	}
	public void setVehicleMake(String vehicleMake) {
		VehicleMake = vehicleMake;
	}
	public String getVehicleModel() {
		return VehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		VehicleModel = vehicleModel;
	}
	public String getVehicleYear() {
		return VehicleYear;
	}
	public void setVehicleYear(String vehicleYear) {
		VehicleYear = vehicleYear;
	}
	public String getVehicleRegistrationNumber() {
		return VehicleRegistrationNumber;
	}
	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		VehicleRegistrationNumber = vehicleRegistrationNumber;
	}
	public Long getOrderId() {
		return OrderId;
	}
	public void setOrderId(Long orderId) {
		OrderId = orderId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public void setOrderId(String orderId) {
		
		
	}

	
	

}
