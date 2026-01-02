package com.mechconnect.backend.mapper;

import com.mechconnect.backend.dto.CustomerOrderDto;
import com.mechconnect.backend.entity.Orders;

public class OrderMapper {

	public static CustomerOrderDto toCustomerOrderDto(Orders order) {

        CustomerOrderDto dto = new CustomerOrderDto();

        dto.setOrderNumber(order.getOrderNumber());
        dto.setServiceType(order.getServiceType());
        dto.setPackageName(order.getPackageName());
        dto.setServiceDate(order.getServiceDate());
        dto.setServiceTime(order.getServiceTime());

        dto.setVehicleMake(order.getVehicleMake());
        dto.setVehicleModel(order.getVehicleModel());

//        dto.setStatus(order.getStatus());

        return dto;
    }
	
}
