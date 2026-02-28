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
import com.mechconnect.backend.service.AdminService;

@RestController
@RequestMapping("/api/admin")

public class AdminController {

    @Autowired
    private AdminService adminService;
    
    

    
    
//   Admin Log In 
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<AdminResponseDto> login(
            @RequestBody AdminLoginRequestDto request) {
    	 System.out.println("🔥 ADMIN LOGIN API HIT");
    	    System.out.println("Email = " + request.getEmail());
        return ResponseEntity.ok(adminService.login(request));
    }
   

    
//    VIEW ALL CUSTOMERS
    @GetMapping("/customers")
    public ResponseEntity<List<AdminCustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(adminService.getAllCustomers());
    }


 
//    VIEW ALL MECHANICS

 @GetMapping("/mechanics")
 public ResponseEntity<List<AdminMechanicDto>> getAllMechanics() {

     return ResponseEntity.ok(adminService.getAllMechanics());
 }

//  VIEW ALL ORDERS
 @CrossOrigin
 @GetMapping("/orders")
 public ResponseEntity<List<AdminOrderDto>> getAllOrders() {
     return ResponseEntity.ok(adminService.getAllOrders());
 }

//   to fetch dashboad status
 @CrossOrigin
 @GetMapping("/stats")
 public ResponseEntity<AdminDashboardStatsDto> getAdminStats() {
     return ResponseEntity.ok(adminService.getDashboardStats());
 }
 
 
}
