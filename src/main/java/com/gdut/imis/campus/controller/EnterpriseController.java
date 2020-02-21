package com.gdut.imis.campus.controller;

import com.gdut.imis.campus.model.Enterprise;
import com.gdut.imis.campus.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;


    @PostMapping("/register")
    public String doRegister(
            @RequestParam(value="creditCode",required = false) String creditCode,
            @RequestParam(value="company",required = false) String company,
            @RequestParam(value="hrName",required = false) String hrName,
            @RequestParam(value="password",required = false) String password,
            @RequestParam(value="phone") String phone,
            @RequestParam(value="email") String email,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("company", company);

        Enterprise enterprise = new Enterprise();
        enterprise.setCreditCode(creditCode);
        enterprise.setCompany(company);
        enterprise.setHrName(hrName);
        enterprise.setPassword(password);
        enterprise.setPhone(phone);
        enterprise.setEmail(email);
        enterprise.setGmtCreate(System.currentTimeMillis());
        enterpriseService.createOrUpdate(enterprise);
        return "redirect:/campus";
    }
}
