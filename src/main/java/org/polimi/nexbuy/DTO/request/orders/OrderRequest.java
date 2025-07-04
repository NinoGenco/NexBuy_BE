package org.polimi.nexbuy.DTO.request.orders;

import lombok.Data;
import org.polimi.nexbuy.DTO.response.common.AddressResponse;
import org.polimi.nexbuy.model.orders.OrderItem;
import org.polimi.nexbuy.model.enums.OrderStatus;
import org.polimi.nexbuy.model.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequest {

    private long id;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
    private PaymentType paymentType;
    private String customerEmail;
    private AddressResponse shippingAddress;
    private AddressResponse billingAddress;
    private List<OrderItem> items;
}
