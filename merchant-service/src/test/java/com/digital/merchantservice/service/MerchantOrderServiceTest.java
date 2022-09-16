package com.digital.merchantservice.service;

import com.digital.common.dto.request.customer.CustomerOrderRequestDTO;
import com.digital.common.dto.response.merchant.CustomerOrderDTO;
import com.digital.common.enums.MerchantStatus;
import com.digital.merchantservice.common.TestUtils;
import com.digital.merchantservice.data.repository.MerchantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MerchantOrderServiceTest {

    @InjectMocks
    private MerchantOrderService merchantOrderService;

    @Mock
    private MerchantRepository merchantRepository;



    @Test
    public void placeCustomerOrderShouldSuccess(){
        CustomerOrderRequestDTO customerOrderRequestDTO = TestUtils.getCustomerOrderRequestDTO();
        CustomerOrderDTO customerOrderDTO = TestUtils.getCustomerOrderDTO();
        when(merchantRepository.findByIdAndStatus(anyLong(), MerchantStatus.ACTIVE)).thenReturn(any());
        when(merchantOrderService.placeCustomerOrder(customerOrderRequestDTO)).thenReturn(customerOrderDTO);
        CustomerOrderDTO result =  merchantOrderService.placeCustomerOrder(customerOrderRequestDTO);
        Assertions.assertEquals(customerOrderDTO, result);
    }

}
