package com.learning.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

     public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    public Customer editCustomer(Integer id, Customer request) {
        Customer customer = customerRepository.findById(id).orElse(null);

        if(customer == null) {
            return null;
        }
        customer.setAge(request.getAge());
        customer.setEmail(request.getEmail());
        customer.setName(request.getName());

        return customerRepository.save(customer);
    }
}
