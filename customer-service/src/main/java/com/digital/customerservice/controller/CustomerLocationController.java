package com.digital.customerservice.controller;

import com.digital.common.dto.request.customer.CustomerLocationDTO;
import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.merchant.LocationDTO;
import com.digital.customerservice.service.CustomerLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/api/v1/customer")
public class CustomerLocationController {

    @Autowired
    private CustomerLocationService customerLocationService;

    @PostMapping("/nearest-merchant/locations")
    public ResponseEntity<PageListDTO<LocationDTO>> getNearestMerchantLocationBySearchCriteria(@RequestBody CustomerLocationDTO customerLocationDTO,
                                                                                             @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                             @RequestParam(value = "size", defaultValue = "20") int size ){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"id"));
        return new ResponseEntity(customerLocationService.getNearestMerchantLocationBySearchCriteria(customerLocationDTO, pageRequest), HttpStatus.OK);
    }

    @GetMapping("/nearest-merchant/{merchant-id}/location/{location-id}/menus")
    public ResponseEntity<LocationDTO> getMenuByNearestMerchantAndLocation(@PathVariable("merchant-id") Long merchantId, @PathVariable("location-id") Long locationId){
        return new ResponseEntity(customerLocationService.getMenuByNearestMerchantAndLocation(merchantId, locationId), HttpStatus.OK);
    }
}
