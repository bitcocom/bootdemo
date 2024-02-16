package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity // 웹 보안을 활성화
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 회원가입 시 비밀번호 암호화에 사용할 Encoder 빈 등록
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                    .requestMatchers("/api/**").authenticated() // "/api/**" 경로는 인증후 접근
                    .requestMatchers("/book/**").authenticated() // "/book/**" 경로는 인증후 접근
                    .anyRequest().permitAll()
            )
           .formLogin(form -> form
                .loginPage("/ui/list") // 사용자 정의 로그인 페이지
                .loginProcessingUrl("/login") // 로그인 처리 URL
                .defaultSuccessUrl("/ui/list", true) // 로그인 성공 후 리다이렉션할 기본 URL
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // 로그아웃 URL
                .logoutSuccessUrl("/ui/list") // 로그아웃 성공 후 리다이렉션할 URL
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // GET 요청으로 로그아웃 허용
                .clearAuthentication(true) // 로그아웃 시 인증정보 클리어(SecurityContext)
                .deleteCookies("JSESSIONID") // 로그아웃 시 삭제할 쿠키 이름
                .invalidateHttpSession(true) // 세션 무효화
            );
        return http.build();
    }
}
