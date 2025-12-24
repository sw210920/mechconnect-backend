package com.mechconnect.backend.service;

/**
 * MechanicRequestService
 *
 * Part of the MechConnect backend application.
 * Responsible for handling service related logic.
 */


import java.util.List;

import com.mechconnect.backend.dto.MechanicRequestCreateDto;
import com.mechconnect.backend.entity.MechanicRequest;
import com.mechconnect.backend.entity.enums.OrderStatus;

public interface MechanicRequestService {

	String sendRequest(MechanicRequestCreateDto dto);

	List<MechanicRequest> getRequestsForCustomer(Long customerId);

	String acceptRequest(Long requestId);

	String rejectRequest(Long requestId);

	Object getAllMechanics();

	List<MechanicRequest> getRequestsForMechanic(Long mechanicId);
	List<MechanicRequest> getPendingRequests(Long mechanicId);

	void updateStatus(Long requestId, String status);

	void updateOrderStatus(Long orderId, OrderStatus status);
	
	

}
