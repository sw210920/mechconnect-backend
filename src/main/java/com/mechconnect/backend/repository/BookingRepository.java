package com.mechconnect.backend.repository;

/**
 * BookingRepository
 *
 * Part of the MechConnect backend application.
 * Responsible for handling repository related logic.
 */


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mechconnect.backend.entity.Booking;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerId(Long customerId);
}
