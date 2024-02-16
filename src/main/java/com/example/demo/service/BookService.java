package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // 트렌젝션 처리
public class BookService {

    @Autowired
    private BookRepository repository;

    //전체리스트 가져오기
    public List<Book> getList(){
        //return repository.findAll(); // select * from book
        return repository.bookList();
    }
    // 등록하기 메서드 만들기
   public Book register(Book book){
        return repository.save(book);
   }

   // 특정 레코드 가져오기
   public Book getById(Long id){
       Optional<Book> optional =repository.findById(id);
       if(optional.isPresent()){
           return optional.get();
       }else{
           throw new RuntimeException("Book not found with id: " + id);
       }
   }
    @Transactional
    public Book update(Long id, Book requestBook) {
        Optional<Book> optional = repository.findById(id);
        if (optional.isPresent()) {
            Book book = optional.get(); // 영속성메모리(DB테이블 정보를관리) <---> Database
            //                                                         Book <---------------------|(book)
            //                                                        title, price 변경 <----------------|update
            book.setTitle(requestBook.getTitle());
            book.setPrice(requestBook.getPrice());
            return book;
        } else {
            // 저장? - save() : insert~
            throw new RuntimeException("Book not found with id: " + id);
        }//
    }
    public void getByDelete(Long id){
        repository.deleteById(id);
    }

  public Book findByTitleAndName(String title, String name){
        return repository.findByTitleName(title, name);
  }

}
