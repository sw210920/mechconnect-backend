package com.mechconnect.backend.service.impl;

/**
 * MechanicRequestServiceImpl
 *
 * Part of the MechConnect backend application.
 * Responsible for handling impl related logic.
 */


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mechconnect.backend.dto.MechanicRequestCreateDto;
import com.mechconnect.backend.entity.Customer;
import com.mechconnect.backend.entity.Mechanic;
import com.mechconnect.backend.entity.MechanicRequest;
import com.mechconnect.backend.entity.Orders;
import com.mechconnect.backend.entity.enums.OrderStatus;
import com.mechconnect.backend.repository.CustomerRepository;
import com.mechconnect.backend.repository.MechanicRepository;
import com.mechconnect.backend.repository.MechanicRequestRepository;
import com.mechconnect.backend.repository.OrderRepository;
import com.mechconnect.backend.service.MechanicRequestService;



@Service
public class MechanicRequestServiceImpl implements MechanicRequestService {
	

	    @Autowired
	    private CustomerRepository customerRepo;

	    @Autowired
	    private MechanicRepository mechanicRepo;
	    
	    @Autowired
	    private MechanicRepository mechanicRepository;
	    
	    @Autowired
	    private MechanicRequestRepository mechanicRequestRepository;

	    @Autowired
	    private OrderRepository orderRepository;




	    // CUSTOMER SEND REQUEST
	    @Override
	    public String sendRequest(MechanicRequestCreateDto dto) {

	        if (dto.getCustomerId() == null) {
	            throw new RuntimeException("Customer ID is missing");
	        }

	        if (dto.getMechanicId() == null) {
	            throw new RuntimeException("Mechanic ID is missing");
	        }

	        Customer customer = customerRepo.findById(dto.getCustomerId())
	                .orElseThrow(() -> new RuntimeException("Customer not found"));

	        Mechanic mechanic = mechanicRepo.findById(dto.getMechanicId())
	                .orElseThrow(() -> new RuntimeException("Mechanic not found"));

	        MechanicRequest request = new MechanicRequest();

	       
	        request.setCustomer(customer);
	        request.setMechanic(mechanic);

	    
	        request.setCustomerName(dto.getCustomerName());
	        request.setServiceType(dto.getServiceType());
	        request.setServiceLocation(dto.getServiceLocation());

	        request.setPackageName(dto.getPackageName());
	        request.setServiceDate(dto.getDate());
	        request.setTime(dto.getTime());

	        request.setMake(dto.getMake());
	        request.setModel(dto.getModel());
	        request.setVehicleYear(dto.getVehicleYear());
	        request.setRegistrationNumber(dto.getRegistrationNumber());

	        request.setStatus("PENDING");

	        mechanicRequestRepository.save(request);

	        return "REQUEST_SENT";
	    }



	  
	    

		@Override
		public List<MechanicRequest> getRequestsForCustomer(Long customerId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String acceptRequest(Long requestId) {
			// TODO Auto-generated method stub
			return null;
		}

		

		@Override
		public Object getAllMechanics() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public List<MechanicRequest> getPendingRequests(Long mechanicId) {
		    return mechanicRequestRepository
		            .findByMechanic_MechanicIdAndStatus(mechanicId, "PENDING");
		}

		
		
		
		

		@Override
		public List<MechanicRequest> getRequestsForMechanic(Long mechanicId) {
			// TODO Auto-generated method stub
			return null;
		}

	
		
 

		
		
		 @Override
		    public void updateStatus(Long requestId, String status) {

		        MechanicRequest request = mechanicRequestRepository.findById(requestId)
		                .orElseThrow(() -> new RuntimeException("Request not found"));

		        if ("ACCEPTED".equals(status)) {
		            moveRequestToOrders(request);
		        }

		        mechanicRequestRepository.delete(request);
		    }

		   
		    
		    
		 private void moveRequestToOrders(MechanicRequest req) {

			    Orders order = new Orders();

			    order.setOrderNumber("ORD-" + System.currentTimeMillis());

			    order.setVehicleMake(req.getMake());
			    order.setVehicleModel(req.getModel());
			    order.setVehicleYear(req.getVehicleYear()); 
			    order.setVehicleRegistrationNumber(req.getRegistrationNumber());

			    order.setServiceType(req.getServiceType());
			    order.setServiceDate(req.getServiceDate());
			    order.setServiceTime(req.getTime());
			    order.setPackageName(req.getPackageName());

			    order.setCustomer(req.getCustomer());
			    order.setMechanic(req.getMechanic());

			    order.setStatus(OrderStatus.PENDING);
			    
			    System.out.println("DEBUG vehicleYear = " + req.getVehicleYear());

			    
			    orderRepository.save(order);

			    mechanicRequestRepository.delete(req);
			}

		 @Override
		 public String rejectRequest(Long requestId) {

		     MechanicRequest req = mechanicRequestRepository.findById(requestId)
		             .orElseThrow(() -> new RuntimeException("Request not found"));

		     req.setStatus("REJECTED");
		     mechanicRequestRepository.save(req);

		     Orders order = new Orders();
		     order.setOrderNumber("ORD-" + System.currentTimeMillis());

		     order.setVehicleMake(req.getMake());
		     order.setVehicleModel(req.getModel());
		     order.setVehicleYear(req.getVehicleYear());
		     order.setVehicleRegistrationNumber(req.getRegistrationNumber());

		     order.setServiceType(req.getServiceType());
		     order.setServiceDate(req.getServiceDate());
		     order.setServiceTime(req.getTime());
		     order.setPackageName(req.getPackageName());

		     order.setCustomer(req.getCustomer());
		     order.setMechanic(req.getMechanic());

		     order.setStatus(OrderStatus.REJECTED);

		     orderRepository.save(order);

		     return "Request rejected and archived in orders";
		 }


		 
		 @Override
		 public void updateOrderStatus(Long orderId, OrderStatus status) {

		     Orders order = orderRepository.findById(orderId)
		             .orElseThrow(() -> new RuntimeException("Order not found"));

		     order.setStatus(status);
		     orderRepository.save(order);
		 }
		}
		
		
	


