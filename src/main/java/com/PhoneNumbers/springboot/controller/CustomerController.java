package com.PhoneNumbers.springboot.controller;

import com.PhoneNumbers.springboot.dto.CustomerDto;
import com.PhoneNumbers.springboot.model.Customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.PhoneNumbers.springboot.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<CustomerDto> getCustomers() {
        return customerService.findAll().stream().map(customer -> customerService.mapCustomerToDto(customer)).collect(Collectors.toList());
    }

    @RequestMapping(value = "country/{country}", method = RequestMethod.GET)
    public List<CustomerDto> getCountryCustomers(@PathVariable String country) {
        return customerService.getCountryCustomers(country).stream().map(customer -> customerService.mapCustomerToDto(customer)).collect(Collectors.toList());
    }

    @RequestMapping(value = "valid/{validity}", method = RequestMethod.GET)
    public List<CustomerDto> getValidCustomers(@PathVariable Boolean validity) {
        return customerService.getValidOrInvalidCustomers(validity).stream().map(customer -> customerService.mapCustomerToDto(customer)).collect(Collectors.toList());
    }

    @RequestMapping(value = "phoneNumber/{phoneNumber}", method = RequestMethod.GET)
    public CustomerDto getCustomerByPhoneNumber(@PathVariable String phoneNumber) {
        return customerService.mapCustomerToDto(customerService.getCustomerByPhoneNumber(phoneNumber));
    }

}
	
