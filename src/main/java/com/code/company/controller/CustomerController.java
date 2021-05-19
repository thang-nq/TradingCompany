package com.code.company.controller;

import com.code.company.entity.Customer;
import com.code.company.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //CRUD api

    @GetMapping
    public Page<Customer> getAll(@RequestParam Optional<Integer> page) {
        return customerService.getAll(page);
    }

    @GetMapping(path = "{id}")
    public Customer getById(@PathVariable("id") Long id) {
        return customerService.getById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") Long id) {
        customerService.deleteById(id);
    }

    @PostMapping
    public void add(@RequestBody Customer customer) {
        customerService.add(customer);
    }

    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") Long id,
                       @RequestBody Customer newCustomer) throws Exception {
        customerService.update(id,newCustomer);
    }

    //Search api

    @GetMapping(path = "find")
    public Page<Customer> find(@RequestParam Optional<Integer> page,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String address,
                               @RequestParam(required = false) String phone) {
        return customerService.find(page,name,address,phone);
    }

}
