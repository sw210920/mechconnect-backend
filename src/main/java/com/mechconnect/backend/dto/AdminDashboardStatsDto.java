package com.mechconnect.backend.dto;

public class AdminDashboardStatsDto {
	 private long totalCustomers;
	    private long totalMechanics;
	    private long totalOrders;
	    
	    public AdminDashboardStatsDto() {}

	    public AdminDashboardStatsDto(long totalCustomers, long totalMechanics, long totalOrders) {
	        this.totalCustomers = totalCustomers;
	        this.totalMechanics = totalMechanics;
	        this.totalOrders = totalOrders;
	    }

	    public long getTotalCustomers() {
	        return totalCustomers;
	    }

	    public void setTotalCustomers(long totalCustomers) {
	        this.totalCustomers = totalCustomers;
	    }

	    public long getTotalMechanics() {
	        return totalMechanics;
	    }

	    public void setTotalMechanics(long totalMechanics) {
	        this.totalMechanics = totalMechanics;
	    }

	    public long getTotalOrders() {
	        return totalOrders;
	    }

	    public void setTotalOrders(long totalOrders) {
	        this.totalOrders = totalOrders;
	    }
	}
