package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
