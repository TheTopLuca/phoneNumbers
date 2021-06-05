package com.PhoneNumbers.springboot.service;
//Interface for the Customer Service
import com.PhoneNumbers.springboot.model.Customer.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICustomerService {

	public List<Customer> findAll();
	public List<Customer> getValidOrInvalidCustomers(Boolean valid);
	public List<Customer> getCountryCustomers(String country);
}
