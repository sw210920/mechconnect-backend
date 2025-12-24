package com.mechconnect.backend.dto;

/**
 * OrderResponseWithoutMechanicDto
 *
 * Part of the MechConnect backend application.
 * Responsible for handling dto related logic.
 */


import java.util.List;

public class OrderResponseWithoutMechanicDto {
	
	private Object MechanicId;
	public Long OrderId2;
	public String orderNumber1;
	public String VehicleMake;
	public String VehicleModel;
	public String VehicleYear;
	public String VehicleRegistrationNumber;

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

	
	
	public Object getMechanicId() {
		return MechanicId;
	}
	public void setMechanicId(Object mechanicId) {
		MechanicId = mechanicId;
	}
	public Long getOrderId() {
		return OrderId2;
	}
	public void setOrderId(Long orderId) {
		OrderId2 = orderId;
	}
	public String getOrderNumber() {
		return orderNumber1;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber1 = orderNumber;
	
	

	}
	public void serOrderId1(String orderId1) {
		// TODO Auto-generated method stub
		
	}
	public void setOrderId1(String orderId1) {
		// TODO Auto-generated method stub
		
	}
	public static void setOrders(List<OrderResponseWithoutMechanicDto> ordersResponseList) {
		// TODO Auto-generated method stub
		
	}
	
}
