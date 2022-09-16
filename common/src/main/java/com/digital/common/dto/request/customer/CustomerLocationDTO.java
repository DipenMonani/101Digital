package com.digital.common.dto.request.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLocationDTO {
    private Double latitude;
    private Double longitude;
    private Integer radius;
    private String searchString="";
}
