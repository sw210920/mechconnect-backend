package com.mechconnect.backend.controller;

import com.mechconnect.backend.dto.MechanicFetchPendingRequestsDto;
import com.mechconnect.backend.dto.RejectServiceRequestDto;

/**
 * MechanicRequestController
 *
 * Part of the MechConnect backend application.
 * Responsible for handling controller related logic.
 */

import com.mechconnect.backend.dto.ServiceRequestCreateDto;
import com.mechconnect.backend.service.ServiceRequestService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class ServiceRequestController {

    @Autowired
    private ServiceRequestService service;
    @Autowired
    private ServiceRequestService mechanicRequestService;


    @CrossOrigin
    // CUSTOMER BOOK SERVICE REQUEST
    @PostMapping("/customers/sendRequest")
    public ResponseEntity<String> sendRequest(
            @RequestBody ServiceRequestCreateDto dto) {

        return ResponseEntity.ok(
                mechanicRequestService.sendRequest(dto)
        );
    }

    
    
//
//    
    @CrossOrigin
    @GetMapping("/mechanic/{mechananicId}/requests")
    public ResponseEntity<List<MechanicFetchPendingRequestsDto>> getPendingRequests(
            @PathVariable Long mechananicId
    ) {
        return ResponseEntity.ok(
            mechanicRequestService.getRequestsForMechanic(mechananicId)
        );
    }

    
    
//    ACCEPT REQUEST ON MECCHANIC LOG IN+
    @CrossOrigin
    @PutMapping("/mechanic/request/{id}/accept")
    public ResponseEntity<?> accept(@PathVariable Long id) {
        service.acceptOrder(id, "ACCEPTED");
        return ResponseEntity.ok("Request accepted and order created");
    }

    
    
    
    
    
    
////    REJECT REQUEST ON MECHANIC LOGIN
//    @CrossOrigin
//    @PutMapping("/mechanic/request/{id}/reject")
//    public ResponseEntity<?> reject(@PathVariable Long id) {
//        service.acceptOrder(id, "REJECTED");
//        return ResponseEntity.ok("Request rejected and deleted");
//    }
//    
    
    
    @CrossOrigin
    @PostMapping("/mechanic/request/{id}/reject")
    public ResponseEntity<String> rejectServiceRequest(
            @RequestBody RejectServiceRequestDto dto
    ) {
        service.rejectRequest(dto.getRequestId());
        return ResponseEntity.ok("Service request rejected");
    }

    
    
}
