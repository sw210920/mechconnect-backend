package com.mechconnect.backend.controller;

/**
 * MechanicRequestController
 *
 * Part of the MechConnect backend application.
 * Responsible for handling controller related logic.
 */

import com.mechconnect.backend.dto.MechanicRequestCreateDto;
import com.mechconnect.backend.entity.MechanicRequest;
import com.mechconnect.backend.service.MechanicRequestService;

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
public class MechanicRequestController {

    @Autowired
    private MechanicRequestService service;
    @Autowired
    private MechanicRequestService mechanicRequestService;


    @CrossOrigin
    // CUSTOMER
    @PostMapping("/customers/sendRequest")
    public ResponseEntity<String> sendRequest(
            @RequestBody MechanicRequestCreateDto dto) {

        return ResponseEntity.ok(
                mechanicRequestService.sendRequest(dto)
        );
    }

    @CrossOrigin
    // MECHANIC
    @GetMapping("/mechanic/{mechanicId}/requests")
    public List<MechanicRequest> getRequests(@PathVariable Long mechanicId) {
        return service.getPendingRequests(mechanicId);
    }

    @CrossOrigin
    @PutMapping("/mechanic/request/{id}/accept")
    public ResponseEntity<?> accept(@PathVariable Long id) {
        service.updateStatus(id, "ACCEPTED");
        return ResponseEntity.ok("Request accepted and order created");
    }

    @CrossOrigin
    @PutMapping("/mechanic/request/{id}/reject")
    public ResponseEntity<?> reject(@PathVariable Long id) {
        service.updateStatus(id, "REJECTED");
        return ResponseEntity.ok("Request rejected and deleted");
    }
}
