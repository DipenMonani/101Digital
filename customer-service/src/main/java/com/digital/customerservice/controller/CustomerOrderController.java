package com.digital.customerservice.controller;

import com.digital.common.dto.request.customer.CustomerOrderRequestDTO;
import com.digital.common.dto.response.merchant.CustomerOrderDTO;
import com.digital.customerservice.service.CustomerOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/customer")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping("/{customer-id}/order")
    public ResponseEntity<CustomerOrderDTO> placeCustomerOrder(@RequestBody CustomerOrderRequestDTO customerOrder){
        return new ResponseEntity(customerOrderService.placeCustomerOrder(customerOrder), HttpStatus.OK);
    }

    @GetMapping("/{customer-id}/order/{order-id}")
    public ResponseEntity<CustomerOrderDTO> getCustomerOrder(@PathVariable("customer-id") Long customerId, @PathVariable("order-id") Long orderId){
        return new ResponseEntity(customerOrderService.getCustomerOrder(customerId, orderId), HttpStatus.OK);
    }
}
