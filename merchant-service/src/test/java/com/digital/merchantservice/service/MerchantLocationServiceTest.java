package com.digital.merchantservice.service;

import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.merchant.LocationDTO;
import com.digital.common.enums.MerchantStatus;
import com.digital.common.exception.CustomException;
import com.digital.merchantservice.common.TestUtils;
import com.digital.merchantservice.data.entity.Location;
import com.digital.merchantservice.data.repository.LocationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MerchantLocationServiceTest {

    @InjectMocks
    private MerchantLocationService merchantLocationService;

    @Mock
    private LocationRepository locationRepository;


    @Test
    public void getMerchantLocationMenusBySearchCriteriaSuccess(){
        PageListDTO<LocationDTO> pageListDTO = TestUtils.getPageListLocationDTOs();
        Page<Location> pageLocationList = TestUtils.getPageLocationList();
        PageRequest pageRequest = PageRequest.of(1, 1, Sort.by(Sort.Direction.DESC,"id"));
        when(locationRepository.getMerchantLocationMenusBySearchCriteria(51.449943, -0.9757451, 5, "", pageRequest)).thenReturn(pageLocationList);
        PageListDTO<LocationDTO>  result =  merchantLocationService.getMerchantLocationMenusBySearchCriteria(TestUtils.getCustomerLocationDTO(),pageRequest);
        Assertions.assertEquals(pageListDTO.getItems(), result.getItems());
    }

    @Test
    public void getMerchantLocationMenusBySearchCriteriaShouldThrow(){
        PageRequest pageRequest = PageRequest.of(1, 1, Sort.by(Sort.Direction.DESC,"id"));
        when(locationRepository.getMerchantLocationMenusBySearchCriteria(51.449943, -0.9757451, 5, "", pageRequest)).thenThrow(CustomException.class);
        Assertions.assertThrows(CustomException.class,() -> merchantLocationService.getMerchantLocationMenusBySearchCriteria(TestUtils.getCustomerLocationDTO(),pageRequest));
    }

    @Test
    public void getMenuByNearestMerchantAndLocationShouldSuccess(){
        LocationDTO  locationDTO = TestUtils.getLocationDTO();
        Location location = TestUtils.getLocation();
        when(locationRepository.findByIdAndMerchantIdAndAndMerchantStatus(1L, 1L, MerchantStatus.ACTIVE)).thenReturn(location);
        LocationDTO  result =  merchantLocationService.getMerchantLocationMenusById(1L, 1L);
        Assertions.assertEquals(locationDTO.getId(), result.getId());
    }

    @Test
    public void getMenuByNearestMerchantAndLocationShouldThrow(){
        when(locationRepository.findByIdAndMerchantIdAndAndMerchantStatus(1L,1L, MerchantStatus.ACTIVE)).thenThrow(CustomException.class);
        Assertions.assertThrows(CustomException.class,() -> merchantLocationService.getMerchantLocationMenusById(1L,1L));
    }
}
