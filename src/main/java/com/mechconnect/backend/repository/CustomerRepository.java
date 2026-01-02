package com.mechconnect.backend.repository;

/**
 * CustomerRepository
 *
 * Part of the MechConnect backend application.
 * Responsible for handling repository related logic.
 */




import org.springframework.stereotype.Repository;

import com.mechconnect.backend.entity.Customer;


import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	
	

	Customer findByEmail(String email);
   
	
	

}


