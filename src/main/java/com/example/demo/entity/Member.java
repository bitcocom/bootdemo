package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 1 2 3,,,,,
    @Column(length = 255, nullable = false, unique = true)
    private String username; // 사용자ID
    private String password; // 사용자 비번
    private String uname; // 사용자이름
    private int age; // 나이
    private String uemail; // 이메일
    // oAuth2 추가
    private String provider;
    private String providerId;
    @CreationTimestamp
    private Timestamp createDate;

   @ManyToMany(fetch=FetchType.EAGER)
   @JoinTable(
           name = "member_roles",
           joinColumns = @JoinColumn(name="member_id"), // member -> id(PK)
           inverseJoinColumns = @JoinColumn(name="role_id") // role -> id(PK)
   )
   private Set<Role> roles;
}
