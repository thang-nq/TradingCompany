package com.code.company.service;

import com.code.company.entity.Customer;
import com.code.company.JPA.CustomerRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }



    public Page<Customer> getAll(Optional<Integer> page) {
        return repository.findAll(PageRequest.of(page.orElse(0),2));
    }

    public Customer getById(Long id) {
        return repository.findCustomerById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void add(Customer customer) {
        repository.save(customer);
    }

    public void update(Long id, Customer newCustomer) throws Exception {
        repository.findById(id).map(customer -> {
            customer.setName(newCustomer.getName());
            customer.setAddress(newCustomer.getAddress());
            customer.setEmail(newCustomer.getEmail());
            customer.setPhone(newCustomer.getPhone());
            customer.setFax(newCustomer.getFax());
            customer.setContactPerson(newCustomer.getContactPerson());
            return repository.save(customer);
        }).orElseThrow(() -> new Exception("Customer not found"));
    }

    public Page<Customer> find(Optional<Integer> page, String name, String address, String phone) {
        return null;
    }
}
