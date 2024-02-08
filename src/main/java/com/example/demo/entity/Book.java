package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Book { // Book->Table
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 일련번호 : 1 2 3 4
    @Column(length = 50, nullable = false)
    private String title;
    private  int price;
    private String name;
    private int page;
}
