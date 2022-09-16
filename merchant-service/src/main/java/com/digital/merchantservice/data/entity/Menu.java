package com.digital.merchantservice.data.entity;

import com.digital.common.data.entity.Audit;
import com.digital.common.dto.response.merchant.MenuDTO;
import com.digital.common.enums.MenuStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name ="menu")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends Audit {

    private String name;

    private String startTime;

    private String endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant")
    private Merchant merchant;

    @Enumerated(EnumType.STRING)
    private MenuStatus status;

    @ManyToMany(mappedBy = "menus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @OrderBy("id ASC")
    private Set<Location> locations = new HashSet<>();

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @OrderBy("id ASC")
    private Set<MenuItem> menuItems = new HashSet<MenuItem>();

    public static MenuDTO build(Menu menu) {
        return MenuDTO.builder()
                .id(menu.getId())
                .name(menu.getName())
                .startTime(menu.getStartTime())
                .endTime(menu.getEndTime())
                .status(menu.getStatus())
                .menuItems(MenuItem.build(menu.getMenuItems()))
                .build();
    }

    public static List<MenuDTO> build(Set<Menu> menus) {
        if(CollectionUtils.isEmpty(menus) == false){
            return menus.stream().map(menu -> Menu.build(menu)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
