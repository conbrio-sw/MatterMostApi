package com.ssafy.MybatisAndJPA.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.MybatisAndJPA.entity.Book;
@Mapper
public interface BookMapper {
	public List<Book> selectAll();
	public Book selectByBookId(int bookId);
}
