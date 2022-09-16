package com.digital.merchantservice.data.repository;

import com.digital.common.enums.MerchantStatus;
import com.digital.merchantservice.data.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    Merchant findByIdAndStatus(Long merchantId, MerchantStatus active);
}
