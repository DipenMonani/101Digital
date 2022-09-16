package com.digital.customerservice.service;

import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.merchant.LocationDTO;
import com.digital.common.exception.CustomException;
import com.digital.customerservice.client.MerchantClient;
import com.digital.customerservice.common.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerLocationServiceTest {

    @InjectMocks
    private CustomerLocationService customerLocationService;

    @Mock
    private MerchantClient merchantClient;

    @Test
    public void getNearestMerchantLocationBySearchCriteriaShouldSuccess(){
        PageListDTO<LocationDTO> pageListDTO = TestUtils.getPageListLocationDTOs();

        PageRequest pageRequest = PageRequest.of(1, 1, Sort.by(Sort.Direction.DESC,"id"));
        when(merchantClient.getNearestMerchantLocationBySearchCriteria(TestUtils.getCustomerLocationDTO(),1,1)).thenReturn(pageListDTO);
        PageListDTO<LocationDTO>  result =  customerLocationService.getNearestMerchantLocationBySearchCriteria(TestUtils.getCustomerLocationDTO(),pageRequest);
        Assertions.assertEquals(1,result.getPageSize());
    }

    @Test
    public void getNearestMerchantLocationBySearchCriteriaShouldReturnNotEmpty(){
        PageListDTO<LocationDTO> pageListDTO = TestUtils.getPageListLocationDTOs();
        PageRequest pageRequest = PageRequest.of(1, 1, Sort.by(Sort.Direction.DESC,"id"));
        when(merchantClient.getNearestMerchantLocationBySearchCriteria(TestUtils.getCustomerLocationDTO(),1,1)).thenReturn(pageListDTO);
        PageListDTO<LocationDTO>  result =  customerLocationService.getNearestMerchantLocationBySearchCriteria(TestUtils.getCustomerLocationDTO(),pageRequest);
        Assertions.assertNotNull(result.getItems());
    }

    @Test
    public void getMenuByNearestMerchantAndLocationShouldSuccess(){
        LocationDTO  locationDTO = TestUtils.getLocationDTO();
        when(merchantClient.getMenuByNearestMerchantAndLocation(1L, 1L)).thenReturn(locationDTO);
        LocationDTO  result =  customerLocationService.getMenuByNearestMerchantAndLocation(1L, 1L);
        Assertions.assertEquals(locationDTO, result);
    }

    @Test
    public void getMenuByNearestMerchantAndLocationShouldThrow(){
        when(merchantClient.getMenuByNearestMerchantAndLocation(1L,1L)).thenThrow(CustomException.class);
        Assertions.assertThrows(CustomException.class,() -> customerLocationService.getMenuByNearestMerchantAndLocation(1L,1L));
    }
}
