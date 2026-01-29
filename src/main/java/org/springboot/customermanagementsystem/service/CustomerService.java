package org.springboot.customermanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springboot.customermanagementsystem.entity.Customer;
import org.springboot.customermanagementsystem.entity.ResponseStructure;
import org.springboot.customermanagementsystem.exception.CustomerNotFunndException;
import org.springboot.customermanagementsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

//	registereCustomer API
	public ResponseEntity<ResponseStructure<Customer>> registerCustomer(Customer customer){
		
		ResponseStructure<Customer> structure = new ResponseStructure<>();
		structure.setData(customerRepository.save(customer));
		
		structure.setMessage("Customer Registered Successfully");
		structure.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.CREATED);
	}
	
//	getCustomerById API
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(Integer id){
		
		ResponseStructure<Customer> structure = new ResponseStructure<>();
		
		Optional<Customer> recCustomer = customerRepository.findById(id);
		
		if(recCustomer.isPresent()) {
			structure.setMessage("Customer Found");
			structure.setData(recCustomer.get());
			structure.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<Customer>> (structure, HttpStatus.OK);
		}
		throw new CustomerNotFunndException(id + " Cutomer Not found");
	}
	
	
//	updateCustomer API
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer, Integer id){
		ResponseStructure<Customer> structure = new ResponseStructure<>();
		
		Optional<Customer> recCustomer = customerRepository.findById(id);
		
		if(recCustomer.isPresent()) {
			Customer existCustomer = recCustomer.get();
			
			existCustomer.setName(customer.getName());
			existCustomer.setEmail(customer.getEmail());
			existCustomer.setPhone(customer.getPhone());
			existCustomer.setAddress(customer.getAddress());
			
			Customer updateCustomer = customerRepository.save(existCustomer);
			
			structure.setData(updateCustomer);
			structure.setMessage("Customer Updated Successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);
			
		}
		throw new CustomerNotFunndException("Cusomer Not Foun");
	}
	
//	getAllCustomer API
	
	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer(){
		ResponseStructure<List<Customer>> structure = new ResponseStructure<>();
		
		List<Customer> recCustomers = customerRepository.findAll();
		
		if(recCustomers.size()>0) {
			structure.setMessage("List Of Customer Found");
			structure.setData(recCustomers);
			structure.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<List<Customer>>>(structure, HttpStatus.OK);
		}
		throw new CustomerNotFunndException("List Of Customers not Found");
	}
	
//	deleteCustomerById API
	public ResponseEntity<ResponseStructure<String>> deleteCustomerById(Integer id){
		
		Customer recCustomer = customerRepository.findById(id).orElseThrow(()-> new CustomerNotFunndException("Cutomer Not Found"));
		customerRepository.delete(recCustomer);
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("Customer Delete Sucessfully");
		structure.setMessage("success");
		structure.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<String>> (structure, HttpStatus.OK);
	}
	
//	searchByPhone API
	public ResponseEntity<ResponseStructure<Optional<Customer>>> searchByPhone(long phone){
		ResponseStructure<Optional<Customer>> structure = new ResponseStructure<>();
		
		Optional<Customer> recCustomer = customerRepository.searchByPhone(phone);
		if(recCustomer.isPresent()) {
			structure.setMessage("Customer Found");
			structure.setData(recCustomer);
			structure.setStatusCode(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<Optional<Customer>>> (structure, HttpStatus.OK);
		}
		throw new CustomerNotFunndException("Given Number " + phone + " Customer Not Found ");
	}
	
	
}























