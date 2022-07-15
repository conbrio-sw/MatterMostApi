package com.ssafy.MybatisAndJPA.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.MybatisAndJPA.entity.Person;

@Mapper
public interface PersonMapper {
	public List<Person> selectAll();
}
