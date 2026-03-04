package com.mechconnect.backend.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechconnect.backend.dto.CreatePackageRequest;
import com.mechconnect.backend.dto.ServicePackageResponseDto;
import com.mechconnect.backend.entity.Admin;
import com.mechconnect.backend.entity.Customer;
import com.mechconnect.backend.entity.Mechanic;
import com.mechconnect.backend.entity.PackageService;
import com.mechconnect.backend.entity.ServicePackage;
import com.mechconnect.backend.entity.enums.CreatedByRole;
import com.mechconnect.backend.entity.enums.PackageType;
import com.mechconnect.backend.exception.ResourceNotFoundException;
import com.mechconnect.backend.repository.AdminRepository;
import com.mechconnect.backend.repository.CustomerRepository;
import com.mechconnect.backend.repository.MechanicRepository;
import com.mechconnect.backend.repository.ServicePackageRepository;
import com.mechconnect.backend.repository.ServiceRepository;
import com.mechconnect.backend.service.ServicePackageService;

import jakarta.transaction.Transactional;


@Service

public class ServicePackageServiceImpl implements ServicePackageService {

	
	  private final ServiceRepository serviceRepository;
	  private final ServicePackageRepository packageRepository;
	  private final AdminRepository adminRepository;
	  private final MechanicRepository mechanicRepository;
	  private final CustomerRepository customerRepository;
	  
	  public ServicePackageServiceImpl(
	            ServiceRepository serviceRepository,
	            ServicePackageRepository packageRepository,
	            AdminRepository adminRepository,
	            MechanicRepository mechanicRepository,
	            CustomerRepository customerRepository) {

	        this.serviceRepository = serviceRepository;
	        this.packageRepository = packageRepository;
	        this.adminRepository = adminRepository;
	        this.mechanicRepository = mechanicRepository;
	        this.customerRepository = customerRepository;
	    }
	  
	  
	  @Override
	  @Transactional
	  public ServicePackageResponseDto createPackage(CreatePackageRequest request) {

	      ServicePackage servicePackage = new ServicePackage();

	   // 🔐 Get logged-in user role
	      Authentication authentication =
	              SecurityContextHolder.getContext().getAuthentication();

	      String loggedInRole = authentication.getAuthorities()
	              .iterator()
	              .next()
	              .getAuthority();

	      // 🔒 Role validation
	      if (loggedInRole.equals("ROLE_MECHANIC")
	              && request.getPackageType() != PackageType.MECHANIC) {

	          throw new IllegalArgumentException(
	                  "Mechanics can only create MECHANIC packages");
	      }

	      if (loggedInRole.equals("ROLE_CUSTOMER")
	              && request.getPackageType() != PackageType.CUSTOMER) {

	          throw new IllegalArgumentException(
	                  "Customers can only create CUSTOMER packages");
	      }

	      // ✅ Set createdByRole from logged-in role
	      if (loggedInRole.equals("ROLE_ADMIN")) {
	          servicePackage.setCreatedByRole(CreatedByRole.ADMIN);
	      }
	      else if (loggedInRole.equals("ROLE_MECHANIC")) {
	          servicePackage.setCreatedByRole(CreatedByRole.MECHANIC);
	      }
	      else {
	          servicePackage.setCreatedByRole(CreatedByRole.CUSTOMER);
	      }
	      
	      
	   // 🔐 Get logged-in username (email)
	      String username = authentication.getName();

	      if (loggedInRole.equals("ROLE_ADMIN")) {

	          Admin admin = adminRepository.findByEmail(username)
	                  .orElseThrow(() ->
	                          new ResourceNotFoundException("Admin not found"));

	          servicePackage.setCreatedById(admin.getAdminId());
	      }
	      else if (loggedInRole.equals("ROLE_MECHANIC")) {

	          Mechanic mechanic = mechanicRepository.findByEmail(username)
	                  .orElseThrow(() ->
	                          new ResourceNotFoundException("Mechanic not found"));

	          servicePackage.setCreatedById(mechanic.getMechanicId());
	      }
	      else {

	          Customer customer = customerRepository.findByEmail(username)
	                  .orElseThrow(() ->
	                          new ResourceNotFoundException("Customer not found"));

	          servicePackage.setCreatedById(customer.getCustomerId());
	      }
	      
	      
	      servicePackage.setPackageName(request.getPackageName());
	      servicePackage.setDescription(request.getDescription());
	      servicePackage.setPackageType(request.getPackageType());
	      
	      
	      
	      
	      servicePackage.setDiscount(
	              request.getDiscount() != null ? request.getDiscount() : 0.0
	      );
	      servicePackage.setEstimatedTime(request.getEstimatedTime());
	      servicePackage.setActive(true);

	      double basePrice = 0.0;

	      for (CreatePackageRequest.ServiceItem item : request.getServices()) {

	          com.mechconnect.backend.entity.Service service =
	        		  serviceRepository.findById(item.getServiceId())
	        		    .orElseThrow(() ->
	        		        new ResourceNotFoundException(
	        		            "Service with ID " + item.getServiceId() + " not found"
	        		        )
	        		    );

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

	      ServicePackage saved = packageRepository.save(servicePackage);

	      return mapToResponse(saved);
	  }	
	
	
	  private ServicePackageResponseDto mapToResponse(ServicePackage pkg) {

		  ServicePackageResponseDto response = new ServicePackageResponseDto();

		    response.setId(pkg.getId());
		    response.setPackageName(pkg.getPackageName());
		    response.setDescription(pkg.getDescription());
		    response.setPackageType(pkg.getPackageType());
		    response.setCreatedByRole(pkg.getCreatedByRole());
		    response.setBasePrice(pkg.getBasePrice());
		    response.setDiscount(pkg.getDiscount());
		    response.setFinalPrice(pkg.getFinalPrice());
		    response.setEstimatedTime(pkg.getEstimatedTime());
		    response.setActive(pkg.getActive());
		    response.setCreatedAt(pkg.getCreatedAt());

		    List<ServicePackageResponseDto.ServiceItemResponse> serviceList =
		            pkg.getServices().stream().map(ps -> {

		            	ServicePackageResponseDto.ServiceItemResponse item =
		                        new ServicePackageResponseDto.ServiceItemResponse();

		                item.setServiceId(ps.getService().getId());
		                item.setServiceName(ps.getService().getName());
		                item.setPrice(ps.getService().getPrice());
		                item.setQuantity(ps.getQuantity());

		                return item;
		            }).toList();

		    response.setServices(serviceList);

		    return response;
		}
	  
	  
	  
	
	  @Override
	  public List<ServicePackageResponseDto> getAllActivePackages() {
	      return packageRepository.findByActiveTrue()
	              .stream()
	              .map(this::mapToResponse)
	              .toList();
	  }

	  @Override
	  public List<ServicePackageResponseDto> getPackagesByType(PackageType type) {
		    return packageRepository.findByPackageTypeAndActiveTrue(type)
		            .stream()
		            .map(this::mapToResponse)
		            .toList();
		}

	@Override
	public List<ServicePackageResponseDto> getPackagesByCreator(Long creatorId) {
	    return packageRepository.findByCreatedByIdAndActiveTrue(creatorId)
	            .stream()
	            .map(this::mapToResponse)
	            .toList();
	}

	@Override
	@Transactional
	public void deactivatePackage(Long id) {
	    ServicePackage pkg = packageRepository.findById(id)
	    	    .orElseThrow(() ->
	            new ResourceNotFoundException(
	                "Package with ID " + id + " not found"
	            )
	        );

	    pkg.setActive(false);
	}
	
	
}
