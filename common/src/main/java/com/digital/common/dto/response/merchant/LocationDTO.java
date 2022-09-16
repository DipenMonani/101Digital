package com.digital.common.dto.response.merchant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    private Long id;

    private Long merchantId;

    private String merchantName;

    private String addressLine1;

    private String addressLine2;

    private String postcode;

    private String city;

    private String country;

    private String contactNumber;

    private List<MenuDTO> menus;
}
