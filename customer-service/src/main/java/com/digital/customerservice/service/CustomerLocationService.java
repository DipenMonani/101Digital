package com.digital.customerservice.service;

import com.digital.common.dto.request.customer.CustomerLocationDTO;
import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.merchant.LocationDTO;
import com.digital.common.enums.ErrorCode;
import com.digital.common.exception.CustomException;
import com.digital.customerservice.client.MerchantClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerLocationService {

    @Autowired
    private MerchantClient merchantClient;

    public PageListDTO<LocationDTO> getNearestMerchantLocationBySearchCriteria(CustomerLocationDTO customerLocationDTO, PageRequest pageRequest) {
        try{
            return merchantClient.getNearestMerchantLocationBySearchCriteria(customerLocationDTO, pageRequest.getPageNumber(), pageRequest.getPageSize());
        }catch (Exception e){
            throw new CustomException(String.format("Error while getting the nearest merchant-location, Error: %s", e.getMessage()), ErrorCode.MERCHANT_FEIGN_CLIENT_ERROR, HttpStatus.NOT_FOUND);
        }
    }

    public LocationDTO getMenuByNearestMerchantAndLocation(Long merchantId, Long locationId) {
        try{
            return merchantClient.getMenuByNearestMerchantAndLocation(merchantId, locationId);
        }catch (Exception e){
            throw new CustomException(String.format("Error while getting the nearest merchant-location-menu details, Error: %s", e.getMessage()), ErrorCode.MERCHANT_FEIGN_CLIENT_ERROR, HttpStatus.NOT_FOUND);
        }
    }
}
