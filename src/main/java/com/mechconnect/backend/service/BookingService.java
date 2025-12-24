package com.mechconnect.backend.service;

/**
 * BookingService
 *
 * Part of the MechConnect backend application.
 * Responsible for handling service related logic.
 */


import com.mechconnect.backend.dto.BookingCreateRequest;
import com.mechconnect.backend.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking saveBooking(BookingCreateRequest req);

    
    List<Booking> findByCustomerId(Long customerId);
}
