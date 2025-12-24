package com.mechconnect.backend.service.impl;

/**
 * CustomerServiceImpl
 *
 * Part of the MechConnect backend application.
 * Responsible for handling impl related logic.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mechconnect.backend.dto.OrderResponseWithoutCustomerDto;
import com.mechconnect.backend.dto.CustomerRegistrationRequest;
import com.mechconnect.backend.entity.Customer;
import com.mechconnect.backend.entity.Orders;
import com.mechconnect.backend.repository.CustomerRepository;
import com.mechconnect.backend.repository.OrderRepository;
import com.mechconnect.backend.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	static Long orderNumber=1L;

	@Override
	public String SaveCustomer(CustomerRegistrationRequest registerCustomer) {
		if(registerCustomer!=null) {
			Customer customer=new Customer();
			 customer.setFirstName(registerCustomer.getFirstName());
			 customer.setLastName(registerCustomer.getLastName());
			 customer.setEmail(registerCustomer.getEmail());
			 //customer.setPassword(registerCustomer.getpassword());
			 
			 customer.setPassword(
				        passwordEncoder.encode(registerCustomer.getpassword())
				    );
			 customer.setMobailNumber(registerCustomer.getMobailNumber());
			 customer.setAddress(registerCustomer.getAddress());
			 
			 customerRepository.save(customer);
		    
		    return "User updated";
		}
		
		return "User not update";
		
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCustomer(Long id) {
		if(id!=null) {
		Optional<Customer> customerOptional=customerRepository.findById(id);
		if(customerOptional.isPresent()) {
			customerRepository.delete(customerOptional.get());
			return "User Deleted";
		}
	}
	return "User Not Deleted";
}

	@Override
	public String createOrder(Long id) {
		// TODO Auto-generated method stub

		if(id!=null) {
			Optional<Customer> customerOptional=customerRepository.findById(id);
			if(customerOptional.isPresent()) {
				
				Orders order=new Orders();
				order.setOrderNumber("ORD-001" +orderNumber);
				order.setCustomer(customerOptional.get());
				
				orderRepository.save(order);
				orderNumber++;
				return "order created";
			}
		}
		return "Order not created";
	}

	@Override
	public String updateCustomer(Long id, CustomerRegistrationRequest registerCustomer) {
		// TODO Auto-generated method stub
		if(id!=null) {
			Optional<Customer> customerFromDB=customerRepository.findById(id);
			if(customerFromDB.isPresent()) {
				Customer customer=customerFromDB.get();
			}
			}
			return null;
	}

	@Override
	public List<Orders> getOrdersById(Long customerId) {
		// TODO Auto-generated method stub
		if(customerId!=null) {
			Optional<Customer> customer=customerRepository.findById(customerId);
			if(customer.isPresent()) {
				List<Orders> orderList=orderRepository.findByCustomer(customer);
				
				if(orderList!=null) {
					for(Orders o:orderList) {
						System.out.println("OrderID"+o.getOrderId() +"OrderNumber"+o.getOrderNumber()+"Customer:"+customerId);
						
					}
					
				}
				
			}
				
			}
			return null;
		}


	@Override
	public String SaveOrders(OrderResponseWithoutCustomerDto ordersResponseWithoutCustomers) {
		if(ordersResponseWithoutCustomers!=null) {
			 Orders order = new Orders();
			    order.setOrderNumber(ordersResponseWithoutCustomers.getOrderNumber());
			    order.setVehicleMake(ordersResponseWithoutCustomers.getVehicleMake());
			    order.setVehicleModel(ordersResponseWithoutCustomers.getVehicleModel());
			    order.setVehicleYear(ordersResponseWithoutCustomers.getVehicleYear());
			    order.setVehicleRegistrationNumber(ordersResponseWithoutCustomers.getVehicleRegistrationNumber());
			  
			 
			    orderRepository.save(order);
		    
		    return "Order updated";
		}
		
		return "User not update";
	}

	@Override
	public Customer findByEmail(String email) {
		// TODO Auto-generated method stub
		 return customerRepository.findByEmail(email);
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id).orElse(null);
	}

	@Override
	public Customer saveCustomer(Customer existing) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer updatedCustomer) {

	    if (updatedCustomer.getCustomerId() == null) {
	        throw new IllegalArgumentException("Customer ID cannot be null");
	    }

	    return customerRepository.findById(updatedCustomer.getCustomerId())
	            .map(existing -> {
	                existing.setFirstName(updatedCustomer.getFirstName());
	                existing.setLastName(updatedCustomer.getLastName());
	                existing.setEmail(updatedCustomer.getEmail());
	                existing.setMobailNumber(updatedCustomer.getMobailNumber());
	                existing.setAddress(updatedCustomer.getAddress());
	                return customerRepository.save(existing);
	            })
	            .orElse(null);
	}

	
	
	@Override
	public boolean changePassword(Long customerId, String oldPassword, String newPassword) {

	    if (customerId == null) {
	        throw new IllegalArgumentException("Customer ID is null");
	    }

	    Customer customer = customerRepository.findById(customerId)
	            .orElseThrow(() -> new RuntimeException("Customer not found"));

	    if (!passwordEncoder.matches(oldPassword, customer.getPassword())) {
	        return false;
	    }

	    customer.setPassword(passwordEncoder.encode(newPassword));
	    customerRepository.save(customer);
	    return true;
	}

}
