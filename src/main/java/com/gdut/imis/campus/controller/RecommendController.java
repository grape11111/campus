package com.gdut.imis.campus.controller;

import com.gdut.imis.campus.dataobject.PaginationDTO;
import com.gdut.imis.campus.dataobject.QuestionDTO;
import com.gdut.imis.campus.model.Enterprise;
import com.gdut.imis.campus.model.JobWithBLOBs;
import com.gdut.imis.campus.model.Student;
import com.gdut.imis.campus.service.EnterpriseService;
import com.gdut.imis.campus.service.JobService;
import com.gdut.imis.campus.service.QuestionService;
import com.gdut.imis.campus.service.StudentService;
import com.gdut.imis.campus.utils.RecommendUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Maps;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Target;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RecommendController {


    @Autowired
    private StudentService studentService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private JobService jobService;

    @ResponseBody
    @PostMapping("/profile/recommend")
    public Map<String,Double> recommend(HttpServletRequest request, Model model) throws ParseException {
        String TargetType=request.getParameter("TargetType");
        String TargetJob=request.getParameter("TargetJob");
        String TargetProvince=request.getParameter("TargetProvince");
        String TargetCity=request.getParameter("TargetCity");
        String TargetDistrict=request.getParameter("TargetDistrict");
        Map res=jobService.recommendlist(TargetType,TargetJob,TargetProvince,TargetCity,TargetDistrict);
        if(res.size()==0){
            res.put("ERROR",-1);
            //data.indexOf("ERROR")
        }
        return res;
    }
}
