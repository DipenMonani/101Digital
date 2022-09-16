package com.digital.merchantservice.service;

import com.digital.merchantservice.data.entity.MenuItem;
import com.digital.merchantservice.data.entity.Merchant;
import com.digital.merchantservice.data.entity.Order;
import com.digital.merchantservice.data.entity.OrderItem;
import com.digital.merchantservice.data.repository.MerchantRepository;
import com.digital.merchantservice.data.repository.OrderRepository;
import com.digital.merchantservice.data.repository.MenuItemRepository;
import com.digital.common.dto.request.customer.CustomerOrderRequestDTO;
import com.digital.common.dto.response.merchant.CustomerOrderDTO;
import com.digital.common.enums.ErrorCode;
import com.digital.common.enums.MenuStatus;
import com.digital.common.enums.MerchantStatus;
import com.digital.common.enums.OrderStatus;
import com.digital.common.exception.CustomException;
import com.digital.common.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MerchantOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public CustomerOrderDTO placeCustomerOrder(CustomerOrderRequestDTO customerOrder) {
        if(customerOrder.getSelectedMenuItemAndQuantityMap().size() == 0){
            log.error("An order has at least one menu item.");
            throw new CustomException("Order must have at least one menu item.", ErrorCode.VALIDATION_ERROR, HttpStatus.BAD_REQUEST);
        }
        Merchant merchant = merchantRepository.findByIdAndStatus(customerOrder.getMerchantId(), MerchantStatus.ACTIVE);
        if(merchant == null){
            log.error("Merchant not found with given id : {}", customerOrder.getMerchantId());
            throw new ResourceNotFoundException(Merchant.class, String.format("Merchant not found with given id : %s", customerOrder.getMerchantId()), ErrorCode.MERCHANT_NOT_FOUND);
        }

        Map<Long, BigDecimal> menuItemPriceDetailsMap = getMerchantMenuItemPriceDetails(merchant.getId());

        preValidationCheck(customerOrder, merchant, menuItemPriceDetailsMap);

        Order order = mapToMerchantOrder(customerOrder, merchant, menuItemPriceDetailsMap);
        orderRepository.save(order);
        log.info("Customer order is placed successfully! customer : {}, order : {}, order-status : {}", order.getCustomerId(), order.getId(), order.getStatus());

        return Order.build(order);
    }

    private void preValidationCheck(CustomerOrderRequestDTO customerOrder, Merchant merchant, Map<Long, BigDecimal> menuItemPriceDetailsMap) {
       List<Long> locationIds = merchant.getLocations().stream().map(location -> location.getId()).collect(Collectors.toList());
       if(!locationIds.contains(customerOrder.getLocationId())){
           throw new CustomException(String.format("Merchant location not exist with location : %s", customerOrder.getLocationId()), ErrorCode.MERCHANT_LOCATION_NOT_EXIST, HttpStatus.BAD_REQUEST);
       }

        for (Map.Entry<Long, Integer> menuItemAndQuantity : customerOrder.getSelectedMenuItemAndQuantityMap().entrySet()) {
            Long menuItemId = menuItemAndQuantity.getKey();
            if(menuItemPriceDetailsMap.get(menuItemId) == null){
                throw new CustomException(String.format("Merchant menu-item not exist with menu-item : %s, merchant : %s", menuItemId, merchant.getId()), ErrorCode.MERCHANT_LOCATION_MENU_ITEM_NOT_EXIST, HttpStatus.BAD_REQUEST);
            }
        }
    }

    private Map<Long, BigDecimal> getMerchantMenuItemPriceDetails(Long merchantId) {
        List<MenuItem> menuItems = menuItemRepository.findAllByMenuMerchantIdAndMenuStatus(merchantId, MenuStatus.ACTIVE);
        Map<Long, BigDecimal> menuItemPriceMap = menuItems.stream().collect(Collectors.toMap(MenuItem::getId, MenuItem:: getPrice));
        return menuItemPriceMap;
    }

    private Order mapToMerchantOrder(CustomerOrderRequestDTO customerOrder, Merchant merchant, Map<Long, BigDecimal> menuItemPriceDetailsMap) {
        Order order = Order.builder()
                        .merchant(merchant)
                        .locationId(customerOrder.getLocationId())
                        .menuId(customerOrder.getMenuId())
                        .customerId(customerOrder.getCustomerId())
                        .status(OrderStatus.QUEUED)
                        .pickUpTime(customerOrder.getPickUpTime())
                        .build();
        Set<OrderItem> orderItems = mapToOrderItems(customerOrder.getSelectedMenuItemAndQuantityMap(), order, menuItemPriceDetailsMap);
        order.setOrderItems(orderItems);

        BigDecimal total = orderItems.stream().map(orderItem -> orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
        order.setTotal(total);
        return order;
    }

    private Set<OrderItem> mapToOrderItems(Map<Long, Integer> selectedMenuItemAndQuantityMap, Order order, Map<Long, BigDecimal> menuItemPriceDetailsMap) {
        Set<OrderItem> orderItems = new HashSet<>();
        for (Map.Entry<Long, Integer> menuItemAndQuantity : selectedMenuItemAndQuantityMap.entrySet()) {
            Long menuItemId = menuItemAndQuantity.getKey();
            Integer quantity = menuItemAndQuantity.getValue();

            OrderItem orderItem = OrderItem.builder()
                    .menuItemId(menuItemAndQuantity.getKey())
                    .quantity(quantity)
                    .order(order)
                    .price(menuItemPriceDetailsMap.get(menuItemId) != null ? menuItemPriceDetailsMap.get(menuItemId) : BigDecimal.ZERO)
                    .build();
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    public CustomerOrderDTO getCustomerOrder(Long customerId, Long orderId) {
        Order order = orderRepository.findByCustomerIdAndId(customerId, orderId);
        if(order == null){
            throw new ResourceNotFoundException(Order.class, String.format("Customer-order not found with given customer: %s, order: %s", customerId, orderId), ErrorCode.CUSTOMER_ORDER_NOT_FOUND);
        }
        return Order.build(order);
    }
}
