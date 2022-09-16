package com.digital.customerservice.client;

import com.digital.common.dto.request.customer.CustomerLocationDTO;
import com.digital.common.dto.request.customer.CustomerOrderRequestDTO;
import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.merchant.CustomerOrderDTO;
import com.digital.common.dto.response.merchant.LocationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="merchant", url = "localhost:8081/api/v1/merchant")
public interface MerchantClient {
    @PostMapping("/locations")
    public PageListDTO<LocationDTO> getNearestMerchantLocationBySearchCriteria(@RequestBody CustomerLocationDTO customerLocationDTO,@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                             @RequestParam(value = "size", defaultValue = "20") int size);

    @GetMapping("/{merchant-id}/location/{location-id}/menus")
    LocationDTO getMenuByNearestMerchantAndLocation(@PathVariable("merchant-id") Long merchantId, @PathVariable("location-id") Long locationId);

    @PostMapping("/customer-order")
    CustomerOrderDTO placeCustomerOrder(@RequestBody CustomerOrderRequestDTO customerOrder);

    @GetMapping("/customer-order/{customer-id}/order/{order-id}")
    CustomerOrderDTO getCustomerOrder(@PathVariable("customer-id") Long customerId, @PathVariable("order-id") Long orderId);
}
