package com.mechconnect.backend.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mechconnect.backend.entity.enums.CreatedByRole;
import com.mechconnect.backend.entity.enums.PackageType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "service_packages")
public class ServicePackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String packageName;

    private String description;

    /**
     * ADMIN / MECHANIC / CUSTOMER
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PackageType packageType;

    /**
     * Who created this package
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CreatedByRole createdByRole;

    /**
     * adminId / mechanicId / customerId
     * null ONLY for ADMIN global packages
     */
    private Long createdById;

    /**
     * Pricing
     */
    @Column(nullable = false)
    private Double basePrice;

    private Double discount = 0.0;

    @Column(nullable = false)
    private Double finalPrice;

    /**
     * Estimated service duration (minutes)
     */
    private Integer estimatedTime;

    private Boolean active = true;

    /**
     * Package → Services
     */
    @OneToMany(
    	    mappedBy = "servicePackage",
    	    cascade = CascadeType.ALL,
    	    orphanRemoval = true
    	)
    	@JsonManagedReference
    	private List<PackageService> services = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

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

	public Long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
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

	public List<PackageService> getServices() {
		return services;
	}

	public void setServices(List<PackageService> services) {
		this.services = services;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

    /* getters & setters */
    
    
    
}
