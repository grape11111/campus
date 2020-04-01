package com.gdut.imis.campus.controller;

import com.alibaba.fastjson.JSONObject;
import com.gdut.imis.campus.dataobject.PaginationDTO;
import com.gdut.imis.campus.dataobject.QuestionDTO;
import com.gdut.imis.campus.model.Enterprise;
import com.gdut.imis.campus.model.JobWithBLOBs;
import com.gdut.imis.campus.model.Student;
import com.gdut.imis.campus.model.Job;
import com.gdut.imis.campus.service.EnterpriseService;
import com.gdut.imis.campus.service.JobService;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerController {


    @Autowired
    private StudentService studentService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private JobService jobService;

    @GetMapping("/manager/{action}")
    public String profile(@PathVariable(name = "action") String action, HttpServletRequest request, Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {

        if ("student".equals(action)) {
            PageHelper.startPage(page, size);
            List<Student> studentlist = studentService.list();
            PaginationDTO paginationDTO = new PaginationDTO();
            paginationDTO.setStudent(studentlist);
            Integer totalCount = studentlist.size();
            paginationDTO.setPagination(totalCount, size, page);
            model.addAttribute("paginationDTO", paginationDTO);
            model.addAttribute("selection", action);
            model.addAttribute("selectionName", "学校用户管理");
        } else if ("enterprise".equals(action)) {
            PageHelper.startPage(page, size);
            List<Enterprise> enterpriselist = enterpriseService.list();
            PaginationDTO paginationDTO = new PaginationDTO();
            paginationDTO.setEnterprise(enterpriselist);
            Integer totalCount = enterpriselist.size();
            paginationDTO.setPagination(totalCount, size, page);
            model.addAttribute("paginationDTO", paginationDTO);
            model.addAttribute("selection", action);
            model.addAttribute("selectionName", "企业用户管理");
        } else if ("question".equals(action)) {
            PageHelper.startPage(page,size);
            List<QuestionDTO> questionlist= questionService.list();
            PaginationDTO paginationDTO= new PaginationDTO();
            paginationDTO.setQuestions(questionlist);
            Integer totalCount=questionlist.size();
            paginationDTO.setPagination(totalCount,size,page);
            model.addAttribute("paginationDTO", paginationDTO);
            model.addAttribute("selection", action);
            model.addAttribute("selectionName", "用户分享管理");
        } else if ("job".equals(action)) {
            PageHelper.startPage(page,size);
            List<JobWithBLOBs> joblist= jobService.list();
            PaginationDTO paginationDTO= new PaginationDTO();
            paginationDTO.setJob(joblist);
            Integer totalCount=joblist.size();
            paginationDTO.setPagination(totalCount,size,page);
            model.addAttribute("paginationDTO", paginationDTO);
            model.addAttribute("selection", action);
            model.addAttribute("selectionName", "职位管理");
        }else if("info".equals(action)) {
            model.addAttribute("selection", action);
            model.addAttribute("selectionName", "详细信息");
        }
        return "profileManager";
    }

    @GetMapping("/setStatus/{id}")
    public String setStatus(@PathVariable(name = "id") String id,
                            @RequestParam(name="status") String status,
                            HttpServletRequest request,
                            Model model) {

        Student stu = new Student();
        stu.setAccountId(id);
        int temp = Integer.parseInt(status);
        if (temp == 1) {
            stu.setStatus(0);
        } else {
            stu.setStatus(1);
        }
        studentService.createOrUpdate(stu);
        return "redirect:/manager/student";
    }

    /**
     * 管理员删除某一分享内容
     * @param id
     * @return
     */
    @GetMapping("/manDelQuestion/{id}")
    public String deleteQuestion(@PathVariable(name="id")Integer id,HttpServletRequest request){
        questionService.delete(id);
        return"redirect:/manager/question";
    }

    /**
     * 管理员删除某一分享内容
     * @param id
     * @return
     */
    @GetMapping("/manDelJob/{id}")
    public String deletejob(@PathVariable(name="id")Integer id,HttpServletRequest request){
        jobService.delete(id);
        return"redirect:/manager/job";
    }


}
