package com.mechconnect.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mechconnect.backend.dto.AdminCustomerDto;
import com.mechconnect.backend.dto.AdminDashboardStatsDto;
import com.mechconnect.backend.dto.AdminLoginRequestDto;
import com.mechconnect.backend.dto.AdminMechanicDto;
import com.mechconnect.backend.dto.AdminOrderDto;
import com.mechconnect.backend.dto.AdminResponseDto;
import com.mechconnect.backend.entity.Customer;
import com.mechconnect.backend.entity.Orders;
import com.mechconnect.backend.repository.CustomerRepository;
import com.mechconnect.backend.repository.MechanicRepository;
import com.mechconnect.backend.repository.OrderRepository;
import com.mechconnect.backend.service.AdminService;

@RestController
@RequestMapping("/api/admin")

public class AdminController {

    @Autowired
    private AdminService adminService;
    
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MechanicRepository mechanicRepository;

    @Autowired
    private OrderRepository orderRepository;
    
    
    
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<AdminResponseDto> login(
            @RequestBody AdminLoginRequestDto request) {
    	 System.out.println("🔥 ADMIN LOGIN API HIT");
    	    System.out.println("Email = " + request.getEmail());
        return ResponseEntity.ok(adminService.login(request));
    }
    
    
//    // 🔹 View all customers
//    @CrossOrigin
//    @GetMapping("/customers")
//    public ResponseEntity<List<Customer>> getAllCustomers() {
//        return ResponseEntity.ok(adminService.getAllCustomers());
//    }
//
//    // 🔹 View all mechanics
//    @CrossOrigin
//    @GetMapping("/mechanics")
//    public ResponseEntity<List<Mechanic>> getAllMechanics() {
//        return ResponseEntity.ok(adminService.getAllMechanics());
//    }

    
    /* =============================
    1️⃣ VIEW ALL CUSTOMERS
 ============================== */
    @GetMapping("/customers")
    public ResponseEntity<List<AdminCustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(adminService.getAllCustomers());
    }


 /* =============================
    2️⃣ VIEW ALL MECHANICS
 ============================== */
 @GetMapping("/mechanics")
 public ResponseEntity<List<AdminMechanicDto>> getAllMechanics() {

     return ResponseEntity.ok(adminService.getAllMechanics());
 }

 /* =============================
    3️⃣ VIEW ALL ORDERS
 ============================== */
 @CrossOrigin
 @GetMapping("/orders")
 public ResponseEntity<List<AdminOrderDto>> getAllOrders() {
     return ResponseEntity.ok(adminService.getAllOrders());
 }


 @CrossOrigin
 @GetMapping("/stats")
 public ResponseEntity<AdminDashboardStatsDto> getAdminStats() {
     return ResponseEntity.ok(adminService.getDashboardStats());
 }
 
 
}
