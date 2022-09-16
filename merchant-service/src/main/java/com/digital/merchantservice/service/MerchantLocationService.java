package com.digital.merchantservice.service;

import com.digital.merchantservice.data.entity.Location;
import com.digital.merchantservice.data.repository.LocationRepository;
import com.digital.common.dto.request.customer.CustomerLocationDTO;
import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.merchant.LocationDTO;
import com.digital.common.enums.ErrorCode;
import com.digital.common.enums.MerchantStatus;
import com.digital.common.exception.CustomException;
import com.digital.common.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

@Service
@Slf4j
public class MerchantLocationService {

    @Autowired
    private LocationRepository locationRepository;

    public PageListDTO<LocationDTO> getMerchantLocationMenusBySearchCriteria(CustomerLocationDTO customerLocationDTO, PageRequest pageable){
        Page<Location> locationList = locationRepository.getMerchantLocationMenusBySearchCriteria(customerLocationDTO.getLatitude(), customerLocationDTO.getLongitude(), customerLocationDTO.getRadius(), customerLocationDTO.getSearchString(), pageable);

        return PageListDTO.<LocationDTO>builder()
                .items(CollectionUtils.isEmpty(locationList.getContent()) == false ? Location.build(locationList.getContent()) : new ArrayList<>())
                .totalElements(locationList.getTotalElements())
                .pageNumber(locationList.getNumber())
                .pageSize(locationList.getSize())
                .totalPages(locationList.getTotalPages())
                .build();
    }

    public LocationDTO getMerchantLocationMenusById(Long merchantId, Long locationId) {
        if(merchantId == null || locationId == null){
            throw new CustomException("Merchant-id and location-id is required.", ErrorCode.VALIDATION_ERROR, HttpStatus.BAD_REQUEST);
        }
        Location location = locationRepository.findByIdAndMerchantIdAndAndMerchantStatus(locationId, merchantId, MerchantStatus.ACTIVE);

        if(location == null){
            throw new ResourceNotFoundException(Location.class,
                    String.format("Location not found with given location: %s and merchant: %s", locationId, merchantId),
                    ErrorCode.LOCATION_NOT_FOUND);
        }

        return Location.build(location, Boolean.TRUE);
    }
}
