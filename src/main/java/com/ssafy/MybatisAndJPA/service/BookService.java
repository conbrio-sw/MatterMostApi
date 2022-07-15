package com.ssafy.MybatisAndJPA.service;

import java.util.List;

import com.ssafy.MybatisAndJPA.entity.Book;

public interface BookService {
	List<Book> listRepo();
	List<Book> listMapper();

}
