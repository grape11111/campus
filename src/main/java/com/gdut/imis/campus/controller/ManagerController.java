package com.gdut.imis.campus.controller;

import com.alibaba.fastjson.JSONObject;
import com.gdut.imis.campus.dataobject.PaginationDTO;
import com.gdut.imis.campus.dataobject.QuestionDTO;
import com.gdut.imis.campus.mapper.ManagerMapper;
import com.gdut.imis.campus.model.*;
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

    @Autowired
    private ManagerMapper managerMapper;

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
            //总数要重新计算
            Integer totalCount = studentService.countAll();
            paginationDTO.setPagination(totalCount, size, page);
            model.addAttribute("paginationDTO", paginationDTO);
            model.addAttribute("selection", action);
            model.addAttribute("selectionName", "学校用户管理");
        } else if ("enterprise".equals(action)) {
            PageHelper.startPage(page, size);
            List<Enterprise> enterpriselist = enterpriseService.list();
            PaginationDTO paginationDTO = new PaginationDTO();
            paginationDTO.setEnterprise(enterpriselist);
            Integer totalCount = enterpriseService.countAll();
            paginationDTO.setPagination(totalCount, size, page);
            model.addAttribute("paginationDTO", paginationDTO);
            model.addAttribute("selection", action);
            model.addAttribute("selectionName", "企业用户管理");
            model.addAttribute("status", "all");
        } else if ("question".equals(action)) {
            PageHelper.startPage(page,size);
            List<QuestionDTO> questionlist= questionService.list();
            PaginationDTO paginationDTO= new PaginationDTO();
            paginationDTO.setQuestions(questionlist);
            Integer totalCount=questionService.countAll();
            paginationDTO.setPagination(totalCount,size,page);
            model.addAttribute("paginationDTO", paginationDTO);
            model.addAttribute("selection", action);
            model.addAttribute("selectionName", "用户分享管理");
        } else if ("job".equals(action)) {
            PageHelper.startPage(page,size);
            List<JobWithBLOBs> joblist= jobService.list();
            PaginationDTO paginationDTO= new PaginationDTO();
            paginationDTO.setJob(joblist);
            Integer totalCount=jobService.countAll();
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


    /**
     * 修改管理员信息
     * @param request
     * @param model
     * @return
     * @throws ParseException
     */

    @PostMapping("/manager/info")
    public String editInfo(HttpServletRequest request, Model model) throws ParseException {
        Manager man = (Manager) request.getSession().getAttribute("user");
        man.setmName(request.getParameter("mName"));
        man.setmPhone(request.getParameter("mPhone"));
        man.setmEmail(request.getParameter("mEmail"));
        man.setmAddress(request.getParameter("mAddress"));
        //更新--按账号更新
        ManagerExample managerExample=new ManagerExample();
        managerExample.createCriteria()
                .andIdEqualTo(man.getId());
        managerMapper.updateByExampleSelective(man,managerExample);
        List<Manager> list=managerMapper.selectByExample(managerExample);
        Manager newMan = list.get(0);
        if (newMan != null) {
            request.getSession().setAttribute("user", newMan);
        } else {
            model.addAttribute("error","修改失败！");
            return "error";
        }
        return "redirect:/manager/info";
    }



    @GetMapping("/manager/enterprise/{action}")
    public String selectEnterprise(@PathVariable(name = "action") String action, HttpServletRequest request, Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        PageHelper.startPage(page, size);
        List<Enterprise> enterpriselist=new ArrayList<>();
        Integer totalCount=0;
        if ("all".equals(action)) {
            enterpriselist = enterpriseService.list();
            totalCount=enterpriseService.countAll();
            model.addAttribute("status", "all");
        }else if("uncheck".equals(action)){
            enterpriselist = enterpriseService.selectByStatus(0);
            totalCount=enterpriseService.countByStatus(0);
            model.addAttribute("status", "uncheck");
        }else if("nopass".equals(action)){
            enterpriselist = enterpriseService.selectByStatus(2);
            totalCount=enterpriseService.countByStatus(2);
            model.addAttribute("status", "nopass");
        }
            PaginationDTO paginationDTO = new PaginationDTO();
            paginationDTO.setEnterprise(enterpriselist);
            paginationDTO.setPagination(totalCount, size, page);
            model.addAttribute("paginationDTO", paginationDTO);
            model.addAttribute("selection", "enterprise");
            model.addAttribute("selectionName", "企业用户管理");
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

    @GetMapping("/setEStatus/{id}")
    public String setEStatus(@PathVariable(name = "id") String id,
                            @RequestParam(name="status") String status,
                            HttpServletRequest request,
                            Model model) {

        Enterprise ent = new Enterprise();
        ent.setId(Integer.parseInt(id));
        int temp = Integer.parseInt(status);
        if(temp==0){
            //同时删除企业发布的职位
            List<JobWithBLOBs> joblist= jobService.listByEntId(Integer.parseInt(id));
            for(JobWithBLOBs job:joblist){
                jobService.delete(job.getId());
            }
            // 删除企业
            enterpriseService.delete(Integer.parseInt(id));
        }else {
            ent.setStatus(temp);
            enterpriseService.createOrUpdate(ent);
        }
        return "redirect:/manager/enterprise";
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
     * 管理员删除某一职位
     * @param id
     * @return
     */
    @GetMapping("/manDelJob/{id}")
    public String deletejob(@PathVariable(name="id")Integer id,HttpServletRequest request){
        jobService.delete(id);
        return"redirect:/manager/job";
    }



}
