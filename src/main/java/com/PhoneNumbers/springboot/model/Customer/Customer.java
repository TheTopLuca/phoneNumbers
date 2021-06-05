package com.PhoneNumbers.springboot.model.Customer;

import com.PhoneNumbers.springboot.model.Customer.Info.CustomerInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, optional = true, mappedBy = "customer")
    private CustomerInfo customerInfo;

}
