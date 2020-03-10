package com.gdut.imis.campus.controller;

import com.gdut.imis.campus.mapper.EnterpriseMapper;
import com.gdut.imis.campus.mapper.StudentMapper;
import com.gdut.imis.campus.model.*;
import com.gdut.imis.campus.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private JobService jobService;

    @GetMapping("/")
    public String test(HttpServletRequest request){
        return "index";
    }

    @GetMapping("/campus")
    public String index(HttpServletRequest request,Model model){
        Cookie[]cookies=request.getCookies();
        if (cookies!=null&&cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName() != null) {
                    if (("accountId").equals(cookie.getName())) {
                        StudentExample studentExample = new StudentExample();
                        studentExample.createCriteria()
                                .andAccountIdEqualTo(cookie.getValue());
                        List<Student> list=studentMapper.selectByExample(studentExample);
                        if (list.size()!=0) {
                            request.getSession().setAttribute("user",list.get(0));
                            request.getSession().setAttribute("type", "0");
                        }
                    }else if(("enterpriseId").equals(cookie.getName())) {
                        EnterpriseExample enterpriseExample = new EnterpriseExample();
                        enterpriseExample.createCriteria()
                                .andIdEqualTo(Integer.parseInt(cookie.getValue()));
                        List<Enterprise> list = enterpriseMapper.selectByExample(enterpriseExample);
                        if (list.size() != 0) {
                            request.getSession().setAttribute("user", list.get(0));
                            request.getSession().setAttribute("type", "2");
                        }
                    }
                }
            }
        }
        List<JobWithBLOBs> joblist= jobService.list();
        List<JobWithBLOBs> listByCount= jobService.listByViewcount();
        model.addAttribute("joblist", joblist);
        model.addAttribute("listByCount", listByCount);
        model.addAttribute("option","index");
        return "index";
    }

    @PostMapping("/campus")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model){
        String username=request.getParameter("username");
        String pwd=request.getParameter("password");
        String type=request.getParameter("type");
        if(type.equals("0")){
            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria()
                    .andAccountIdEqualTo(username);
            List<Student>list = studentMapper.selectByExample(studentExample);
            if (list.size()!=0) {
                Student stu=list.get(0);
                if( pwd.equals(stu.getPassword()) && stu.getStatus()==1){
                    request.getSession().setAttribute("user",stu);
                    request.getSession().setAttribute("type", "0");
                    response.addCookie(new Cookie("accountId",stu.getAccountId()));
                }else{
                    model.addAttribute("error","密码出错啦！");
                    return "error";
                }
            }else{
                model.addAttribute("error","用户不存在！");
                return "error";
            }
        }else if(type.equals("2")) {
            EnterpriseExample enterpriseExample = new EnterpriseExample();
            enterpriseExample.createCriteria()
                    .andPhoneEqualTo(username);
            List<Enterprise>list = enterpriseMapper.selectByExample(enterpriseExample);
            if (list.size()!=0) {
                Enterprise stu=list.get(0);
                if( pwd.equals(stu.getPassword()) && stu.getStatus()==1){
                    request.getSession().setAttribute("user",stu);
                    request.getSession().setAttribute("type", "2");
                    response.addCookie(new Cookie("enterpriseId",stu.getId().toString()));
                }else{
                    model.addAttribute("error","密码出错啦！");
                    return "error";
                }
            }else{
                model.addAttribute("error","用户不存在！");
                return "error";
            }
        }
        model.addAttribute("option","index");
        List<JobWithBLOBs> joblist= jobService.list();
        List<JobWithBLOBs> listByCount= jobService.listByViewcount();
        model.addAttribute("joblist", joblist);
        model.addAttribute("listByCount", listByCount);
        return "index";
    }

    @GetMapping("/logout")
    public String callback(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("type");
        Cookie cookie=new Cookie("accountId",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        Cookie companyId=new Cookie("enterpriseId",null);
        companyId.setMaxAge(0);
        response.addCookie(companyId);
        return "redirect:/campus";
    }
}
