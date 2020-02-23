package com.gdut.imis.campus.controller;

import com.gdut.imis.campus.dataobject.QuestionDTO;
import com.gdut.imis.campus.model.Enterprise;
import com.gdut.imis.campus.model.JobWithBLOBs;
import com.gdut.imis.campus.model.Question;
import com.gdut.imis.campus.model.Student;
import com.gdut.imis.campus.service.JobService;
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

    @Autowired
    private JobService jobService;

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
    public String getPublish(HttpServletRequest request){
        if(request.getSession().getAttribute("type").toString().equals("2")){
            return "publishEnt";
        }else {
            return "publish";
        }
    }

    /**
     * 学生分享问题
     * */
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
        return "redirect:/profile/questions";
    }


    /**
     * 修改某一职位信息
     * @param id--某一职位id
     * @param model
     * @return
     */
    @GetMapping("/publishEnt/{id}")
    public String publishEnt(@PathVariable(name="id")Integer id, Model model){
        JobWithBLOBs job=jobService.getById(id);
        model.addAttribute("name", job.getName());
        model.addAttribute("degree", job.getDegree());
        model.addAttribute("LowSalary",job.getLowSalary());
        model.addAttribute("HighSalary",job.getHighSalary());
        model.addAttribute("GmtInvalid",job.getGmtInvalid());
        model.addAttribute("content",job.getContent());
        model.addAttribute("requirement",job.getRequirement());
        model.addAttribute("id", id);
        return "publishEnt";
    }

    /**
     * 企业用户发布或者修改职位
     * */
    @PostMapping("/publishEnt")
    public String doPublishJob(@RequestParam(value="name") String name,
                               @RequestParam(value="degree") String degree,
                               @RequestParam(value="LowSalary") String LowSalary,
                               @RequestParam(value="HighSalary") String HighSalary,
                               @RequestParam(value="GmtInvalid") String GmtInvalid,
                               @RequestParam(value = "content") String content,
                               @RequestParam(value="requirement") String requirement,
                               @RequestParam(value="id") String id,
                               HttpServletRequest request,
                               Model model){

        JobWithBLOBs job = new JobWithBLOBs();
        if(id!=null){
            job.setId(Integer.parseInt(id));
        }
        job.setName(name);
        job.setDegree(degree);
        job.setLowSalary(Integer.parseInt(LowSalary));
        job.setHighSalary(Integer.parseInt(HighSalary));
        job.setGmtInvalid(GmtInvalid);
        job.setContent(content);
        job.setRequirement(requirement);
        Enterprise ent = (Enterprise) request.getSession().getAttribute("user");
        job.setEnterpriseId(ent.getId());
        job.setEnterpriseName(ent.getCompany());
        job.setEnterpriseLogo(ent.getAvatarUrl());

        jobService.createOrUpdate(job);
        return "redirect:/profile/jobs";
    }


    /**
     * 修改职位状态：0-下线  1-上线
     * @param id
     * @param status
     * @param request
     * @param model
     * @return
     */
    @PostMapping("/setStatus")
    public String doSetStatus(@RequestParam(value="id") String id,
                               @RequestParam(value="status") String status,
                               HttpServletRequest request,
                               Model model){

            JobWithBLOBs job=new JobWithBLOBs();
            job.setId(Integer.parseInt(id));
            int temp=Integer.parseInt(status);
            if(temp==1){
                job.setStatus(0);
            }else{
                job.setStatus(1);
            }
            jobService.createOrUpdate(job);
            return"redirect:/profile/jobs";
    }

}

