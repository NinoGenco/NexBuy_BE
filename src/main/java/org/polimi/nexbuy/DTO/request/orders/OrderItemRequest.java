package org.polimi.nexbuy.DTO.request.orders;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemRequest {

    private String productName;
    private int quantity;
    private BigDecimal unitPrice;
}
