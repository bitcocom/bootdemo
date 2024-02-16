package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Member;
import com.example.demo.entity.Role;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //회원 가입
    public Member memberRegister(Member vo) {
        // 1. 패스워드 해싱
        String hashedPassword = passwordEncoder.encode(vo.getPassword());
        vo.setPassword(hashedPassword);
        // 2. 권한 부여(ㅁUSER ㅁMANAGER ㅁADMIN)
        Set<Role> roles = new HashSet<>();
        Role userRole = roleService.findByName("USER"); // Find or create the USER role
        roles.add(userRole); // USER
        //roles.add(userRole); // MANAGER
        //roles.add(userRole); // ADMIN
        vo.setRoles(roles);
        // 비즈니스 로직 구현~~~
        return memberRepository.save(vo);//insert into member ~~
    }
}