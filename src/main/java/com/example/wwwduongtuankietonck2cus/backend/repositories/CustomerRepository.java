package com.example.wwwduongtuankietonck2cus.backend.repositories;

import com.example.wwwduongtuankietonck2cus.backend.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE YEAR(c.dob) = 2000")
    List<Customer> findCustomerByYearDob();
}
