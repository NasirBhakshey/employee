package com.example.springboot_thymeleaf_jpa_crud.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot_thymeleaf_jpa_crud.Services.ServiceImpl;
import com.example.springboot_thymeleaf_jpa_crud.entities.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class mycontroller {

    @Autowired
    private ServiceImpl serviceImpl;

    @GetMapping("regpage")
    public ModelAndView display()
    {
        return new ModelAndView("register","user",new User());
    }

    @PostMapping("regform")
    public String register(@ModelAttribute("user") User user,Model model){
        boolean status=serviceImpl.InsertUser(user);
        if(status)
        {
            model.addAttribute("Successmsg", "User Register Successfully...");
        }
        else
        {
            model.addAttribute("errormsg", "User Register Failed Due TSo Some Error");
        }
        return "register";
    }


    @GetMapping("loginpage")
    public ModelAndView loginpage()
    {
        return new ModelAndView("login","user",new User());
    }

    @PostMapping("loginform")
    public String loginform(@ModelAttribute("user") User user,Model model)
    {
        User user1= serviceImpl.loginuser(user.getEmail(), user.getPassword());
        if(user1 !=null)
        {
            model.addAttribute("UserName", user1.getName());
            return "profile";
        }else{
            model.addAttribute("errormsg", "Sorry Email-ID And Password Didnt Match...");
            return "login";
        }
    }

    @GetMapping("logout")
    public String Logoutpage(HttpServletRequest request)
    {
        HttpSession session=request.getSession(false);
        if(session != null)
        {
            session.invalidate();
        }
        return "redirect:/loginpage";
    }

    @GetMapping("viewpage")
    public String viewform(Model model){
        List<User> vList=serviceImpl.getAllList();
        model.addAttribute("Viewlist", vList);
        return "View";
    }

    @GetMapping("/Viewlist/edit/{ID}")
    public ModelAndView Editsave(@PathVariable int ID)
    {
        User user=serviceImpl.getById(ID);
        return new ModelAndView("Editpage","user",user);
    }

    @PostMapping("/Viewlist/{ID}")
    public String editpage(@PathVariable("ID") int ID,@ModelAttribute("user") User user)
    {
        serviceImpl.UpdateUser(user, ID);
        return "redirect:/viewpage";
    }

    @GetMapping("/Viewlist/{ID}")
    public String deletepage(@PathVariable("ID") int ID)
    {
        serviceImpl.DeleteUser(ID);
        return "redirect:/viewpage";
    }

    


}
