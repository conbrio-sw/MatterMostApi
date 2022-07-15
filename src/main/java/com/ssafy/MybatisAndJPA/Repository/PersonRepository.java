package com.ssafy.MybatisAndJPA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.MybatisAndJPA.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
