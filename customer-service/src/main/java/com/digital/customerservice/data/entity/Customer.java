package com.digital.customerservice.data.entity;

import com.digital.common.data.entity.Audit;
import com.digital.common.dto.response.customer.CustomerDTO;
import com.digital.common.enums.AddressType;
import com.digital.common.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name ="customer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Audit {
    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String postcode;

    private String country;

    private String password;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status = CustomerStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    public static CustomerDTO build(Customer customer){
        return CustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .mobileNumber(customer.getMobileNumber())
                .addressLine1(customer.getAddressLine1())
                .addressLine2(customer.getAddressLine2())
                .city(customer.getCity())
                .postcode(customer.getPostcode())
                .country(customer.getCountry())
                .status(customer.getStatus())
                .addressType(customer.getAddressType())
                .build();
    }
}