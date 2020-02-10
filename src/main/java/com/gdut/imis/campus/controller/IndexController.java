package com.gdut.imis.campus.controller;

import com.gdut.imis.campus.mapper.StudentMapper;
import com.gdut.imis.campus.model.Student;
import com.gdut.imis.campus.model.StudentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private StudentMapper studentMapper;

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
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria()
                .andAccountIdEqualTo(username);
        List<Student> list=studentMapper.selectByExample(studentExample);
        if (list.size()!=0) {
            Student stu=list.get(0);
            if( pwd.equals(stu.getPassword()) && type.equals(stu.getType().toString())){
                request.getSession().setAttribute("user",stu);
            }else{
                System.out.println("密码错误"+stu.getPassword()+stu.getType());
            }
        }else{
            System.out.println("该用户不存在！");
        }

        return "index";
    }

    @GetMapping("/logout")
    public String callback(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");
        return "redirect:/campus";
    }
}
