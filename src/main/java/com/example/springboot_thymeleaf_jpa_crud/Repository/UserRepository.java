package com.example.springboot_thymeleaf_jpa_crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot_thymeleaf_jpa_crud.entities.User;


public interface  UserRepository extends JpaRepository<User, Integer>{

    User findByEmail(String email);
    

}
