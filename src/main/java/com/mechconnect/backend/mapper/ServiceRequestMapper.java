package com.mechconnect.backend.mapper;

import org.springframework.stereotype.Component;

import com.mechconnect.backend.dto.MechanicFetchPendingRequestsDto;
import com.mechconnect.backend.entity.ServiceRequest;

@Component
public class ServiceRequestMapper {
	public MechanicFetchPendingRequestsDto mapToDTO(ServiceRequest request) {
		MechanicFetchPendingRequestsDto dto = new MechanicFetchPendingRequestsDto();

	    dto.setRequestId(request.getRequestId());
	    dto.setCustomerName(request.getCustomerName());
	    dto.setServiceType(request.getServiceType());
	    dto.setServiceDate(request.getServiceDate());
	    dto.setTime(request.getTime());
	    dto.setStatus(request.getstatus().name());
	    dto.setPackageName(request.getPackageName());
	    dto.setMake(request.getMake());
	    dto.setModel(request.getModel());

	    return dto;
	}

}
