package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {   //  1   : USER,  2 :  MANAGER, 3 : ADMIN ......4, 5,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 1

    @Column(unique = true)
    private String name; // USER --> ROLE_USER

}
