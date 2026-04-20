package com.sparta.miniorder.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity //JPA 엔티티 선언 -> JPA가 Product class 관리(테이블 매핑)
@Getter // Lombok 사용해 Product 모든 field의 Getter method 자동 생성
@Table(name = "products") // Product entity와 매핑할 DB 테이블 이름 명시적으로 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 매개변수 없는 기본 생성자 생성
public class Product {

    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 DB에 위임 -> DB가 자동으로 번호 할당(중복 방지, 동시성 처리)
    private Long id;

    @Column(nullable = false, length = 100)
    // 객체 필드를 테이블 컬럼과 매핑, NOT NULL 제약 조건 및 문자열 최대 길이 제한
    private String name;

    @Column(nullable = false)
    private Integer price;

    // 생성자
    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    // entity 상태 변경 메서드
    public void update(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

}