package com.mechconnect.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mechconnect.backend.entity.ServicePackage;

public interface ServicePackageRepository extends JpaRepository<ServicePackage, Long> {

	 List<ServicePackage> findByPackageTypeAndActiveTrue(Enum packageType);

	    List<ServicePackage> findByCreatedByIdAndActiveTrue(Long createdById);
}
