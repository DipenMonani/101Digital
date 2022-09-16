package com.digital.common.dto.request.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderRequestDTO {
    private Long customerId;

    private Long merchantId;

    private Long locationId;

    private Long menuId;

    private LocalDateTime pickUpTime;

    private Map<Long, Integer> selectedMenuItemAndQuantityMap;
}
