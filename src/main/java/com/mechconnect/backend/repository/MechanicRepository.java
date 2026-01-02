package com.mechconnect.backend.repository;

/**
 * MechanicRepository
 *
 * Part of the MechConnect backend application.
 * Responsible for handling repository related logic.
 */


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mechconnect.backend.entity.Mechanic;
import com.mechconnect.backend.entity.enums.ServiceType;


@Repository
public interface MechanicRepository extends JpaRepository<Mechanic,Long> {

	Mechanic findByEmail(String email);


	

    Mechanic findByEmailIgnoreCase(String email);


	 List<Mechanic> findByServiceLocationIgnoreCaseAndSpecialization(
	            String serviceLocation,
	            ServiceType  specialization
	    );


	 List<Mechanic> findByServiceLocationIgnoreCaseAndSpecializationIn(
	            String serviceLocation,
	            List<ServiceType> types
			 );

}
