package org.polimi.nexbuy.DTO.response.products;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductResponse {

    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private boolean available;
    private String category;
    private Double rating;
    private Integer reviewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String sellerName;
}
