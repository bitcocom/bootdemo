package com.example.demo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class CustomerMember extends User implements OAuth2User {
    private Member member;

    // oAuth2 로그인
    private Map<String, Object> attributes;

    // 일반 로그인
    public CustomerMember(Member member) { //               USER, MANAGER, ADMIN
        super(member.getUsername(), member.getPassword(), getAuthorities(member.getRoles())); // User클래스에 넘겨 준다.
        this.member=member;
    }
    // oAuth2 로그인
    public CustomerMember(Member member, Map<String, Object> attributes) { //               USER, MANAGER, ADMIN
        super(member.getUsername(), member.getPassword(), getAuthorities(member.getRoles())); // User클래스에 넘겨 준다.
        this.member=member;
        this.attributes=attributes;
    }
    private static Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList()); // [ROLE_USER, ROLE_MANAGER, ROLE_ADMIN]
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
   // OAuth2User interface 구현 추가 메서드
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    @Override
    public String getName() {
        return null;
    }
}
