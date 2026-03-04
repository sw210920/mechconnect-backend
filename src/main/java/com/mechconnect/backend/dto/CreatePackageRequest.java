package com.mechconnect.backend.dto;

import java.util.List;

import com.mechconnect.backend.entity.enums.CreatedByRole;
import com.mechconnect.backend.entity.enums.PackageType;

public class CreatePackageRequest {

	
	private String packageName;
    private String description;

    private PackageType packageType;
    private CreatedByRole createdByRole;
 

    private Double discount;
    private Integer estimatedTime;

    private List<ServiceItem> services;

 
    // getters & setters

    public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PackageType getPackageType() {
		return packageType;
	}

	public void setPackageType(PackageType packageType) {
		this.packageType = packageType;
	}

	public CreatedByRole getCreatedByRole() {
		return createdByRole;
	}

	public void setCreatedByRole(CreatedByRole createdByRole) {
		this.createdByRole = createdByRole;
	}

	

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(Integer estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public List<ServiceItem> getServices() {
		return services;
	}

	public void setServices(List<ServiceItem> services) {
		this.services = services;
	}
   
    
    public static class ServiceItem {
      
		private Long serviceId;
        private Integer quantity;

        // getters & setters

        public Long getServiceId() {
			return serviceId;
		}
		public void setServiceId(Long serviceId) {
			this.serviceId = serviceId;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
        
    }
}