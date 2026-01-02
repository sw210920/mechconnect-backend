package com.mechconnect.backend.service;

/**
 * MechanicRequestService
 *
 * Part of the MechConnect backend application.
 * Responsible for handling service related logic.
 */


import java.util.List;

import com.mechconnect.backend.dto.MechanicFetchPendingRequestsDto;
import com.mechconnect.backend.dto.ServiceRequestCreateDto;
import com.mechconnect.backend.entity.ServiceRequest;
import com.mechconnect.backend.entity.enums.OrderStatus;

public interface ServiceRequestService {

	String sendRequest(ServiceRequestCreateDto dto);



	

	String rejectRequest(Long requestId);


	
	
	
	    List<MechanicFetchPendingRequestsDto> getRequestsForMechanic(Long mechanicId);



	void acceptOrder(Long requestId, String status);

//	void updateOrderStatus(Long orderId, OrderStatus status);
	
	

}
