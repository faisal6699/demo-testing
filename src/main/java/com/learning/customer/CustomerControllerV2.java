package com.learning.customer;

import com.learning.customer.exception.ApiRequestException;
import com.learning.customer.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v2/customers")
public class CustomerControllerV2 {

        private final CustomerService customerService;

        record NewCustomerRequest(String name,
                                  String email,
                                  Integer age){ }
        public CustomerControllerV2(CustomerService customerService) {
            this.customerService = customerService;
        }

        @GetMapping
        public List<Customer> getCustomers() {
            return customerService.getCustomer();
        }

        @GetMapping("{customerId}")
        public Customer getCustomer(@PathVariable("customerId") Integer id) {
            return customerService.getCustomer()
                    .stream()
                    .filter(customer -> customer.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("user not found"));
        }

        @PostMapping
        public String addCustomer(@Valid @RequestBody Customer customer) {
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
