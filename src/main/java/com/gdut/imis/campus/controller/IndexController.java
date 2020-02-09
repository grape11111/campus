package com.gdut.imis.campus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @GetMapping("/")
    public String test(HttpServletRequest request){
//        List<User> list=userMapper.findAllUsers();
//        for(User user:list){
//            System.out.println(user.getId()+"  "+user.getName());
//        }
//        request.getSession().setAttribute("list",list);
        return "index";
    }

    @GetMapping("/campus")
    public String index(HttpServletRequest request){
        return "index";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request){
        String username=request.getParameter("username");
        String pwd=request.getParameter("password");
        String type=request.getParameter("type");
        if(username.equals("aaa") && pwd.equals("123") && type.equals("0")){
            request.getSession().setAttribute("user",username);
        }
        return "index";
    }

    @GetMapping("/logout")
    public String callback(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");
        return "redirect:/campus";
    }
}
