package com.gdut.imis.campus.controller;


import com.gdut.imis.campus.dataobject.PaginationDTO;
import com.gdut.imis.campus.dataobject.QuestionDTO;
import com.gdut.imis.campus.model.JobWithBLOBs;
import com.gdut.imis.campus.model.Question;
import com.gdut.imis.campus.service.JobService;
import com.gdut.imis.campus.service.QuestionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private JobService jobService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id")Integer id, Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        String[] tags=questionDTO.getTag().split("、");
        List<String> resultList = new ArrayList<>(5);
        for (String s : tags) {
            resultList.add(s);
        }
        questionDTO.setTags(resultList);
        //增加阅读数
        if(questionDTO.getId()!=null){
            questionService.incView(id);
        }
        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }

    @GetMapping("/questions")
    public  String index(HttpServletRequest request, Model model,
                         @RequestParam(name="page",defaultValue = "1")Integer page,
                         @RequestParam(name="size",defaultValue = "5")Integer size
    ){
        PageHelper.startPage(page, size);
        List<QuestionDTO> questionlist= questionService.list();
        PaginationDTO paginationDTO= new PaginationDTO();
        paginationDTO.setQuestions(questionlist);
        Integer totalCount=questionService.count();
        paginationDTO.setPagination(totalCount,size,page);
        List<Question> listByCount= questionService.listByViewcount();
        //热门问题只返回前10个
        if(listByCount.size()>10){
            listByCount=listByCount.subList(0,10);
        }
        model.addAttribute("listByCount", listByCount);
        model.addAttribute("paginationDTO", paginationDTO);
        model.addAttribute("option","questions");
        return "questions";
    }

    /**
     * 用户删除某一分享内容
     * @param id
     * @return
     */
    @GetMapping("/stuDelQuestion/{id}")
    public String deleteQuestion(@PathVariable(name="id")Integer id,HttpServletRequest request){
        questionService.delete(id);
        return"redirect:/profile/questions";
    }

    @GetMapping("/jobs")
    public String listjobs(HttpServletRequest request,Model model,
                           @RequestParam(name="page",defaultValue = "1")Integer page,
                           @RequestParam(name="size",defaultValue = "5")Integer size) {
        PageHelper.startPage(page, size);
        List<JobWithBLOBs> joblist= jobService.list();
        PaginationDTO paginationDTO= new PaginationDTO();
        paginationDTO.setJob(joblist);
        Integer totalCount=jobService.count();
        paginationDTO.setPagination(totalCount,size,page);
        model.addAttribute("option","jobs");
        model.addAttribute("paginationDTO", paginationDTO);
        return "jobs";
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

    /**
     * 删除某一分享内容
     * @param id
     * @return
     */
    @GetMapping("/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable(name="id")Integer id){
        questionService.delete(id);
        return"redirect:/profile/questions";
    }

    @ResponseBody
    @PostMapping("/search")
    public Map<String,Object> selectByCondition(HttpServletRequest request, Model model){
        String days=request.getParameter("days");
        String degree=request.getParameter("degree");
        String type=request.getParameter("type");
        int workDays=0;
        if(days!="" && days!=null) {
            workDays = Integer.parseInt(days.substring(0, 1));
        }
        List<JobWithBLOBs> joblist=jobService.selectByCondition(workDays,type,degree);
        Map<String,Object> resultMap=new HashMap<String,Object>();

        resultMap.put("result",joblist);
        return resultMap;
    }

    @GetMapping("/search")
    public String getByName(HttpServletRequest request,
                            @RequestParam(name="page",defaultValue = "1")Integer page,
                            @RequestParam(name="size",defaultValue = "5")Integer size,Model model){
        PageHelper.startPage(page, size);
        String name=request.getParameter("name");
        List<JobWithBLOBs> joblist= jobService.selectByName(name);
        PaginationDTO paginationDTO= new PaginationDTO();
        paginationDTO.setJob(joblist);
        Integer totalCount=jobService.countByName(name);
        paginationDTO.setPagination(totalCount,size,page);
        model.addAttribute("option","jobs");
        model.addAttribute("paginationDTO", paginationDTO);
        return "jobs";
    }

}
