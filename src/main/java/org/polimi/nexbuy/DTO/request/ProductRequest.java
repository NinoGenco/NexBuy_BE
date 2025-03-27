package org.polimi.nexbuy.DTO.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private boolean available;
    private String category;
    private Long sellerId;
}

