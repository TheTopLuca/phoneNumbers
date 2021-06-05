package com.PhoneNumbers.springboot.repository;

import com.PhoneNumbers.springboot.model.Customer.Customer;
import com.PhoneNumbers.springboot.model.Customer.Info.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerInfoRepository extends JpaRepository<CustomerInfo,Long> {

    //"select customer_info.customer from customer_info where customer_info.valid = :validity"
    @Query(name = "getCustomersByValidity" , value = "select c from customer c join customer_info ci on c.id=ci.id where ci.valid = :validity")
    List<Customer> getValidCustomers(boolean validity);
}
