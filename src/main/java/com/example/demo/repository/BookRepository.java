package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// JDBC->MyBatis->JPA
//                                                                   CrudRepository
public interface BookRepository extends JpaRepository<Book, Long> {
   // 전체리스트를 가져오기 - findAll() -> select  b.id, b.title from Book  b;
   // 데이터 입력하기 -  save() -> insert into Book b values(b.title,,,,,)
   // 특정 레코드 가져오기 - findById( PK값 )
   // findByTitleANDPrice(String title, int price) // 쿼리메서드(규칙) : findBy+속성이름
    // -> select * from Book b where b.title=#{title} and b.price=#{price}
    //@Query("select * from book")
    // getList();
    // 특정 레코드 수정하기 - save() -> update ~ Book=id(PK)=X
    // 삭제하기 - deleteById(PK)
    // 2. 쿼리메서드(규칙 : findBy + 속성이름)
   //List<Book> findByTitleAndName(String title, String name);
   // 3. JPQL 형식
   // @Query("select b from Book b") // Entity 기준
   // List<Book> bookList(); // @Param("title")
   // 4. 기본 SQL 형식
    @Query(value="select * from book", nativeQuery = true) // table 기준
     List<Book> bookList(); // @Param("title")

    //Book findByTitleAndName(String title, String name);
    //@Query("select b from Book b where b.title=:title and b.name=:name")
    //Book findByTitleName(@Param("title") String title, @Param("name") String name);

    @Query(value="select * from book where title=?1 and name=?2", nativeQuery = true)
    Book findByTitleName(String title,  String name);
}
// EntityManagerFactory

