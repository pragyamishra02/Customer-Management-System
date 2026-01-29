package org.springboot.customermanagementsystem.repository;

import java.util.Optional;

import org.springboot.customermanagementsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	@Query("SELECT c FROM Customer c WHERE c.phone=?1")
	Optional<Customer> searchByPhone(long phone);

// Optional<Customer> searchByPhoneAndEmail(long phone, String email);
	
}
