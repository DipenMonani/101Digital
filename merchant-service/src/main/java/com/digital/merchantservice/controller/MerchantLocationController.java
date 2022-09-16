package com.digital.merchantservice.controller;

import com.digital.common.dto.request.customer.CustomerLocationDTO;
import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.merchant.LocationDTO;
import com.digital.merchantservice.service.MerchantLocationService;
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
@RequestMapping("/api/v1/merchant")
public class MerchantLocationController {

    @Autowired
    private MerchantLocationService merchantLocationService;

    @PostMapping("/locations")
    public ResponseEntity<PageListDTO<LocationDTO>> getMerchantLocationMenusBySearchCriteria(@RequestBody CustomerLocationDTO customerLocationDTO,
                                                                                             @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                             @RequestParam(value = "size", defaultValue = "20") int size ){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"id"));
        return new ResponseEntity(merchantLocationService.getMerchantLocationMenusBySearchCriteria(customerLocationDTO, pageRequest), HttpStatus.OK);
    }

    @GetMapping("/{merchant-id}/location/{location-id}/menus")
    public ResponseEntity<LocationDTO> getMerchantLocationMenusById(@PathVariable("merchant-id") Long merchantId, @PathVariable("location-id") Long locationId){
        return new ResponseEntity(merchantLocationService.getMerchantLocationMenusById(merchantId, locationId), HttpStatus.OK);
    }

}
