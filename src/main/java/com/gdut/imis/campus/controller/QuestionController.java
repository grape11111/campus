package com.gdut.imis.campus.controller;


import com.gdut.imis.campus.dataobject.QuestionDTO;
import com.gdut.imis.campus.model.JobWithBLOBs;
import com.gdut.imis.campus.service.JobService;
import com.gdut.imis.campus.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private JobService jobService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id")Integer id, Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        //增加阅读数
        if(questionDTO.getId()!=null){
            questionService.incView(id);
        }
        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }


    @GetMapping("/job/{id}")
    public String job(@PathVariable(name="id")Integer id, Model model) {
        JobWithBLOBs jobWithBLOBs = jobService.getById(id);
        //增加阅读数
        if(jobWithBLOBs.getId()!=null){
            jobService.incView(id);
        }
        model.addAttribute("job",jobWithBLOBs);
        return "job";
    }

    /**
     * 删除某一职位
     * @param id
     * @return
     */
    @GetMapping("/deleteJob/{id}")
    public String deleteJob(@PathVariable(name="id")Integer id){
        jobService.delete(id);
        return"redirect:/profile/jobs";
    }

}
