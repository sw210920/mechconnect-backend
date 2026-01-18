package com.mechconnect.backend.service;

import java.util.List;

import com.mechconnect.backend.dto.AdminCustomerDto;
import com.mechconnect.backend.dto.AdminLoginRequestDto;
import com.mechconnect.backend.dto.AdminMechanicDto;
import com.mechconnect.backend.dto.AdminOrderDto;
import com.mechconnect.backend.dto.AdminResponseDto;




public interface AdminService {

	AdminResponseDto login(AdminLoginRequestDto request);
	List<AdminCustomerDto> getAllCustomers();

    List<AdminMechanicDto> getAllMechanics();
 
    
    List<AdminOrderDto> getAllOrders();
    
}
