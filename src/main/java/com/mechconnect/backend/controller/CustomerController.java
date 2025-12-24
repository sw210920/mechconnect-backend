package com.mechconnect.backend.controller;

/**
 * CustomerController
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
import com.mechconnect.backend.dto.MechanicRequestCreateDto;
import com.mechconnect.backend.dto.OrderResponseWithoutCustomerDto;
import com.mechconnect.backend.dto.PasswordResetRequest;
import com.mechconnect.backend.dto.CustomerRegistrationRequest;
import com.mechconnect.backend.entity.Customer;
import com.mechconnect.backend.entity.Mechanic;
import com.mechconnect.backend.entity.MechanicRequest;
import com.mechconnect.backend.entity.Orders;
import com.mechconnect.backend.repository.CustomerRepository;
import com.mechconnect.backend.service.CustomerService;
import com.mechconnect.backend.service.MechanicRequestService;
import com.mechconnect.backend.service.MechanicService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping(value="/api")
public class CustomerController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	@Autowired
	CustomerService customerService;
	public String CustomerNameFromProp;
	
	@Autowired
	MechanicService mechanicService;
	@Autowired
	MechanicRequestService mechanicRequestService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Value
	("${test.Customer.name}")
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("In Post Constructor");
		
	}
		
	//localhost:8080/api/saveUser
	@CrossOrigin
	//@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value="/saveCustomer",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public String saveCustomer(@RequestBody CustomerRegistrationRequest registerCustomer) {
		System.out.println("Controller Started");
		 System.out.println("In Save User");
		 
		return customerService.SaveCustomer(registerCustomer);
	}
		
		
		@PostMapping("/SaveOrders")
		 public String SaveOrders(@RequestBody OrderResponseWithoutCustomerDto OrdersResponseWithoutCustomers) {
			System.out.println("Controller Started");
			 System.out.println("In Save Order");
			return customerService.SaveOrders(OrdersResponseWithoutCustomers);
		 
	 }
		
		@CrossOrigin
		@PostMapping(value="/Customerlogin", consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {

		    String cleanEmail = loginRequest.getEmail().trim();
		    Customer customer = customerService.findByEmail(cleanEmail);
		    String rawPassword = loginRequest.getpassword();
		    
		    if (customer == null) {
		        return ResponseEntity.status(404).body("User not found");
		    }

		    if (!passwordEncoder.matches(rawPassword, customer.getPassword())) {
		        return ResponseEntity.status(401).body("Password is incorrect");
		    }

		    
		 //   if (!customer.getPassword().equals(loginRequest.getpassword())) {
		    //    return ResponseEntity.status(401).body("Password is incorrect");
		  //  }

		    // RETURN CUSTOMER OBJECT
		    return ResponseEntity.ok(customer);
		}

		
		// 1) GET PROFILE matching your frontend fetch(`http://localhost:6060/api/${user.customerId}`)
		@CrossOrigin
		@GetMapping("/customer/{customerId}")
	    public ResponseEntity<?> getCustomerProfile(@PathVariable Long customerId) {
	        Customer customer = customerService.getCustomerById(customerId);
	        if (customer == null) return ResponseEntity.status(404).body("Customer not found");
	        return ResponseEntity.ok(customer);
	    }

	    // 2) UPDATE PROFILE
		@CrossOrigin
		@PutMapping("/customer/update") // PUT method
		public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
		    // Map frontend `id` to `customerId` if necessary
		    if (customer.getCustomerId() == null && customer.getCustomerId() != null) {
		        customer.setCustomerId((Long) customer.getCustomerId());
		    }

		    if (customer.getCustomerId() == null) {
		        return ResponseEntity.badRequest().body("Customer ID is missing!");
		    }

		    Customer updated = customerService.updateCustomer(customer);
		    if (updated == null) {
		        return ResponseEntity.status(404).body("Customer not found");
		    }
		    return ResponseEntity.ok(updated);
		}


	    // 3) CHANGE PASSWORD
		@CrossOrigin
		// allow your frontend
		@PutMapping("/customer/change-password")
		public ResponseEntity<?> changePassword(@RequestBody PasswordResetRequest req) {

		    System.out.println("DEBUG customerId = " + req.getCustomerId());
		    System.out.println("DEBUG oldPassword = " + req.getOldPassword());

		    if (req.getCustomerId() == null) {
		        return ResponseEntity.badRequest().body("Customer ID missing");
		    }

		    boolean success = customerService.changePassword(
		            req.getCustomerId(),
		            req.getOldPassword(),
		            req.getNewPassword()
		    );

		    if (!success) {
		        return ResponseEntity.status(400).body("Incorrect current password");
		    }

		    return ResponseEntity.ok("Password updated successfully");
		}


		
	




		@CrossOrigin
       @GetMapping(value="/GetCustomer")
       public List<Customer> getAllCustomer() {
    	   System.out.println("User Name From Prop"+CustomerNameFromProp);
    	   System.out.println("Get All Users Called");
    	   return customerService.getAllCustomer();
    	   }	
		
		
       @PutMapping("/updateCustomer/{customerId}")
       public String updateCustomer(@PathVariable Long customerId,@RequestBody CustomerRegistrationRequest registerCustomer) {
    	   return customerService.updateCustomer(customerId,registerCustomer);
    	   
       }
       @DeleteMapping("/deleteCustomer/{customerId}")
    	   public String deleteCustomer(@PathVariable Long customerId,@PathVariable String name,@RequestParam String email,@RequestParam String Password) {
    	   System.out.println("In Delet API "+customerId+" "+name+" "+email+" "+Password+"");
    	   return customerService.deleteCustomer(customerId);
       }
       @PostMapping("/createOrder/{customerId}")
       public String createOrder(@PathVariable Long customerId) {
    	   System.out.println("create order fore User"+customerId);
    	   return customerService.createOrder(customerId);
       }
       
       @GetMapping("/getOrdersById/{customerId}")
       public List<Orders> getOrdersById(@PathVariable Long customerId){
    	   System.out.println("Get Orders By ID");
    	   return customerService.getOrdersById(customerId);
       }
     
       
       @GetMapping("/customer/requests/{customerId}")
       public List<MechanicRequest> getRequestsForCustomer(@PathVariable Long customerId) {
           return mechanicRequestService.getRequestsForCustomer(customerId);
       }
    
    
       @CrossOrigin
       @GetMapping("/mechanics/nearby")
       public ResponseEntity<List<Mechanic>> getNearbyMechanics(
               @RequestParam String serviceLocation,
               @RequestParam String serviceType
       ) {
           List<Mechanic> mechanics =
                   mechanicService.findNearbyMechanics(serviceLocation, serviceType);

           if (mechanics.isEmpty()) {
               return ResponseEntity.noContent().build();
           }

           return ResponseEntity.ok(mechanics);
       }
       
       
       @CrossOrigin
       @PostMapping("/customers/forgot-password/find-user")
       public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {

           String email = request.get("email");

           if (email == null || email.trim().isEmpty()) {
               return ResponseEntity.badRequest().body("Email is required");
           }

           email = email.trim().toLowerCase();

           System.out.println("Searching email: [" + email + "]");

           Customer customer = customerRepository.findByEmail(email); // ✅ FIXED

           if (customer == null) {
               return ResponseEntity.status(404).body("Email not registered");
           }

           String otp = String.valueOf(new Random().nextInt(900000) + 100000);

           customer.setOtp(otp);
           customer.setOtpExpiry(LocalDateTime.now().plusMinutes(5));

           customerRepository.save(customer);

           return ResponseEntity.ok("OTP sent successfully");
       }
       
       
       @CrossOrigin
       @PostMapping("/customers/forgot-password/verify-otp")
       public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> request) {

           String email = request.get("email");
           String otp = request.get("otp");

           if (email == null || email.trim().isEmpty()
                   || otp == null || otp.trim().isEmpty()) {
               return ResponseEntity.badRequest().body("Email and OTP are required");
           }

           email = email.trim().toLowerCase();
           otp = otp.trim();

           Customer customer = customerRepository.findByEmail(email);

           if (customer == null) {
               return ResponseEntity.status(404).body("Email not registered");
           }

           // 🔐 Check OTP
           if (!otp.equals(customer.getOtp())) {
               return ResponseEntity.status(400).body("Invalid OTP");
           }

           // ⏰ Check expiry
           if (customer.getOtpExpiry().isBefore(LocalDateTime.now())) {
               return ResponseEntity.status(400).body("OTP expired");
           }

           return ResponseEntity.ok("OTP verified successfully");
       }
       





//4 RESET PASSWORD

@CrossOrigin
@PostMapping("/customers/forgot-password/reset-password")
public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {

    String email = request.get("email");
    String newPassword = request.get("newPassword");

    // ✅ Validation
    if (email == null || email.trim().isEmpty()
            || newPassword == null || newPassword.trim().isEmpty()) {
        return ResponseEntity.badRequest().body("Email and new password are required");
    }

    email = email.trim().toLowerCase();
    newPassword = newPassword.trim();

    Customer customer = customerRepository.findByEmail(email);

    if (customer == null) {
        return ResponseEntity.status(404).body("Email not registered");
    }

    // 🔐 Ensure OTP exists (means it was generated & verified)
    if (customer.getOtp() == null || customer.getOtpExpiry() == null) {
        return ResponseEntity.status(400).body("OTP verification required");
    }

    // ⏰ Check OTP expiry again for safety
    if (customer.getOtpExpiry().isBefore(LocalDateTime.now())) {
        return ResponseEntity.status(400).body("OTP expired");
    }

    // 🔐 Encrypt & update password
    customer.setPassword(passwordEncoder.encode(newPassword));

    // 🧹 Clear OTP after successful reset
    customer.setOtp(null);
    customer.setOtpExpiry(null);

    customerRepository.save(customer);

    return ResponseEntity.ok("Password reset successful");
}

//send service request api
@CrossOrigin
@PostMapping(value="/sendRequest" ,consumes = MediaType.APPLICATION_JSON_VALUE)
public String sendRequest(@RequestBody MechanicRequestCreateDto dto) {
    return mechanicRequestService.sendRequest(dto);
}


}
