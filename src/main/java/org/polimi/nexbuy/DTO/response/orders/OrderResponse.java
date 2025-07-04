package org.polimi.nexbuy.DTO.response.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.polimi.nexbuy.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderResponse {

    private long id;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
    private LocalDateTime estimatedDeliveryDate;

}
