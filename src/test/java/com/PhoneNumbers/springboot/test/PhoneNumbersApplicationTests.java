package com.PhoneNumbers.springboot.test;

import java.util.List;

import java.util.stream.Collectors;

import com.PhoneNumbers.springboot.dto.CustomerDto;
import com.PhoneNumbers.springboot.model.Customer.Customer;
import com.PhoneNumbers.springboot.model.Customer.Info.CustomerInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.PhoneNumbers.springboot.service.CustomerService;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class PhoneNumbersApplicationTests {


	@Autowired
	CustomerService customerService;

	@Test
    void getAll() {
	    List<Customer> customers = customerService.findAll();
        assertEquals(3,customers.size());
    }

    @Test
    void getAllInCountry(){
	    customerService.processUnProcessedCustomers();
	    String countryName = "Morocco";
        List<Customer> customers = customerService.getCountryCustomers("Morocco");
        assertEquals(2,customers.size());
        customers.forEach(customer -> assertEquals(countryName,customer.getCustomerInfo().getCountry().getName()));
    }

    @Test
    void getCountryByPhoneNumber_InvalidNumber(){
	    String phoneNumber = "(215) 9292929292";
	    assertNull(customerService.getCountryByPhoneNumber(phoneNumber));
    }

    @Test
    void getCountryByPhoneNumber_Morocco(){
	    String countryName = "Morocco";
        String phoneNumber = "+(212) 9292929292";
	    assertEquals(countryName , customerService.getCountryByPhoneNumber(phoneNumber).getName());
    }

    @Test
    void getCountryByPhoneNumber_Ethiopia(){
        String countryName = "Ethiopia";
        String phoneNumber = "+(251) 9292929292";
        assertEquals(countryName , customerService.getCountryByPhoneNumber(phoneNumber).getName());
    }

    @Test
    void getCountryByPhoneNumber_Cameroon(){
        String countryName = "Cameroon";
        String phoneNumber = "+(237) 9292929292";
        assertEquals(countryName , customerService.getCountryByPhoneNumber(phoneNumber).getName());
    }

    @Test
    void getCountryByPhoneNumber_Mozambique(){
        String countryName = "Mozambique";
        String phoneNumber = "+(258) 9292929292";
        assertEquals(countryName , customerService.getCountryByPhoneNumber(phoneNumber).getName());
    }

    @Test
    void getCountryByPhoneNumber_Uganda(){
        String countryName = "Uganda";
        String phoneNumber = "+(256) 9292929292";
        assertEquals(countryName , customerService.getCountryByPhoneNumber(phoneNumber).getName());
    }

    @Test
    void getValidCustomers(){
	    customerService.processUnProcessedCustomers();
	    List<Customer> validCustomers = customerService.getValidOrInvalidCustomers(true);
	    assertEquals(1,validCustomers.size());
    }

    @Test
    void getInValidCustomers(){
        customerService.processUnProcessedCustomers();
        List<Customer> inValidCustomers = customerService.getValidOrInvalidCustomers(false);
        assertEquals(2,inValidCustomers.size());
    }

    @Test
    void getCustomersWithoutCountries(){
        customerService.processUnProcessedCustomers();
        List<Customer> customersWithoutBorders = customerService.getValidOrInvalidCustomers(false)
                .stream().filter(customer -> customer.getCustomerInfo().getCountry()==null).
                        collect(Collectors.toList());
        assertEquals(1,customersWithoutBorders.size());
    }

    @Test
    void checkCodeValiditySuccess(){
	    String codeValid = "212";
	    assertTrue(customerService.checkCodeValidity(codeValid));
    }
    @Test
    void checkCodeValidityFailure1(){
        String codeValid = "(212";
        assertFalse(customerService.checkCodeValidity(codeValid));
    }
    @Test
    void checkCodeValidityFailure2(){
        String codeValid = "212)";
        assertFalse(customerService.checkCodeValidity(codeValid));
    }
    @Test
    void checkCodeValidityFailure3(){
        String codeValid = "(212)";
        assertFalse(customerService.checkCodeValidity(codeValid));
    }

    @Test
    void checkCodeValidityFailure4(){
        String codeValid = "A12";
        assertFalse(customerService.checkCodeValidity(codeValid));
    }

    @Test
    void getCustomerByPhoneNumberSuccess(){
	    String phone = "(212) 6007989253";
	    assertEquals(phone,customerService.getCustomerByPhoneNumber(phone).getPhoneNumber());
    }

    @Test
    void getCustomerByPhoneNumberNoCustomerFound(){
        String phone = "(212) 14007989253";
        assertNull(customerService.getCustomerByPhoneNumber(phone));
    }

    @Test
    void mapCustomerToDtoFullCustomer(){
	    Customer c = new Customer(5L,"Ali Hany","(212) 1112356647",null);
        CustomerInfo customerInfo = new CustomerInfo(5L,c,customerService.getCountryByCode((short)212),false);
        c.setCustomerInfo(customerInfo);
        CustomerDto dto  = customerService.mapCustomerToDto(c);
        assertNotNull(dto.getCountryName());
    }

    @Test
    void mapCustomerToDtoNoCustomerInfo(){
        Customer c = new Customer(5L,"Ali Hany","(212) 1112356647",null);
        CustomerDto dto  = customerService.mapCustomerToDto(c);
        assertNull(dto.getCountryName());
        assertNull(dto.getValid());
    }

    @Test
    void mapCustomerToDtoWithCustomerInfoButNoCountry(){
        Customer c = new Customer(5L,"Ali Hany","(212) 1112356647",null);
        CustomerInfo customerInfo = new CustomerInfo(5L,c,null,false);
        CustomerDto dto  = customerService.mapCustomerToDto(c);
        assertNull(dto.getCountryName());
    }
}
