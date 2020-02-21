package com.gdut.imis.campus.interceptor;

import com.gdut.imis.campus.mapper.EnterpriseMapper;
import com.gdut.imis.campus.mapper.StudentMapper;
import com.gdut.imis.campus.model.Enterprise;
import com.gdut.imis.campus.model.EnterpriseExample;
import com.gdut.imis.campus.model.Student;
import com.gdut.imis.campus.model.StudentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[]cookies=request.getCookies();
        if (cookies!=null&&cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName() != null) {
                    if (("accountId").equals(cookie.getName())) {
                        StudentExample studentExample = new StudentExample();
                        studentExample.createCriteria()
                                .andAccountIdEqualTo(cookie.getValue());
                        List<Student> list=studentMapper.selectByExample(studentExample);
                        if (list.size()!=0 && list.get(0).getStatus()==1) {
                            request.getSession().setAttribute("user",list.get(0));
                            request.getSession().setAttribute("type", "0");
                            return true;
                        }
                    }else if(("enterpriseId").equals(cookie.getName())) {
                        EnterpriseExample enterpriseExample = new EnterpriseExample();
                        enterpriseExample.createCriteria()
                                .andIdEqualTo(Integer.parseInt(cookie.getValue()));
                        List<Enterprise> list = enterpriseMapper.selectByExample(enterpriseExample);
                        if (list.size() != 0 && list.get(0).getStatus()==1) {
                            request.getSession().setAttribute("user", list.get(0));
                            request.getSession().setAttribute("type", "2");
                            return true;
                        }
                    }
                }
            }
        }
        response.addCookie(new Cookie("error", "用户未登录"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
