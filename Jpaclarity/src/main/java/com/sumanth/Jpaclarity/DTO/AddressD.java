package com.sumanth.Jpaclarity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressD {
    private Long id;

    private String city;
    private String street;
    private String zipcode;
    private String state;

}
