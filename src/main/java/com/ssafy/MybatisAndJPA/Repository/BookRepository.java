package com.ssafy.MybatisAndJPA.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.MybatisAndJPA.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
