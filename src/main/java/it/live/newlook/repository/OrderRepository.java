package it.live.newlook.repository;

import it.live.newlook.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long user_id);
    void deleteAllByUserId(Long user_id);
}
