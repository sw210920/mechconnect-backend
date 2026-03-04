package com.mechconnect.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mechconnect.backend.entity.ServicePackage;
import com.mechconnect.backend.entity.enums.PackageType;

public interface ServicePackageRepository extends JpaRepository<ServicePackage, Long> {

	 List<ServicePackage> findByPackageTypeAndActiveTrue(Enum packageType);

	 
	    
	    
	    // Only active packages
	    List<ServicePackage> findByActiveTrue();

	    // By type + active
	    List<ServicePackage> findByPackageTypeAndActiveTrue(PackageType packageType);

	    // By creator + active
	    List<ServicePackage> findByCreatedByIdAndActiveTrue(Long createdById);
}
