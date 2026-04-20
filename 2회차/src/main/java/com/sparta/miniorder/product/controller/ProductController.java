package com.sparta.miniorder.product.controller;

import com.sparta.miniorder.product.dto.ProductRequest;
import com.sparta.miniorder.product.dto.ProductResponse;
import com.sparta.miniorder.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController // Controller + ResponseBody -> 모든 메서드 반환값이 JSON 형태로 변환되어 응답
@RequestMapping("/api/products") // 클래스 내부 모든 API 경로에 공통으로 붙는 기본 URL
@RequiredArgsConstructor // final이 붙은 필드를 인자로 받는 생성자 자동 생성 (DI)

// ResponseEntity : status code, Headers, Body (HTTP 3가지 요소를 모두 제어)

public class ProductController {

    private final ProductService productService;

    // @Valid -> ProductRequest의 제약 조건 검증
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.created(URI.create("/api/products/" + response.getId())).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    // 반환 타입이 List<ProductResponse>로 배열 형태의 JSON 응답
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    // 수정할 대상의 ID는 @PathVariable
    // 수정할 데이터는 @RequestBody로 동시에 받음
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request
    ) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        // 응답 본문에 담을 데이터 없을 때, HTTP 204 No Content 상태 코드 반환
        return ResponseEntity.noContent().build();
    }
}
