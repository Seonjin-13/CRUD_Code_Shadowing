package com.sparta.miniorder.order.service;

import com.sparta.miniorder.order.dto.OrderRequest;
import com.sparta.miniorder.order.dto.OrderResponse;
import com.sparta.miniorder.order.entity.Order;
import com.sparta.miniorder.order.repository.OrderRepository;
import com.sparta.miniorder.product.entity.Product;
import com.sparta.miniorder.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.streotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 상품이 존재하지 않습니다. id=" + request.getProductId()));

        Order order = new Order(product);
        Order saved = orderRepository.save(order);

        return new OrderResponse(saved);
    }

    public OrderResponse getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 주문이 존재하지 않습니다. id=" + id));

        return new OrderResponse(order);
    }
}