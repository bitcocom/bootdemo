package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Book { // Book->Table
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 일련번호 : 1 2 3 4
    @Column(length = 50, nullable = false)
    private String title; // 제목
    private  int price;
    private String name; // 저자
    private int page;
}
