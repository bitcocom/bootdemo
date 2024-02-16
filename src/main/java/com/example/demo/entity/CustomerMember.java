package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerMember extends User {
    private Member member;

    public CustomerMember(Member member) { //               USER, MANAGER, ADMIN
        super(member.getUsername(), member.getPassword(), getAuthorities(member.getRoles())); // User클래스에 넘겨 준다.
        this.member=member;
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
}
