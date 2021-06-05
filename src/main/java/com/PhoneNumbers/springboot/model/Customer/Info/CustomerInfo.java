package com.PhoneNumbers.springboot.model.Customer.Info;

import com.PhoneNumbers.springboot.model.Country.Country;
import com.PhoneNumbers.springboot.model.Customer.Customer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "customer_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerInfo implements Serializable {

    @Id
    private Long id ;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "country")
    private Country country;

    @Column(name = "valid")
    private Boolean valid;


}
