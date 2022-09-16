package com.digital.merchantservice.data.repository;

import com.digital.merchantservice.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByCustomerIdAndId(Long customerId, Long orderId);
}
