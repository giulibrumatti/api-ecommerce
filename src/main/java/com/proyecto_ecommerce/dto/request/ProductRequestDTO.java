package com.proyecto_ecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    private String name;
    private String image;
    private Double price;
    private Integer stock;
    private String category;
    private String description;
}
