package com.example.demo.service;

import com.example.demo.entity.CustomerMember;
import com.example.demo.entity.Member;
import com.example.demo.entity.Role;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration:" + userRequest.getClientRegistration());
        System.out.println("getAccessToken:" + userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User=super.loadUser(userRequest);
        System.out.println("getAttributes:" + oAuth2User.getAttributes());

        // 강제로 회원가입을 진행하기 위해서 정보 추출
        String provider = userRequest.getClientRegistration().getRegistrationId(); // google
        String providerId=oAuth2User.getAttribute("sub");
        String username=provider+"_"+providerId;
        String password=passwordEncoder.encode("임의의값");
        String uname= (String)oAuth2User.getAttributes().get("name");
        int age=0;
        String uemail=oAuth2User.getAttribute("email");
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("USER"); // Find or create the USER role
        roles.add(userRole);

        // 회원등록 여부 체크
        Optional<Member> optional=memberRepository.findByUsername(username);
        Member member=null;
        if(optional.isPresent()){
            System.out.println("로그인을 이미 한적이 있습니다.당신은 자동회원가입이 되어있습니다.");
            member=optional.get();
        }else{
            System.out.println("처음 oAuth2 로그인 사용자 입니다.");
            member = new Member();
            member.setUsername(username);
            member.setPassword(password);
            member.setUname(uname);
            member.setUemail(uemail);
            member.setAge(age); // 수정
            member.setProvider(provider);
            member.setProviderId(providerId);
            member.setRoles(roles); //USER
            memberRepository.save(member); // 강제로 회원가입
        }
        return new CustomerMember(member, oAuth2User.getAttributes());
    }
}
