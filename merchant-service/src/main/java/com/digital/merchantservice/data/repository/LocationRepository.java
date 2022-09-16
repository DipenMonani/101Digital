package com.digital.merchantservice.data.repository;

import com.digital.common.enums.MerchantStatus;
import com.digital.merchantservice.data.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * FROM location lo WHERE lo.id IN ( " +
            "SELECT  l.id FROM location AS l JOIN merchant AS m ON ( m.id = l.merchant) " +
            "JOIN location_menu AS lm ON (lm.location_id = l.id) " +
            "JOIN menu AS mu ON ( lm.menu_id = mu.id) " +
            "JOIN menu_item AS mi ON (mi.menu = mu.id) " +
            "WHERE ((?1 is null AND ?2 is null) OR (SQRT( POW(69.1 * (l.latitude - ?1), 2) + POW(69.1 * (?2 - l.longitude) * COS(l.latitude / 57.3), 2))  < ?3))\n" +
            "AND ((?4 is null) OR (m.name like CONCAT('%',?4,'%') OR mi.category like CONCAT('%',?4,'%') OR (mi.sub_category like CONCAT('%',?4,'%') OR mi.item_name like CONCAT('%',?4,'%')))) \n" +
            ")\n",nativeQuery = true)
    Page<Location> getMerchantLocationMenusBySearchCriteria(Double latitude, Double longitude, Integer radius, String searchString, PageRequest pageable);

    Location findByIdAndMerchantIdAndAndMerchantStatus(Long locationId, Long merchantId, MerchantStatus active);
}
