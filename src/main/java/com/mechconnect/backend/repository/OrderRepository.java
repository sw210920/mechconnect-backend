package com.mechconnect.backend.repository;

/**
 * OrderRepository
 *
 * Part of the MechConnect backend application.
 * Responsible for handling repository related logic.
 */


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mechconnect.backend.entity.Customer;
import com.mechconnect.backend.entity.Mechanic;
import com.mechconnect.backend.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
  
	

	List<Orders> findByCustomer(Optional<Customer>customer);

	static List<Orders> findByMechanic(Optional<Mechanic> mechanic) {
		// TODO Auto-generated method stub
		return null;
	}
}
