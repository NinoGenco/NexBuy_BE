package org.polimi.nexbuy.service.impl.orders;

import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.DTO.request.orders.OrderRequest;
import org.polimi.nexbuy.DTO.response.orders.OrderResponse;
import org.polimi.nexbuy.repository.orders.OrderRepository;
import org.polimi.nexbuy.service.def.orders.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderResponse getOrderById(long orderId) {
        return null;
    }

    @Override
    public OrderResponse createOrder(OrderRequest order) {
        return null;
    }

    @Override
    public void deleteOrderById(long orderId) {

    }

    @Override
    public OrderResponse updateOrder(long orderId, OrderRequest order) {
        return null;
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(long userId) {
        return List.of();
    }

    @Override
    public List<OrderResponse> getOrdersByOrderDate(LocalDate orderDate) {
        return List.of();
    }

    @Override
    public List<OrderResponse> getOrdersByStatus(String status) {
        return List.of();
    }
}
