package com.example.demo.controller;

import com.example.demo.entity.CustomerMember;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @RequestMapping("/list")
     public String list(Model model){
        List<String> list=new ArrayList<>();
        list.add("사과");
        list.add("바나나");
        list.add("귤");
        model.addAttribute("list", list);
        return "list"; // list.html <-- ${ }
    }
   @GetMapping("/test/login")
    public @ResponseBody String testLogin(Authentication authentication,
                                          @AuthenticationPrincipal CustomerMember customerMember){
       System.out.println("/test/login====================");
       CustomerMember cus = (CustomerMember) authentication.getPrincipal();
       System.out.println("authentication :" + cus.getMember());
       System.out.println("authentication :" + customerMember.getMember());
       return "세션 정보 확인하기";
    }

    @GetMapping("/test/oauth2/login")
    public @ResponseBody String testOauth2Login(Authentication authentication,
                                                @AuthenticationPrincipal OAuth2User oauth2 ){
        System.out.println("/test/oauth2/login====================");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication :" + oAuth2User.getAttributes());
        System.out.println("oAuth2User :" + oauth2.getAttributes());
        return "oAuth2 세션 정보 확인하기";
    }

    //oAuth2 -> CustomerMember받을 수 있다.
    //일반로그인 -> CustomerMember받을 수 있다.
    @GetMapping("/user")
    public @ResponseBody String user
                 (@AuthenticationPrincipal CustomerMember customerMember){
        System.out.println("CustomerMember :" + customerMember.getMember());
        return "user";
    }
}
