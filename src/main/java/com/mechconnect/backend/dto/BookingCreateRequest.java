package com.mechconnect.backend.dto;

/**
 * BookingCreateRequest
 *
 * Part of the MechConnect backend application.
 * Responsible for handling dto related logic.
 */


public class BookingCreateRequest {
    private Long customerId;
    private String packageName;
    private String date;
    private String time;
    private Vehicle vehicle;

    public static class Vehicle {
        private String make;
        private String model;
        private String year;
        private String reg;

        // getters / setters
        public String getMake() { return make; }
        public void setMake(String make) { this.make = make; }

        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }

        public String getYear() { return year; }
        public void setYear(String year) { this.year = year; }

        public String getReg() { return reg; }
        public void setReg(String reg) { this.reg = reg; }
    }

    // getters / setters
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getPackageName() { return packageName; }
    public void setPackageName(String packageName) { this.packageName = packageName; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
}
