package com.mechconnect.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.mechconnect.backend.entity.enums.CreatedByRole;
import com.mechconnect.backend.entity.enums.PackageType;

public class ServicePackageResponseDto {
	  private Long id;
	    private String packageName;
	    private String description;
	    private PackageType packageType;
	    private CreatedByRole createdByRole;
	    private Double basePrice;
	    private Double discount;
	    private Double finalPrice;
	    private Integer estimatedTime;
	    private Boolean active;
	    private LocalDateTime createdAt;

	    private List<ServiceItemResponse> services;

	    // getters & setters

	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

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

		public Double getBasePrice() {
			return basePrice;
		}

		public void setBasePrice(Double basePrice) {
			this.basePrice = basePrice;
		}

		public Double getDiscount() {
			return discount;
		}

		public void setDiscount(Double discount) {
			this.discount = discount;
		}

		public Double getFinalPrice() {
			return finalPrice;
		}

		public void setFinalPrice(Double finalPrice) {
			this.finalPrice = finalPrice;
		}

		public Integer getEstimatedTime() {
			return estimatedTime;
		}

		public void setEstimatedTime(Integer estimatedTime) {
			this.estimatedTime = estimatedTime;
		}

		public Boolean getActive() {
			return active;
		}

		public void setActive(Boolean active) {
			this.active = active;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public List<ServiceItemResponse> getServices() {
			return services;
		}

		public void setServices(List<ServiceItemResponse> services) {
			this.services = services;
		}

		
		
		
		
		public static class ServiceItemResponse {
	        private Long serviceId;
	        private String serviceName;
	        private Double price;
	        private Integer quantity;
	        
	        
			public Long getServiceId() {
				return serviceId;
			}
			public void setServiceId(Long serviceId) {
				this.serviceId = serviceId;
			}
			public String getServiceName() {
				return serviceName;
			}
			public void setServiceName(String serviceName) {
				this.serviceName = serviceName;
			}
			public Double getPrice() {
				return price;
			}
			public void setPrice(Double price) {
				this.price = price;
			}
			public Integer getQuantity() {
				return quantity;
			}
			public void setQuantity(Integer quantity) {
				this.quantity = quantity;
			}

	        // getters & setters
	    }
	}