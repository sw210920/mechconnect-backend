package com.mechconnect.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechconnect.backend.dto.CreatePackageRequest;
import com.mechconnect.backend.entity.PackageService;
import com.mechconnect.backend.entity.ServicePackage;
import com.mechconnect.backend.repository.ServicePackageRepository;
import com.mechconnect.backend.repository.ServiceRepository;
import com.mechconnect.backend.service.ServicePackageService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicePackageServiceImpl implements ServicePackageService {

	  @Autowired
	    private ServiceRepository serviceRepository;
	  
	  @Autowired
	    private ServicePackageRepository packageRepository;

	
	@Override
	@Transactional
    public ServicePackage createPackage(CreatePackageRequest request) {

        ServicePackage servicePackage = new ServicePackage();

        servicePackage.setPackageName(request.getPackageName());
        servicePackage.setDescription(request.getDescription());
        servicePackage.setPackageType(request.getPackageType());
        servicePackage.setCreatedByRole(request.getCreatedByRole());
        servicePackage.setCreatedById(request.getCreatedById());
        servicePackage.setDiscount(
                request.getDiscount() != null ? request.getDiscount() : 0.0
        );
        servicePackage.setEstimatedTime(request.getEstimatedTime());
        servicePackage.setActive(true);

        double basePrice = 0.0;

        for (CreatePackageRequest.ServiceItem item : request.getServices()) {

          com.mechconnect.backend.entity.Service service = serviceRepository.findById(item.getServiceId())
                    .orElseThrow(() -> new RuntimeException("Service not found"));

            PackageService packageService = new PackageService();
            packageService.setServicePackage(servicePackage);
            packageService.setService(service);
            packageService.setQuantity(item.getQuantity());

            servicePackage.getServices().add(packageService);

            basePrice += service.getPrice() * item.getQuantity();
        }

        servicePackage.setBasePrice(basePrice);

        double finalPrice = basePrice - servicePackage.getDiscount();
        servicePackage.setFinalPrice(finalPrice);

        return packageRepository.save(servicePackage);
    }
}
