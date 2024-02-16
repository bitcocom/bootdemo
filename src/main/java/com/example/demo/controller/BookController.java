package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "books/list"; //list.html
    }

}
