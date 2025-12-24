package com.mechconnect.backend.controller;

/**
 * BookingController
 *
 * Part of the MechConnect backend application.
 * Responsible for handling controller related logic.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mechconnect.backend.dto.BookingCreateRequest;
import com.mechconnect.backend.entity.Booking;
import com.mechconnect.backend.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*") // tighten origin in production
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(@RequestBody BookingCreateRequest request) {
        Booking saved = bookingService.saveBooking(request);
        return ResponseEntity.ok(saved);
    }

    // optional: get bookings by customer
    @GetMapping("/customer/{id}")
    public ResponseEntity<?> bookingsByCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.findByCustomerId(id)); // implement in service if needed
    }
}
