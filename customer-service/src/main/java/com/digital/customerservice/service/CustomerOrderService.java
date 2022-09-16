package com.digital.customerservice.service;

import com.digital.common.dto.request.customer.CustomerOrderRequestDTO;
import com.digital.common.dto.response.merchant.CustomerOrderDTO;
import com.digital.common.enums.ErrorCode;
import com.digital.common.exception.CustomException;
import com.digital.customerservice.client.MerchantClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerOrderService {

    @Autowired
    public MerchantClient merchantClient;

    public CustomerOrderDTO placeCustomerOrder(CustomerOrderRequestDTO customerOrder) {
        try{
            CustomerOrderDTO customerOrderDTO = merchantClient.placeCustomerOrder(customerOrder);
            log.info("Customer order placed successfully, customer : {}, order : {}, order-status : {}", customerOrderDTO.getCustomerId(), customerOrderDTO.getId(), customerOrderDTO.getOrderStatus());
            return customerOrderDTO;
        }catch (Exception e){
            log.error("Error while placing the customer-order, Error : {}",e.getMessage());
            throw new CustomException(String.format("Error while placing the customer-order, Error : %s",e.getMessage()), ErrorCode.MERCHANT_FEIGN_CLIENT_ERROR, HttpStatus.NO_CONTENT);
        }
    }

    public CustomerOrderDTO getCustomerOrder(Long customerId, Long orderId) {
        try{
            return merchantClient.getCustomerOrder(customerId, orderId);
        }catch (Exception e){
            log.error("Error while getting the customer-order : customer : {}, order : {}, Error : {}", customerId, orderId, e.getMessage());
            throw new CustomException(String.format("Error while getting the customer-order : customer : %s, order : %s, Error : %s", customerId, orderId, e.getMessage()), ErrorCode.MERCHANT_FEIGN_CLIENT_ERROR, HttpStatus.NOT_FOUND);
        }
    }
}
