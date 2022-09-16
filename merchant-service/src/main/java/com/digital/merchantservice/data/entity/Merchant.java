package com.digital.merchantservice.data.entity;

import com.digital.common.data.entity.Audit;
import com.digital.common.enums.MerchantStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="merchant")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Merchant extends Audit {
    private String name;

    @Enumerated(EnumType.STRING)
    private MerchantStatus status = MerchantStatus.ACTIVE;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "merchant")
    @OrderBy("createdAt DESC")
    private Set<Location> locations = new HashSet<Location>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "merchant")
    @OrderBy("createdAt DESC")
    private Set<Menu> menus = new HashSet<Menu>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "merchant")
    @OrderBy("createdAt DESC")
    private Set<Order> orders = new HashSet<Order>();
}
