package com.digital.merchantservice.data.entity;

import com.digital.common.data.entity.Audit;
import com.digital.common.dto.response.merchant.CustomerOrderItemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name ="customer_order_item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends Audit {

    private Long menuItemId;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_order")
    private Order order;

    public static CustomerOrderItemDTO build(OrderItem orderItem) {
        return CustomerOrderItemDTO.builder()
                .id(orderItem.getId())
                .menuItemId(orderItem.getMenuItemId())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }

    public static List<CustomerOrderItemDTO> build(Set<OrderItem> orderItems) {
        if(CollectionUtils.isEmpty(orderItems) == false){
            return orderItems.stream().map(orderItem -> OrderItem.build(orderItem)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
