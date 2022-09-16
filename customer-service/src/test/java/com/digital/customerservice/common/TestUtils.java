package com.digital.customerservice.common;

import com.digital.common.dto.request.customer.CustomerLocationDTO;
import com.digital.common.dto.request.customer.CustomerOrderRequestDTO;
import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.merchant.CustomerOrderDTO;
import com.digital.common.dto.response.merchant.LocationDTO;
import com.digital.common.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Collections;

public class TestUtils {

    public static CustomerLocationDTO getCustomerLocationDTO(){
        return CustomerLocationDTO.builder()
                .latitude(51.449943)
                .longitude(-0.9757451)
                .radius(5)
                .searchString("")
                .build();
    }

    public static PageListDTO<LocationDTO> getPageListLocationDTOs(){
         PageListDTO<LocationDTO> pageListDTO = PageListDTO.<LocationDTO>builder()
                 .items(Collections.singletonList(LocationDTO.builder()
                                 .id(1L)
                                 .merchantId(1L)
                                 .merchantName("Nescafe Hub")
                                 .addressLine1("city plaza")
                                 .addressLine2("rani garden")
                                 .city("Patan")
                                 .postcode("123456")
                                 .country("INDIA")
                                 .contactNumber("+919822989891")
                                 .build()))
                 .pageNumber(1)
                 .pageSize(1)
                 .totalElements(1)
                 .build();

        return pageListDTO;
    }

    public static CustomerOrderRequestDTO getCustomerOrderRequestDTO(){
        return CustomerOrderRequestDTO.builder()
                .customerId(1L)
                .merchantId(1L)
                .locationId(1L)
                .menuId(2L)
                .pickUpTime(LocalDateTime.parse("2022-09-16T13:08:42"))
                .build();
    }

    public static CustomerOrderDTO getCustomerOrderDTO() {
        return CustomerOrderDTO.builder()
                .id(1L)
                .customerId(1L)
                .merchantId(1L)
                .locationId(1L)
                .menuId(2L)
                .pickUpTime(LocalDateTime.parse("2022-09-16T13:08:42"))
                .orderStatus(OrderStatus.QUEUED)
                .build();
    }

    public static LocationDTO getLocationDTO(){
        return LocationDTO.builder()
                .id(1L)
                .merchantId(1L)
                .merchantName("Nescafe Hub")
                .addressLine1("city plaza")
                .addressLine2("rani garden")
                .city("Patan")
                .postcode("123456")
                .country("INDIA")
                .contactNumber("+919822989891")
                .build();
    }
}
