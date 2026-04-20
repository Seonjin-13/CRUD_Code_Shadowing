package com.sparta.miniorder.product.dto;

import com.sparta.miniorder.product.entity.Product;
import lombok.Getter;

@Getter
public class ProductResponse {
    // final 키워드 -> '읽기 전용' 객체임을 명시 (데이터 변조 방지)
    // final 필드 포함되면, @NoArgsConstructor 사용 불가 (모든 필드 초기화하기 때문)
    private final Long id;
    private final String name;
    private final Integer price;

    // Product 엔티티를 그대로 반환하지 않는 이유
    // 1. 필요한 데이터만 선택적으로 노출 가능
    // 2. 안전하게 데이터 전달 (연관 관계 매핑 시, 순환 참조로 무한 루프 발생 가능성 제거)
    // 3. DB 설계 바뀌어도 API 유지 가능
    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}