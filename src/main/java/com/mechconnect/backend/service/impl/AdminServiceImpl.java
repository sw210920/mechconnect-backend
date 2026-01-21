package com.mechconnect.backend.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.mechconnect.backend.dto.AdminCustomerDto;
import com.mechconnect.backend.dto.AdminDashboardStatsDto;
import com.mechconnect.backend.dto.AdminLoginRequestDto;
import com.mechconnect.backend.dto.AdminMechanicDto;
import com.mechconnect.backend.dto.AdminOrderDto;
import com.mechconnect.backend.dto.AdminResponseDto;
import com.mechconnect.backend.entity.Admin;
import com.mechconnect.backend.entity.Customer;
import com.mechconnect.backend.entity.Mechanic;
import com.mechconnect.backend.entity.Orders;
import com.mechconnect.backend.entity.ServiceRequest;
import com.mechconnect.backend.repository.AdminRepository;
import com.mechconnect.backend.repository.CustomerRepository;
import com.mechconnect.backend.repository.MechanicRepository;
import com.mechconnect.backend.repository.OrderRepository;
import com.mechconnect.backend.repository.ServiceRequestRepository;
import com.mechconnect.backend.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MechanicRepository mechanicRepository;
    
    @Autowired
    private ServiceRequestRepository serviceRequestRepository;
    
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public AdminResponseDto login(AdminLoginRequestDto request) {

        Admin admin = adminRepository.findByEmail(
            request.getEmail().trim().toLowerCase()
        );

        if (admin == null) {
            throw new RuntimeException("Admin not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        AdminResponseDto dto = new AdminResponseDto();
        dto.setAdminId(admin.getAdminId());
        dto.setEmail(admin.getEmail());
        dto.setRole(admin.getRole());

        return dto;
    }
    
    
    @Override
    public List<AdminCustomerDto> getAllCustomers() {
        return customerRepository.findAll()
            .stream()
            .map(c -> {
                AdminCustomerDto dto = new AdminCustomerDto();
                dto.setCustomerId(c.getCustomerId());
                dto.setFirstName(c.getFirstName());
                dto.setLastName(c.getLastName());
                dto.setEmail(c.getEmail());
                dto.setMobailNumber(c.getMobailNumber());
                dto.setAddress(c.getAddress());
                return dto;
            })
            .toList();
	}
  

   

    @Override
    public List<AdminMechanicDto> getAllMechanics() {

        return mechanicRepository.findAll()
                .stream()
                .map(mechanic -> {
                    AdminMechanicDto dto = new AdminMechanicDto();

                    dto.setMechanicId(mechanic.getMechanicId());
                    dto.setFirstName(mechanic.getFirstName());
                    dto.setLastName(mechanic.getLastName());
                    dto.setEmail(mechanic.getEmail());
                    dto.setMobailNumber(mechanic.getMobailNumber());

                    dto.setServiceLocation(mechanic.getServiceLocation());
                    dto.setAddress(mechanic.getAddress());

                    // ✅ specialization (Enum/String safe)
                    if (mechanic.getSpecialization() != null) {
                        dto.setSpecialization(mechanic.getSpecialization().toString());
                    }

                    dto.setYearsOfExperience(mechanic.getYearsOfExperience());
                    dto.setCertifications(mechanic.getCertifications());
                  

                    return dto;
                })
                .toList();
    }


    @Override
    public List<AdminOrderDto> getAllOrders() {

        List<AdminOrderDto> result = new ArrayList<>();

        /* ===================================
           1) Pending Requests (ServiceRequest Table)
        =================================== */
        List<ServiceRequest> pendingRequests =
                serviceRequestRepository.findAllByOrderByRequestIdDesc();

        for (ServiceRequest req : pendingRequests) {

            AdminOrderDto dto = new AdminOrderDto();

            dto.setOrderId(req.getRequestId());
            dto.setOrderNumber("REQ-" + req.getRequestId());

            dto.setStatus(req.getstatus() != null ? req.getstatus().name() : "PENDING");

            dto.setServiceType(req.getServiceType());
            dto.setPackageName(req.getPackageName());
            dto.setServiceMode(req.getServiceMode());

            dto.setServiceDate(req.getServiceDate());
            dto.setServiceTime(req.getTime());

            dto.setVehicleMake(req.getMake());
            dto.setVehicleModel(req.getModel());
            dto.setVehicleRegistrationNumber(req.getRegistrationNumber());

            // Customer info
            if (req.getCustomer() != null) {
                dto.setCustomerId(req.getCustomer().getCustomerId());
                dto.setCustomerName(
                        req.getCustomer().getFirstName() + " " + req.getCustomer().getLastName()
                );
            }

            // Mechanic info
            if (req.getMechanic() != null) {
                dto.setMechanicId(req.getMechanic().getMechanicId());
                dto.setMechanicName(
                        req.getMechanic().getFirstName() + " " + req.getMechanic().getLastName()
                );
            }

            result.add(dto);
        }

        /* ===================================
           2) Orders Table (Accepted / Completed / Rejected)
        =================================== */
        List<Orders> orders = orderRepository.findAllByOrderByCreatedAtDesc();

        for (Orders order : orders) {

            AdminOrderDto dto = new AdminOrderDto();

            dto.setOrderId(order.getOrderId());
            dto.setOrderNumber(order.getOrderNumber());

            dto.setStatus(order.getStatus() != null ? order.getStatus().name() : null);

            dto.setServiceType(order.getServiceType());
            dto.setPackageName(order.getPackageName());

            // if you have serviceMode in Orders entity
            try {
                dto.setServiceMode(order.getServiceMode());
            } catch (Exception e) {
                dto.setServiceMode(null);
            }

            dto.setServiceDate(order.getServiceDate());
            dto.setServiceTime(order.getServiceTime());

            dto.setVehicleMake(order.getVehicleMake());
            dto.setVehicleModel(order.getVehicleModel());
            dto.setVehicleRegistrationNumber(order.getVehicleRegistrationNumber());

            // Customer info
            if (order.getCustomer() != null) {
                dto.setCustomerId(order.getCustomer().getCustomerId());
                dto.setCustomerName(
                        order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName()
                );
            }

            // Mechanic info
            if (order.getMechanic() != null) {
                dto.setMechanicId(order.getMechanic().getMechanicId());
                dto.setMechanicName(
                        order.getMechanic().getFirstName() + " " + order.getMechanic().getLastName()
                );
            }

            result.add(dto);
        }

        return result;
    }


    @Override
    public AdminDashboardStatsDto getDashboardStats() {

        long totalCustomers = customerRepository.count();
        long totalMechanics = mechanicRepository.count();
        long totalOrders = orderRepository.count();

        return new AdminDashboardStatsDto(totalCustomers, totalMechanics, totalOrders);
    }
   
    
   
    
    
}