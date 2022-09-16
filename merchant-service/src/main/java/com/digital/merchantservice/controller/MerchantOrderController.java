package com.digital.merchantservice.controller;

import com.digital.common.dto.request.customer.CustomerOrderRequestDTO;
import com.digital.common.dto.response.merchant.CustomerOrderDTO;
import com.digital.merchantservice.service.MerchantOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/merchant")
public class MerchantOrderController {

    @Autowired
    private MerchantOrderService merchantOrderService;

    @PostMapping("/customer-order")
    public ResponseEntity<CustomerOrderDTO> placeCustomerOrder(@RequestBody CustomerOrderRequestDTO customerOrder){
        return new ResponseEntity(merchantOrderService.placeCustomerOrder(customerOrder), HttpStatus.OK);
    }

    @GetMapping("/customer-order/{customer-id}/order/{order-id}")
    public ResponseEntity<CustomerOrderDTO> getCustomerOrder(@PathVariable("customer-id") Long customerId, @PathVariable("order-id") Long orderId){
        return new ResponseEntity(merchantOrderService.getCustomerOrder(customerId, orderId), HttpStatus.OK);
    }
}
