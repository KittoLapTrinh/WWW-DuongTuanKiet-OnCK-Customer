package com.example.wwwduongtuankietonck2cus.backend.services;

import com.example.wwwduongtuankietonck2cus.backend.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    List<Customer> getCustomersByYearDob();
    Customer getCustomerById(Long id);
    Customer addCustomer(Customer customer);
    boolean deleteCustomer(Long id);
    boolean updateCustomer(Customer customer);
}
