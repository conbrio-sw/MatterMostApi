package com.ssafy.MybatisAndJPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.MybatisAndJPA.entity.Book;
import com.ssafy.MybatisAndJPA.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	BookService service;
	
	
	// 목록
	@GetMapping(value = "/bookMapper")
	public List<Book> listMapper() {
		List<Book> list = service.listMapper();
		return list;
	}
	@GetMapping(value = "/bookRepo")
	public List<Book> listRepo() {
		List<Book> list = service.listRepo();
		return list;
	}
}
