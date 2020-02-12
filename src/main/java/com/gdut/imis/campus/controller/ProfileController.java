package com.gdut.imis.campus.controller;

import com.gdut.imis.campus.dataobject.PaginationDTO;
import com.gdut.imis.campus.dataobject.QuestionDTO;
import com.gdut.imis.campus.model.Student;
import com.gdut.imis.campus.service.QuestionService;
import com.gdut.imis.campus.service.StudentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {


    @Autowired
    private StudentService studentService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name="action")String action, HttpServletRequest request, Model model,
                          @RequestParam(name="page",defaultValue = "1")Integer page,
                          @RequestParam(name="size",defaultValue = "5")Integer size
    ){
        Student user=(Student)request.getSession().getAttribute("user");
        if("questions".equals(action)) {
            PageHelper.startPage(page,size);
            List<QuestionDTO> questionlist= questionService.listByUserId(user.getAccountId());
            PaginationDTO paginationDTO= new PaginationDTO();
            paginationDTO.setQuestions(questionlist);
            Integer totalCount=questionService.countByUserId(user.getAccountId());
            paginationDTO.setPagination(totalCount,size,page);
            model.addAttribute("paginationDTO", paginationDTO);
            model.addAttribute("selection", action);
            model.addAttribute("selectionName","我的分享");
        }else if("info".equals(action)) {
            model.addAttribute("selection", action);
            model.addAttribute("selectionName","个人信息");
        }else if("replies".equals(action)) {
            model.addAttribute("selection", action);
            model.addAttribute("selectionName","最新回复");
        }else if("liking".equals(action)) {
            model.addAttribute("selection", action);
            model.addAttribute("selectionName","我关注的话题");
        }
        return "profile";
    }

    @PostMapping("/profile/info")
    public String editInfo(HttpServletRequest request, Model model){
        Student stu =new Student();
        String accountId=((Student)request.getSession().getAttribute("user")).getAccountId();
        stu.setAccountId(accountId);
        if(request.getParameter("password1")!=null && request.getParameter("password2")!=null ){
            stu.setPassword(request.getParameter("password1"));
        }else {
            stu.setDegree(request.getParameter("degree"));
            stu.setNickname(request.getParameter("nickname"));
            stu.setEmail(request.getParameter("email"));
            stu.setPhone(request.getParameter("phone"));
            stu.setMajor(request.getParameter("major"));
            stu.setDept(request.getParameter("dept"));
            stu.setBio(request.getParameter("bio"));
        }
        Student newStu=studentService.createOrUpdate(stu);
        if(newStu!=null) {
            request.getSession().setAttribute("user", newStu);
        }else{
            model.addAttribute("editInfo","操作失败！");
        }
        return "redirect:/profile/info";
    }

}
