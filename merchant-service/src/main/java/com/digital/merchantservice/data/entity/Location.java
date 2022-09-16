package com.digital.merchantservice.data.entity;

import com.digital.common.data.entity.Audit;
import com.digital.common.dto.response.merchant.LocationDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name ="location")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location extends Audit {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant")
    private Merchant merchant;

    private String addressLine1;

    private String addressLine2;

    private String postcode;

    private String city;

    private String country;

    private String contactNumber;

    private Double latitude;

    private Double longitude;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "location_menu", joinColumns = @JoinColumn(name = "location_id"), inverseJoinColumns = @JoinColumn(name = "menu_id "))
    @ToString.Exclude
    @JsonIgnore
    private Set<Menu> menus = new HashSet<Menu>();

    public static LocationDTO build(Location location, Boolean isRequiredMenu){
        return LocationDTO.builder()
                .id(location.getId())
                .merchantId(location.getMerchant().getId())
                .merchantName(location.getMerchant().getName())
                .addressLine1(location.getAddressLine1())
                .addressLine2(location.getAddressLine2())
                .city(location.getCity())
                .postcode(location.getPostcode())
                .country(location.getCountry())
                .contactNumber(location.getContactNumber())
                .menus(isRequiredMenu == Boolean.TRUE ? Menu.build(location.getMenus()) : null)
                .build();
    }
    public static List<LocationDTO> build(List<Location> locations){
        if(CollectionUtils.isEmpty(locations) == false){
            return locations.stream().map(location -> Location.build(location, Boolean.FALSE)).collect(Collectors.toList());
        }
        return new ArrayList<LocationDTO>();
    }
}
