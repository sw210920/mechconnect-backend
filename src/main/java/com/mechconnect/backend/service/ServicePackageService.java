package com.mechconnect.backend.service;

import java.util.List;

import com.mechconnect.backend.dto.CreatePackageRequest;
import com.mechconnect.backend.dto.ServicePackageResponseDto;
import com.mechconnect.backend.entity.ServicePackage;
import com.mechconnect.backend.entity.enums.PackageType;

public interface ServicePackageService {

	ServicePackageResponseDto createPackage(CreatePackageRequest request);
	
	  List<ServicePackageResponseDto> getAllActivePackages();

	    List<ServicePackageResponseDto> getPackagesByType(PackageType type);

	    List<ServicePackageResponseDto> getPackagesByCreator(Long creatorId);

	    void deactivatePackage(Long id);
	 
	 
	 
	 
	 
}
