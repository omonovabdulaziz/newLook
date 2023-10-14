package it.live.newlook.controller;

import it.live.newlook.entity.Product;
import it.live.newlook.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping("/getAllProduct")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/getProductById/{productId}")
    public Product getAllProduct(@PathVariable Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Bunday id li product topilmadi"));
    }
}
