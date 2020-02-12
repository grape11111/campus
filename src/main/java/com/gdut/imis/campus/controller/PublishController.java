package com.gdut.imis.campus.controller;

import com.gdut.imis.campus.dataobject.QuestionDTO;
import com.gdut.imis.campus.model.Question;
import com.gdut.imis.campus.model.Student;
import com.gdut.imis.campus.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String publish(@PathVariable(name="id")Integer id, Model model){
        QuestionDTO questionDTO=questionService.getById(id);
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("id", id);
        return "publish";
    }

    @GetMapping("/publish")
    public String getPublish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPubilsh(
            @RequestParam(value="title",required = false) String title,
            @RequestParam(value="description",required = false) String description,
            @RequestParam(value="tag",required = false) String tag,
            @RequestParam(value="id",required = false) Integer id,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if(title==null || title==""){
            model.addAttribute("error", "标题不能为空！");
            return "publish";
        }
        if(description==null || description==""){
            model.addAttribute("error", "内容不能为空！");
            return "publish";
        }
        if(tag==null || tag==""){
            model.addAttribute("error", "标签不能为空！");
            return "publish";
        }


        Student user=(Student)request.getSession().getAttribute("user");

        if(user==null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getAccountId());
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}

