package it.live.newlook.controller;


import it.live.newlook.entity.Order;
import it.live.newlook.mapper.OrderMapper;
import it.live.newlook.payload.ApiResponse;
import it.live.newlook.payload.OrderDTO;
import it.live.newlook.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @PostMapping("/addOrder")
    public ResponseEntity<ApiResponse> addOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        orderRepository.save(order);
        return ResponseEntity.ok(ApiResponse.builder().message("Buyurtma saqlandi").status(200).build());
    }

    @GetMapping("/getAllOrderByPage")
    public List<Order> getAllOrderByPage(@RequestParam int page, @RequestParam int size) {
        return orderRepository.findAll(PageRequest.of(page, size)).stream().toList();
    }

    @GetMapping("/getOrderByUserId/{userId}")
    public List<Order> getOrderByUserId(@PathVariable Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Transactional
    @DeleteMapping("/deleteByUserId/{userId}")
    public ResponseEntity<ApiResponse> deleteByUserId(@PathVariable Long userId) {
        try {
            orderRepository.deleteAllByUserId(userId);
            return ResponseEntity.ok(ApiResponse.builder().message("Ushbu user buyurtmalari o'chirildi").status(200).build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Xatolik");
        }
    }

    @DeleteMapping("/deleteAllOrder")
    public ResponseEntity<ApiResponse> deleteALL() {
        orderRepository.deleteAll();
        return ResponseEntity.ok(ApiResponse.builder().message("Hammasi o'chirildi").status(200).build());
    }
}
