package com.mechconnect.backend.service;

import com.mechconnect.backend.dto.CreatePackageRequest;
import com.mechconnect.backend.entity.ServicePackage;

public interface ServicePackageService {

	 ServicePackage createPackage(CreatePackageRequest request);
	
}
