package com.example.wwwduongtuankietonck2cus.backend.services.impl;

import com.example.wwwduongtuankietonck2cus.backend.models.Customer;
import com.example.wwwduongtuankietonck2cus.backend.repositories.CustomerRepository;
import com.example.wwwduongtuankietonck2cus.backend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> getCustomersByYearDob() {
        return customerRepository.findCustomerByYearDob();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean deleteCustomer(Long id) {
        if(!customerRepository.existsById(id)){
            return false;
        }
        customerRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        if(!customerRepository.existsById(customer.getId())){
            customerRepository.save(customer);
            return true;
        }
        customerRepository.save(customer);
        return false;
    }
}
