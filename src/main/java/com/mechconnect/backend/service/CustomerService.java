package com.mechconnect.backend.service;

/**
 * CustomerService
 *
 * Part of the MechConnect backend application.
 * Responsible for handling service related logic.
 */


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mechconnect.backend.dto.OrderResponseWithoutCustomerDto;
import com.mechconnect.backend.dto.PasswordResetRequest;
import com.mechconnect.backend.dto.CustomerRegistrationRequest;
import com.mechconnect.backend.entity.Customer;
import com.mechconnect.backend.entity.Orders;


public interface CustomerService {

	String SaveCustomer(CustomerRegistrationRequest registerCustomer);

	List<Customer> getAllCustomer();

	String deleteCustomer(Long CustomerId);

	String createOrder(Long CustomerId);

	String updateCustomer(Long id, CustomerRegistrationRequest registerCustomer);

	List<Orders> getOrdersById(Long userId);


	String SaveOrders(OrderResponseWithoutCustomerDto ordersResponseWithoutCustomers);

	Customer findByEmail(String email);

	Customer getCustomerById(Long CustomerId);

	Customer saveCustomer(Customer existing);
	
	Customer updateCustomer(Customer customer);
	
    boolean changePassword(Long CustomerId, String oldPassword, String newPassword);

	
}
