package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookRestController { // <---- Ajax(JSON)---->View(JSP/Thymeleaf)

    @Autowired
    private BookService service;

    @GetMapping("/books") //  http://localhost:8081/api/books
    public List<Book> getList(){
        return service.getList(); // JSON
    }
   // http://localhost:8081/api/books
    @PostMapping("/books") //  JSON---@RequestBody---->Object
    public Book register(@RequestBody Book book){
        return service.register(book);
    }
   // http://localhost:8081/api/books/3
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getByBook(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Book not found with id: " + id, HttpStatus.NOT_FOUND);
        }//try
    }

    // http://localhost:8081/api/books/1
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,
                                           @RequestBody Book requestBook) {
        try {
            Book updatedBook = service.update(id, requestBook);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }//
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            service.getByDelete(id);
            return ResponseEntity.ok("Deleted book with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete book with id: " + id + ". Error: " + e.getMessage());
        }
    }

    @GetMapping("/books/{title}/{name}") // books/Python/패스트
    public Book findByTitleAndName(@PathVariable String title, @PathVariable String name){
         return service.findByTitleAndName(title, name);
    }
}
