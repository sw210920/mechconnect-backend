package com.mechconnect.backend.service;

/**
 * MechanicService
 *
 * Part of the MechConnect backend application.
 * Responsible for handling service related logic.
 */


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mechconnect.backend.dto.OrderResponseWithoutCustomerDto;
import com.mechconnect.backend.dto.OrderResponseWithoutMechanicDto;
import com.mechconnect.backend.dto.MechanicRegistrationRequest;
import com.mechconnect.backend.entity.Mechanic;
import com.mechconnect.backend.entity.Orders;

public interface MechanicService {


	List<Mechanic> getAllMechanic();

	Mechanic updateMechanic(Long id, MechanicRegistrationRequest registerMechanic);

	String saveMechanic(MechanicRegistrationRequest registerMechanic);

	String deleteMechanic(Long id);

	String AcceptOrder(Long mechanicId);

	List<Orders> getOrdersById2(Long mechanicId);

    Mechanic findByEmail(String email);

	List<Mechanic> findAll();
	
	 Mechanic findById(Long id);  

	Mechanic getMechanicById(Long mechanicId);

	List<Mechanic> getAllMechanic(Long mechanicId);

	Mechanic updateMechanic(Mechanic mechanic);

    boolean changePassword(Long mechanicId, String oldPassword, String newPassword);

	
	 List<Mechanic> findNearbyMechanics(String serviceLocation, String serviceType);

	 void save(Mechanic mechanic); 


	

}
