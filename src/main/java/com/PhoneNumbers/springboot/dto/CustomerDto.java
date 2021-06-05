package com.PhoneNumbers.springboot.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CustomerDto {

    private Long id;

    private String name ;

    private String phoneNumber;

    private String countryName;

    private Boolean valid;

    private Short countryCode;

}
