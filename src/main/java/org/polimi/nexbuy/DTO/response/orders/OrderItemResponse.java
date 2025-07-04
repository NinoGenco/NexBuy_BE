package org.polimi.nexbuy.DTO.response.orders;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {

    private String productName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotalPrice; //unitPrice * quantity
}
