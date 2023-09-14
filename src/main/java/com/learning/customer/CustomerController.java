package com.learning.customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    record NewCustomerRequest(String name,
                              String email,
                              Integer age){ }
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomer();
    }

    @PostMapping
    public String addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return "OK";
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerService.deleteCustomer(id);
    }

    @PutMapping("{customerId}")
    public String editCustomer(@PathVariable("customerId") Integer id, @RequestBody Customer request) {
        customerService.editCustomer(id, request);
        return "OK";
    }
}
