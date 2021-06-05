package com.PhoneNumbers.springboot.repository;

import com.PhoneNumbers.springboot.model.Country.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

    Country findByCode(Short code);
}
