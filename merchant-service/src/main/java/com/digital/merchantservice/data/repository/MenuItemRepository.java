package com.digital.merchantservice.data.repository;

import com.digital.common.enums.MenuStatus;
import com.digital.merchantservice.data.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findAllByMenuMerchantIdAndMenuStatus(Long merchantId, MenuStatus active);
}
