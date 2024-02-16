package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 1 2 3,,,,,
    @Column(length = 50, nullable = false, unique = true)
    private String username; // 사용자ID
    private String password; // 사용자 비번
    private String uname; // 사용자이름
    private int age;
    private String uemail; // 이메일
    // 권한정보?(ADMIN, MANAGER, USER~~)
    // 회원한명당 1개의 권한? /  (여러개의 권한?)  M : N
   // Member(M) <-------?--------> (N) Role
    //  member_roles
    //  member_id(FK)  |  role_id(FK) |
   @ManyToMany(fetch=FetchType.EAGER)
   @JoinTable(
           name = "member_roles",
           joinColumns = @JoinColumn(name="member_id"), // member -> id(PK)
           inverseJoinColumns = @JoinColumn(name="role_id") // role -> id(PK)
   )
   private Set<Role> roles; // USER, MANAGER, ADMIN
}
