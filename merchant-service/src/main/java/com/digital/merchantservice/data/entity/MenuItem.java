package com.digital.merchantservice.data.entity;

import com.digital.common.data.entity.Audit;
import com.digital.common.dto.response.merchant.MenuItemDTO;
import com.digital.common.enums.OrderItemCategory;
import com.digital.common.enums.OrderItemSubCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name ="menu_item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem extends Audit {

    @Enumerated(EnumType.STRING)
    private OrderItemCategory category;

    @Enumerated(EnumType.STRING)
    private OrderItemSubCategory subCategory;

    private String itemName;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu")
    @JsonIgnore
    private Menu menu;

    public static MenuItemDTO build(MenuItem menuItem) {
        return MenuItemDTO.builder()
                .id(menuItem.getId())
                .category(menuItem.getCategory())
                .subCategory(menuItem.getSubCategory())
                .itemName(menuItem.getItemName())
                .price(menuItem.getPrice())
                .quantity(menuItem.getQuantity())
                .build();
    }

    public static List<MenuItemDTO> build(Set<MenuItem> menuItems) {
        if(CollectionUtils.isEmpty(menuItems) == false){
            return menuItems.stream().map(menuItem -> MenuItem.build(menuItem)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
