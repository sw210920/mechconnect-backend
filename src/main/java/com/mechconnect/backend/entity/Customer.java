package com.mechconnect.backend.entity;

/**
 * Customer
 *
 * Part of the MechConnect backend application.
 * Responsible for handling entity related logic.
 */


import java.time.LocalDateTime;
import java.util.Set;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long customerId;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="MobailNumber")
    private String mobailNumber;

    @Column(name="Address")
    private String address;

    @Column (name="otp")
    private String otp;

    @Column (name="otpExpiry")
    private LocalDateTime otpExpiry;
    
    @OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
    private Set<Orders> orderList;

   
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getMobailNumber() { return mobailNumber; }
    public void setMobailNumber(String mobailNumber) { this.mobailNumber = mobailNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    
    
    public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getOtpExpiry() {
		return otpExpiry;
	}

	public void setOtpExpiry(LocalDateTime otpExpiry) {
		this.otpExpiry = otpExpiry;
	}

	public String getName() {
        return firstName + " " + lastName;
    }

    public void setName(String name) {
        if (name.contains(" ")) {
            String[] parts = name.split(" ", 2);
            this.firstName = parts[0];
            this.lastName = parts[1];
        } else {
            this.firstName = name;
            this.lastName = "";
        }
    }

    public String getPhone() { return mobailNumber; }
    public void setPhone(String phone) { this.mobailNumber = phone; }

	
}