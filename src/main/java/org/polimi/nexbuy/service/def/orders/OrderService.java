package org.polimi.nexbuy.service.def.orders;

import org.polimi.nexbuy.DTO.request.orders.OrderRequest;
import org.polimi.nexbuy.DTO.response.orders.OrderResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface OrderService {

    OrderResponse getOrderById(long orderId);
    OrderResponse createOrder(OrderRequest order);
    void deleteOrderById(long orderId);
    OrderResponse updateOrder(long orderId, OrderRequest order);
    List<OrderResponse> getOrdersByUserId(long userId);
    List<OrderResponse> getOrdersByOrderDate(LocalDate orderDate);
    List<OrderResponse> getOrdersByStatus(String status);
}
