package it.live.newlook.mapper;

import it.live.newlook.entity.Order;
import it.live.newlook.payload.OrderDTO;
import it.live.newlook.repository.ProductRepository;
import it.live.newlook.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMapper {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Order toEntity(OrderDTO orderDTO) {
        return Order.builder().product(productRepository.findById(orderDTO.getProductId()).orElseThrow(() -> new RuntimeException("Bunday product mavjud emas"))).count(orderDTO.getCount()).user(userRepository.findById(orderDTO.getUserId()).orElseThrow(() -> new RuntimeException("Bunday user mavjud emas"))).build();
    }
}
