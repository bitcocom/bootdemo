package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class BookController {

    @Autowired
    private BookService service;

    // http://localhost:8081/ui/list
    @GetMapping("/list")
    public String bookList(Model model){
        List<Book> list=service.getList();
        model.addAttribute("list",list);
        return "books/list"; //list.html(로그인페이지)
    }

    @Secured("ROLE_USER")
    @GetMapping("/info")
    public @ResponseBody String info(){
         return "개인정보";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/fast")
    public @ResponseBody String fast(){
        return "패스트캠퍼스";
    }
}
