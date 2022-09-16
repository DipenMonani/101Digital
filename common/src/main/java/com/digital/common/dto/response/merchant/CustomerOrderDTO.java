package com.digital.common.dto.response.merchant;

import com.digital.common.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderDTO {
    private Long id;

    private Long customerId;

    private Long merchantId;

    private Long locationId;

    private Long menuId;

    private BigDecimal totalAmount;

    private List<CustomerOrderItemDTO> orderItems;

    private OrderStatus orderStatus;

    private LocalDateTime pickUpTime;
}
