package com.example.springboot_thymeleaf_jpa_crud.Services;

import java.util.List;

import com.example.springboot_thymeleaf_jpa_crud.entities.User;

public interface UserService {

    public boolean InsertUser(User user);
    public List<User> getAllList();
    public User getById(int id);
    public boolean UpdateUser(User user,int id);
    public boolean DeleteUser(int id);
    public User loginuser(String email,String pass);

}
