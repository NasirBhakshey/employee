package com.example.springboot_thymeleaf_jpa_crud.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot_thymeleaf_jpa_crud.Repository.UserRepository;
import com.example.springboot_thymeleaf_jpa_crud.entities.User;

@Service
public class ServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean InsertUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getAllList() {
        return userRepository.findAll();
    }

    @Override
    public User getById(int id) {
       Optional<User> optional=userRepository.findById(id);
       if(optional.isPresent())
       {
        return optional.get();
       }else{
        return null;
       }
    }

    @Override
    public boolean UpdateUser(User user,int id) {
        User user1=getById(id);
        if(user1 !=null)
        {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean DeleteUser(int id) {
        User user1=getById(id);
        if(user1 !=null)
        {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User loginuser(String email, String pass) {
       User user=userRepository.findByEmail(email);
       if(user!=null && user.getPassword().equals(pass))
       {
        return user;
       }
       return null;
    }

}
