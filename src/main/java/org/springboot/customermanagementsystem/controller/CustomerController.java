package org.springboot.customermanagementsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springboot.customermanagementsystem.entity.Customer;
import org.springboot.customermanagementsystem.entity.ResponseStructure;
import org.springboot.customermanagementsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping(path = "/register")
	public ResponseEntity<ResponseStructure<Customer>> registerCustomer(@RequestBody Customer customer){
		return customerService.registerCustomer(customer);
	}
	
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@PathVariable Integer id){
		return customerService.getCustomerById(id);
	}
	
	@PutMapping(path = "update/{id}")// customer/update/1
	ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer, @PathVariable Integer id){
		return customerService.updateCustomer(customer, id);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer(){
		return customerService.getAllCustomer();
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteCustomerById(@PathVariable Integer id){
		return customerService.deleteCustomerById(id);
	}
	
	@PostMapping(path = "/searchByPhone")
	ResponseEntity<ResponseStructure<Optional<Customer>>> searchByPhone(@RequestParam long phone){
		return customerService.searchByPhone(phone);
	}
	
	
}






















