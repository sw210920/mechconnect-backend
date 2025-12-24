package com.mechconnect.backend.service.impl;

/**
 * MechanicServiceImpl
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

import com.mechconnect.backend.dto.OrderResponseWithoutMechanicDto;
import com.mechconnect.backend.dto.MechanicRegistrationRequest;
import com.mechconnect.backend.entity.Mechanic;
import com.mechconnect.backend.entity.Orders;
import com.mechconnect.backend.repository.MechanicRepository;
import com.mechconnect.backend.repository.OrderRepository;
import com.mechconnect.backend.service.MechanicService;

@Service
public class MechanicServiceImpl implements MechanicService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	@Autowired
	MechanicRepository mechanicRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	static Long orderNumber1=1L;
	
	@Override
	public String saveMechanic(MechanicRegistrationRequest registerMechanic) {
		// TODO Auto-generated method stub
		if(registerMechanic!=null) {
			Mechanic mechanic=new Mechanic();
			 mechanic.setFirstName(registerMechanic.getFirstName());
			 mechanic.setLastName(registerMechanic.getLastName());
			 mechanic.setEmail(registerMechanic.getEmail());
			// mechanic.setPassword(registerMechanic.getpassword());
			 mechanic.setPassword(
			            passwordEncoder.encode(registerMechanic.getpassword()) );
			 mechanic.setMobailNumber(registerMechanic.getMobailNumber());
			 mechanic.setServiceLocation(registerMechanic.getServiceLocation());
			 mechanic.setYearsOfExperience(registerMechanic.getYearsOfExperience());
			 mechanic.setSpecialization(registerMechanic.getSpecialization());
			 mechanic.setCertifications(registerMechanic.getCertifications());
			 mechanic.setBio(registerMechanic.getBio());
			 mechanicRepository.save(mechanic);
		    
		    return "User updated";
		}
		
		return "User not update";
		
	}


	@Override
	public Mechanic getMechanicById(Long mechanicId) {
	    return mechanicRepository.findById(mechanicId).orElse(null);
	}


	@Override
	 public Mechanic updateMechanic(Long mechanicId, MechanicRegistrationRequest registerMechanic) {
        return mechanicRepository.findById(mechanicId).map(mechanic -> {
            mechanic.setFirstName(registerMechanic.getFirstName());
            mechanic.setLastName(registerMechanic.getLastName());
            mechanic.setMobailNumber(registerMechanic.getMobailNumber());
            mechanic.setYearsOfExperience(registerMechanic.getYearsOfExperience());
            mechanic.setSpecialization(registerMechanic.getSpecialization());
            mechanic.setServiceLocation(registerMechanic.getServiceLocation());
            mechanic.setCertifications(registerMechanic.getCertifications());
            mechanic.setBio(registerMechanic.getBio());
            return mechanicRepository.save(mechanic);
        }).orElse(null);
    }


	@Override
	public String deleteMechanic(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String AcceptOrder(Long id) {
		if(id!=null) {
			Optional<Mechanic> mechanicOptional=mechanicRepository.findById(id);
			if(mechanicOptional.isPresent()) {
				//we create new order
				Orders order1=new Orders();
				order1.setOrderNumber("ORDMAC-001" +orderNumber1);
				order1.setMechanic(mechanicOptional.get());
				
				orderRepository.save(order1);
				orderNumber1++;
				return "order created";
			}
		}
		return "Order not created";
	}

	@Override
	public List<Orders> getOrdersById2(Long id) {
		// TODO Auto-generated method stub
		if(id!=null) {
			Optional<Mechanic> mechanic=mechanicRepository.findById(id);
			if(mechanic.isPresent()) {
				List<Orders> orderList1=OrderRepository.findByMechanic(mechanic);
				
				if(orderList1!=null) {
					for(Orders o:orderList1) {
						System.out.println("OrderID"+o.getOrderId() +"OrderNumber"+o.getOrderNumber()+"Mechanic:"+id);
						
					}
					
				}
				
			}
				
			}
			return null;
		}

	@Override
	public Mechanic findByEmail(String email) {
		// TODO Auto-generated method stub
		 return mechanicRepository.findByEmail(email);
	}



	

	@Override
	public List<Mechanic> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Mechanic> getAllMechanic() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Mechanic> getAllMechanic(Long mechanicId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Mechanic updateMechanic(Mechanic mechanic) {
		Mechanic existing = mechanicRepository
                .findById(mechanic.getMechanicId())
                .orElse(null);

        if (existing == null) return null;

        existing.setFirstName(mechanic.getFirstName());
        existing.setLastName(mechanic.getLastName());
        existing.setMobailNumber(mechanic.getMobailNumber());
        existing.setYearsOfExperience(mechanic.getYearsOfExperience());
        existing.setSpecialization(mechanic.getSpecialization());
        existing.setServiceLocation(mechanic.getServiceLocation());
        existing.setCertifications(mechanic.getCertifications());
        existing.setBio(mechanic.getBio());

        return mechanicRepository.save(existing);
    }


	@Override
	public boolean changePassword(Long mechanicId, String oldPassword, String newPassword) {
		Mechanic mechanic = mechanicRepository.findById(mechanicId).orElse(null);

        if (mechanic == null) return false;

        if (!passwordEncoder.matches(oldPassword, mechanic.getPassword())) {
            return false;
        }

        mechanic.setPassword(passwordEncoder.encode(newPassword));
        mechanicRepository.save(mechanic);

        return true;
    }


	
	  @Override
	    public List<Mechanic> findNearbyMechanics(String serviceLocation, String serviceType) {
	        return mechanicRepository
	                .findByServiceLocationIgnoreCaseAndSpecializationIgnoreCase(
	                		serviceLocation,
	                        serviceType
	                );
	    }


	  @Override
	    public Mechanic findById(Long id) {
	        return mechanicRepository.findById(id).orElse(null);
	    }


	  @Override
	    public void save(Mechanic mechanic) {
	        mechanicRepository.save(mechanic);
	    }

}









