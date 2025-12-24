package com.mechconnect.backend.controller;

/**
 * MechanicController
 *
 * Part of the MechConnect backend application.
 * Responsible for handling controller related logic.
 */


import java.time.LocalDateTime;
																																																																																																														
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mechconnect.backend.dto.LoginRequestDto;
import com.mechconnect.backend.dto.MechanicLoginResponseDto;
import com.mechconnect.backend.dto.MechanicProfileResponseDto;
import com.mechconnect.backend.dto.MechanicProfileUpdateRequestDto;
import com.mechconnect.backend.dto.PasswordResetRequest;
import com.mechconnect.backend.dto.MechanicRegistrationRequest;
import com.mechconnect.backend.entity.Customer;
import com.mechconnect.backend.entity.Mechanic;
import com.mechconnect.backend.entity.MechanicRequest;
import com.mechconnect.backend.entity.Orders;
import com.mechconnect.backend.repository.MechanicRepository;
import com.mechconnect.backend.repository.MechanicRequestRepository;
import com.mechconnect.backend.repository.OrderRepository;
import com.mechconnect.backend.service.MechanicRequestService;
import com.mechconnect.backend.service.MechanicService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping(value="/api")
public class MechanicController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	  @Autowired
      private MechanicRepository mechanicRepository;
	
	@Autowired
	MechanicService mechanicService;
	
	public String MechanicNameFromProp;
	
	@Autowired
	private MechanicRequestService mechanicRequestService;
	
	@Autowired
	private MechanicRequestRepository mechanicRequestRepository;
	

	
	@Value
	("${test.Mechanic.name}")
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("In Post Constructor");
		
	}
	
	//localhost:6060/api/saveUser
		@CrossOrigin
	//localhost:6060/api/saveUser
	@PostMapping(value="/saveMechanic",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public String saveCustomer(@RequestBody MechanicRegistrationRequest registerMechanic) {
		System.out.println("Controller Started");
		 System.out.println("In Save Mechanic");
		 System.out.println("ServiceLocation = " + registerMechanic.getServiceLocation());
		return mechanicService.saveMechanic(registerMechanic);
		 
	 }
		
		
		
		
		@CrossOrigin(origins = "http://127.0.0.1:5500")
		@PostMapping("/Mechaniclogin")
		public ResponseEntity<?> mechanicLogin(@RequestBody LoginRequestDto loginRequest) {

		    String email = loginRequest.getEmail().trim();
		    String rawPassword = loginRequest.getpassword();

		    Mechanic mechanic = mechanicService.findByEmail(email);

		    if (mechanic == null) {
		        return ResponseEntity.status(404).body("User not found");
		    }

		    System.out.println("==== LOGIN DEBUG ====");
		    System.out.println("Email entered     : " + email);
		    System.out.println("Password entered  : " + rawPassword);
		    System.out.println("Password in DB    : " + mechanic.getPassword());
		    System.out.println("Is password match : " +
		        passwordEncoder.matches(rawPassword, mechanic.getPassword())
		    );
		    System.out.println("=====================");
		    if (!passwordEncoder.matches(rawPassword, mechanic.getPassword())) {
		        return ResponseEntity.status(401).body("Invalid password");
		    }

		    MechanicLoginResponseDto res = new MechanicLoginResponseDto();
		    res.setMechanicId(mechanic.getMechanicId());
		    res.setFirstName(mechanic.getFirstName());
		    res.setLastName(mechanic.getLastName());
		    res.setEmail(mechanic.getEmail());
		    res.setRole("mechanic");

		    return ResponseEntity.ok(res);
		}

		
			
		
		@GetMapping("/mechanic/{id}")
		public ResponseEntity<?> getMechanicProfile(@PathVariable Long id) {

		    Mechanic mechanic = mechanicService.findById(id);

		    if (mechanic == null) {
		        return ResponseEntity.notFound().build();
		    }

		    MechanicProfileResponseDto res = new MechanicProfileResponseDto();
		    res.setMechanicId(mechanic.getMechanicId());
		    res.setFirstName(mechanic.getFirstName());
		    res.setLastName(mechanic.getLastName());
		    res.setEmail(mechanic.getEmail());
		    res.setMobailNumber(mechanic.getMobailNumber());

		    res.setSpecialization(mechanic.getSpecialization());
		    res.setServiceLocation(mechanic.getServiceLocation());
		    res.setYearsOfExperience(mechanic.getYearsOfExperience());

		    res.setCertifications(mechanic.getCertifications());
		    res.setBio(mechanic.getBio());

		    return ResponseEntity.ok(res);
		}





		
		
       
		@CrossOrigin(origins = "http://127.0.0.1:5500") // allow your frontend
		 // 3️⃣ CHANGE MECHANIC PASSWORD
	    // ============================================
	    @PutMapping("/mechanic/change-password")
	    public ResponseEntity<?> changePassword(@RequestBody PasswordResetRequest req) {

	        boolean success = mechanicService.changePassword(
	                req.getMechanicId(),
	                req.getOldPassword(),
	                req.getNewPassword()
	        );

	        if (!success) {
	            return ResponseEntity.status(400)
	                    .body("Current password incorrect or user not found");
	        }

	        return ResponseEntity.ok("Password updated successfully");
	    }
	

       
       @DeleteMapping("/deleteMechanic/{mechanicId}/{name}")
    	   public String deleteMechanic(@PathVariable Long mechanicId,@PathVariable String name,@RequestParam String email,@RequestParam String Password ,@RequestParam String MobailNumber ,@RequestParam String Bio ,@RequestParam String Certifications ,@RequestParam String YearsOfExperience ,@RequestParam String Specializations ,@RequestParam String ServiceLocation ) {
    	   System.out.println("In Delet API "+mechanicId+" "+name+" "+email+" "+Password+" "+Bio+" "+Certifications+""+MobailNumber+""+YearsOfExperience+""+Specializations+""+ServiceLocation+"");
    	   return mechanicService.deleteMechanic(mechanicId);
       }
       
       
          
     
       
       @GetMapping("/getOrdersByIdm/{mechanicId}")
       public List<Orders> getOrdersById(@PathVariable Long mechanicId){
    	   System.out.println("Get Orders By ID");
    	   return mechanicService.getOrdersById2(mechanicId);
       }
          
       
       @GetMapping("/test")
       public String test() {
           return "MECHANIC CONTROLLER WORKING";
       }

       
       
       @CrossOrigin
       @PutMapping("/mechanic/update")
       public ResponseEntity<?> updateMechanic(
               @RequestBody MechanicProfileUpdateRequestDto req
       ) {

           Mechanic mechanic = mechanicService.findById(req.getMechanicId());

           if (mechanic == null) {
               return ResponseEntity.notFound().build();
           }

           mechanic.setFirstName(req.getFirstName());
           mechanic.setLastName(req.getLastName());
           mechanic.setMobailNumber(req.getMobailNumber());
           mechanic.setSpecialization(req.getSpecialization());
           mechanic.setServiceLocation(req.getServiceLocation());
           mechanic.setYearsOfExperience(req.getYearsOfExperience());
           mechanic.setCertifications(req.getCertifications());
           mechanic.setBio(req.getBio());
           System.out.println("ServiceLocation from request = " + req.getServiceLocation());
           mechanicService.save(mechanic);

           return ResponseEntity.ok("Profile updated");
       }
   
       
     

       @PostMapping(
    		    value = "/mechanic/forgot-password",
    		    consumes = MediaType.APPLICATION_JSON_VALUE
    		)
       public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {

           System.out.println("🔥 MECHANIC FORGOT PASSWORD API HIT 🔥");

           String email = request.get("email");

           if (email == null || email.trim().isEmpty()) {
               return ResponseEntity.badRequest().body("Email is required");
           }

           Mechanic mechanic =
               mechanicRepository.findByEmailIgnoreCase(email.trim());

           if (mechanic == null) {
               return ResponseEntity.status(404).body("Email not registered");
           }

           String otp = String.format("%06d", new Random().nextInt(1_000_000));

           mechanic.setOtp(otp);
           mechanic.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
           mechanicRepository.save(mechanic);

           return ResponseEntity.ok("OTP sent successfully");
       }
       
       
       @CrossOrigin
       @PostMapping("/mechanic/verify-otp")
       public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> request) {

           String email = request.get("email");
           String otp = request.get("otp");

           if (email == null || email.trim().isEmpty()
                   || otp == null || otp.trim().isEmpty()) {
               return ResponseEntity.badRequest().body("Email and OTP are required");
           }

           email = email.trim().toLowerCase();
           otp = otp.trim();

           Mechanic mechanic = mechanicRepository.findByEmailIgnoreCase(email);

           if (mechanic == null) {
               return ResponseEntity.status(404).body("Email not registered");
           }

           // 🔐 Check OTP
           if (!otp.equals(mechanic.getOtp())) {
               return ResponseEntity.status(400).body("Invalid OTP");
           }

           // ⏰ Check expiry
           if (mechanic.getOtpExpiry().isBefore(LocalDateTime.now())) {
               return ResponseEntity.status(400).body("OTP expired");
           }

           return ResponseEntity.ok("OTP verified successfully");
       }
       
       @CrossOrigin
       @PostMapping(
           value = "/mechanic/reset-password",
           consumes = MediaType.APPLICATION_JSON_VALUE
       )
       public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {

           System.out.println("🔥 MECHANIC RESET PASSWORD API HIT 🔥");

           String email = request.get("email");
           String otp = request.get("otp");
           String password = request.get("newPassword");

           if (email == null || email.trim().isEmpty()
                   || otp == null || otp.trim().isEmpty()
                   || password == null || password.trim().isEmpty()) {
               return ResponseEntity.badRequest()
                       .body("Email, OTP and new password are required");
           }

           email = email.trim().toLowerCase();
           otp = otp.trim();

           Mechanic mechanic = mechanicRepository.findByEmailIgnoreCase(email);

           if (mechanic == null) {
               return ResponseEntity.status(404).body("Email not registered");
           }

           // 🔐 Validate OTP again (IMPORTANT)
           if (!otp.equals(mechanic.getOtp())) {
               return ResponseEntity.status(400).body("Invalid OTP");
           }

           if (mechanic.getOtpExpiry().isBefore(LocalDateTime.now())) {
               return ResponseEntity.status(400).body("OTP expired");
           }

           // 🔒 Encrypt & update password
           mechanic.setPassword(passwordEncoder.encode(password));

           // 🧹 Clear OTP after successful reset
           mechanic.setOtp(null);
           mechanic.setOtpExpiry(null);

           mechanicRepository.save(mechanic);

           return ResponseEntity.ok("Password reset successfully");
       }

       
       //service order accept(extra api)
       @GetMapping("/AcceptOrder/{mechanicId}")
       public String createOrder(@PathVariable Long mechanicId ) {
    	   System.out.println("Accepted order fore Customer"+mechanicId);
    	   return mechanicService.AcceptOrder(mechanicId);
       }
       
       //view service requests
       @CrossOrigin
       @GetMapping("/mechanic/requests/{mechanicId}")
       public ResponseEntity<List<MechanicRequest>> viewRequestsForMechanic(
               @PathVariable Long mechanicId) {

           List<MechanicRequest> requests =
                   mechanicRequestService.getRequestsForMechanic(mechanicId);

           return ResponseEntity.ok(requests);
       }

       //service request accept
       @CrossOrigin
       @PutMapping("/request/accept/{requestId}")
       public String acceptRequest(@PathVariable Long requestId) {
           return mechanicRequestService.acceptRequest(requestId);
       } 
       
       
       //service request reject
       @CrossOrigin
       @PutMapping("/request/reject/{requestId}")
       public String rejectRequest(@PathVariable Long requestId) {

           MechanicRequest request = mechanicRequestRepository.findById(requestId)
                   .orElseThrow(() -> new RuntimeException("Request not found"));

           // 1️⃣ update request status
           request.setStatus("REJECTED");
           mechanicRequestRepository.save(request);

           // 2️⃣ create rejected order
           Orders order = new Orders();
           order.setCustomer(request.getCustomer());
           order.setMechanic(request.getMechanic());
           order.setMechanicRequest(request);
           order.setServiceType(request.getServiceType());
           order.setServiceLocation(request.getServiceLocation());
           order.setDate(request.getDate());
           order.setTime(request.getTime());

           order.setStatus("REJECTED"); // 🔴 important
           MechanicRequestRepository.save(order);

           return "Request rejected & order stored";
       }


       
       
       
     // Mechanic password forgot
       @CrossOrigin
       @PostMapping("/mechanic/forgot-password/find-user")
       public ResponseEntity<?> forgotPassword1(@RequestBody Map<String, String> request) {

           String email = request.get("email");

           if (email == null || email.trim().isEmpty()) {
               return ResponseEntity.badRequest().body("Email is required");
           }

           email = email.trim().toLowerCase();

           System.out.println("Searching email: [" + email + "]");

           Mechanic mechanic = mechanicRepository.findByEmail(email); // ✅ FIXED

           if (mechanic == null) {
               return ResponseEntity.status(404).body("Email not registered");
           }

           String otp = String.valueOf(new Random().nextInt(900000) + 100000);

           mechanic.setOtp(otp);
           mechanic.setOtpExpiry(LocalDateTime.now().plusMinutes(5));

          mechanicRepository.save(mechanic);

           return ResponseEntity.ok("OTP sent successfully");
       }
       
       
       @CrossOrigin
       @PostMapping("/mechanic/forgot-password/verify-otp")
       public ResponseEntity<?> verifyOtp1(@RequestBody Map<String, String> request) {

           String email = request.get("email");
           String otp = request.get("otp");

           if (email == null || email.trim().isEmpty()
                   || otp == null || otp.trim().isEmpty()) {
               return ResponseEntity.badRequest().body("Email and OTP are required");
           }

           email = email.trim().toLowerCase();
           otp = otp.trim();

         Mechanic mechanic = mechanicRepository.findByEmail(email);

           if (mechanic == null) {
               return ResponseEntity.status(404).body("Email not registered");
           }

           // 🔐 Check OTP
           if (!otp.equals(mechanic.getOtp())) {
               return ResponseEntity.status(400).body("Invalid OTP");
           }

           // ⏰ Check expiry
           if (mechanic.getOtpExpiry().isBefore(LocalDateTime.now())) {
               return ResponseEntity.status(400).body("OTP expired");
           }

           return ResponseEntity.ok("OTP verified successfully");
       }
       





//4 RESET PASSWORD

@CrossOrigin
@PostMapping("/mechanic/forgot-password/reset-password")
public ResponseEntity<?> resetPassword1(@RequestBody Map<String, String> request) {

    String email = request.get("email");
    String newPassword = request.get("newPassword");

    // ✅ Validation
    if (email == null || email.trim().isEmpty()
            || newPassword == null || newPassword.trim().isEmpty()) {
        return ResponseEntity.badRequest().body("Email and new password are required");
    }

    email = email.trim().toLowerCase();
    newPassword = newPassword.trim();

    Mechanic mechanic = mechanicRepository.findByEmail(email);

    if (mechanic == null) {
        return ResponseEntity.status(404).body("Email not registered");
    }

    // 🔐 Ensure OTP exists (means it was generated & verified)
    if (mechanic.getOtp() == null || mechanic.getOtpExpiry() == null) {
        return ResponseEntity.status(400).body("OTP verification required");
    }

    // ⏰ Check OTP expiry again for safety
    if (mechanic.getOtpExpiry().isBefore(LocalDateTime.now())) {
        return ResponseEntity.status(400).body("OTP expired");
    }

    // 🔐 Encrypt & update password
    mechanic.setPassword(passwordEncoder.encode(newPassword));

    // 🧹 Clear OTP after successful reset
    mechanic.setOtp(null);
    mechanic.setOtpExpiry(null);

    mechanicRepository.save(mechanic);

    return ResponseEntity.ok("Password reset successful");
}

       
       
   }


       

