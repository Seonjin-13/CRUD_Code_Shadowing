package com.sparta.miniorder.product.service;

import com.sparta.miniorder.product.dto.ProductRequest;
import com.sparta.miniorder.product.dto.ProductResponse;
import com.sparta.miniorder.product.entity.Product;
import com.sparta.miniorder.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // Spring 컨테이너에 Bean으로 등록 -> @RestController 클래스에서 ProductService를 주입받아 사용 가능
@RequiredArgsConstructor // final로 선언된 필드 생성자 자동 생성
@Transactional(readOnly = true) // 모든 method를 읽기 전용
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional // readOnly를 default값 false로 덮어씌워 데이터 쓰기 가능
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product(request.getName(), request.getPrice());
        Product saved = productRepository.save(product);
        return new ProductResponse(saved);
    }

    public ProductResponse getProduct(Long id) {
        Product product = findProductById(id);
        return new ProductResponse(product);
    }

    public List<ProductResponse> getProducts() {
        return ProductRepository.findAll().stream()
                .map(ProductResponse::new)
                .toList();
    }

    // save() 사용 X -> Transactional로 변경 감지(Dirty Checking) 후, 이전과 상태가 다르면 자동으로 UPDATE 쿼리 수행
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = findProductById(id);
        product.update(request.getName(), request.getPrice());
        return new ProductResponse(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = findProductById(id);
        productRepository.delete(product);
    }

    // ID로 상품 찾는 공통 method 구현
    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다. id=" + id));
    }
}