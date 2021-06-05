package com.PhoneNumbers.springboot.repository;

import com.PhoneNumbers.springboot.model.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repository to handle the db IOs
@Repository

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(name = "getAllCustomersWithoutInfo",
            value = "select c from customer c where c.id not in( select ci from customer_info ci)")
     List<Customer> getUnprocessedCustomers();

    @Query(name = "getCustomersByCountry",
            value = "select c from  customer c join customer_info ci on c.id = ci.customer.id where ci.country.name = :country")
    List<Customer> findCustomerByCountry(String country);

    Customer findCustomerByPhoneNumber(String phoneNumber);
}
