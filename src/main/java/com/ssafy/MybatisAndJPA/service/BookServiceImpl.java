package com.ssafy.MybatisAndJPA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.MybatisAndJPA.Repository.BookRepository;
import com.ssafy.MybatisAndJPA.entity.Book;
import com.ssafy.MybatisAndJPA.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookMapper mapper;
	
	@Autowired
	BookRepository repo;

	@Override
	public List<Book> listRepo() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}

	@Override
	public List<Book> listMapper() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
