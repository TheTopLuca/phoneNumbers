package com.PhoneNumbers.springboot.service;


import java.util.List;

import com.PhoneNumbers.springboot.dto.CustomerDto;
import com.PhoneNumbers.springboot.exception.InvalidInputException;
import com.PhoneNumbers.springboot.model.Country.Country;
import com.PhoneNumbers.springboot.model.Customer.Customer;
import com.PhoneNumbers.springboot.model.Customer.Info.CustomerInfo;
import com.PhoneNumbers.springboot.repository.CountryRepository;
import com.PhoneNumbers.springboot.repository.CustomerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PhoneNumbers.springboot.repository.CustomerRepository;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Override
    //get all customers
    public List<Customer> findAll() {
        processUnProcessedCustomers();
        return customerRepository.findAll();
    }

    public void insertNewCustomer(Customer customer) throws InvalidInputException {
        if (customer.getPhoneNumber() != null && customer.getName() != null && customer.getId() != null) {
            customerRepository.save(customer);
        } else {
            throw new InvalidInputException("customer basic info has missing fields");
        }
    }

    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findCustomerByPhoneNumber(phoneNumber);
    }

    //Get customers based on state
    @Override
    public List<Customer> getValidOrInvalidCustomers(Boolean valid) {
        return customerInfoRepository.getValidCustomers(valid);
    }

    //get Customers based on Country
    @Override
    public List<Customer> getCountryCustomers(String country) {
        // TODO Auto-generated method stub
        return customerRepository.findCustomerByCountry(country);
    }

    public boolean checkCodeValidity(String code) {
        return !code.contains("(") && !code.contains(")") && !code.contains(" ") && code.matches("\\d+");
    }

    public Country getCountryByPhoneNumber(String phoneNo) {
        String code = phoneNo.substring(phoneNo.indexOf('(') + 1, phoneNo.lastIndexOf(')'));
        if (checkCodeValidity(code)) {
            return getCountryByCode(Short.parseShort(code));
        }
        return null;
    }


    public Country getCountryByCode(short code) {
        return countryRepository.findByCode(code);
    }

    public void processUnProcessedCustomers() {
        List<Customer> unProcessedCustomers = customerRepository.getUnprocessedCustomers();
        unProcessedCustomers.forEach(this::insertCustomerInfo);
    }

    private void insertCustomerInfo(Customer customer) {
        Country customerCountry = getCountryByPhoneNumber(customer.getPhoneNumber());
        CustomerInfo info = new CustomerInfo();
        info.setId(customer.getId());
        info.setCustomer(customer);
        info.setCountry(customerCountry);
        if (customerCountry == null) {
            info.setValid(false);
        } else {
            info.setValid(customer.getPhoneNumber().matches(customerCountry.getRegexp()));
        }
        customerInfoRepository.save(info);
    }

    public CustomerDto mapCustomerToDto(Customer customer) {
        if (customer.getCustomerInfo() != null && customer.getCustomerInfo().getCountry() != null) {
            return new CustomerDto(customer.getId()
                    , customer.getName()
                    , customer.getPhoneNumber()
                    , customer.getCustomerInfo().getCountry().getName()
                    , customer.getCustomerInfo().getValid()
                    , customer.getCustomerInfo().getCountry().getCode());
        } else {
            return new CustomerDto(customer.getId()
                    , customer.getName()
                    , customer.getPhoneNumber(), null, null, null);
        }
    }

}



