package org.polimi.nexbuy.repository.orders;

import org.polimi.nexbuy.model.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByOrderDate(LocalDateTime orderDate);

}