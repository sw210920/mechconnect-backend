package com.mechconnect.backend.repository;

/**
 * MechanicRequestRepository
 *
 * Part of the MechConnect backend application.
 * Responsible for handling repository related logic.
 */


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mechconnect.backend.entity.Mechanic;
import com.mechconnect.backend.entity.MechanicRequest;
import com.mechconnect.backend.entity.Orders;

public interface MechanicRequestRepository extends JpaRepository<MechanicRequest, Long> {
	
    List<MechanicRequest> findByCustomer_CustomerId(Long customerId);

   

    
    List<MechanicRequest> findByMechanic_MechanicIdAndStatus(Long mechanicId, String status);

	static void save(Orders order) {
		// TODO Auto-generated method stub
		
	}

    
}
