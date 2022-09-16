package com.digital.merchantservice.data.entity;

import com.digital.common.data.entity.Audit;
import com.digital.common.dto.response.merchant.CustomerOrderDTO;
import com.digital.common.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name ="customer_order")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Audit {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant")
    private Merchant merchant;

    private Long locationId;

    private Long menuId;

    private Long customerId;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime pickUpTime;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

    public static CustomerOrderDTO build(Order order) {
        return CustomerOrderDTO.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .merchantId(order.getMerchant().getId())
                .locationId(order.getLocationId())
                .menuId(order.getMenuId())
                .totalAmount(order.getTotal())
                .orderStatus(order.getStatus())
                .orderItems(OrderItem.build(order.getOrderItems()))
                .pickUpTime(order.getPickUpTime())
                .build();
    }

    public static List<CustomerOrderDTO> build(Set<Order> orders) {
        if(CollectionUtils.isEmpty(orders) == false){
            return orders.stream().map(order -> Order.build(order)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
