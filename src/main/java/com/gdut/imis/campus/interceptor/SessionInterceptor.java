package com.gdut.imis.campus.interceptor;

import com.gdut.imis.campus.mapper.EnterpriseMapper;
import com.gdut.imis.campus.mapper.ManagerMapper;
import com.gdut.imis.campus.mapper.StudentMapper;
import com.gdut.imis.campus.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;


@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[]cookies=request.getCookies();
        if (cookies!=null&&cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getValue()!= null) {
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
                    }else if(("managerId").equals(cookie.getName())){
                        ManagerExample managerExample = new ManagerExample();
                        managerExample.createCriteria()
                                .andIdEqualTo(Integer.parseInt(cookie.getValue()));
                        List<Manager>list = managerMapper.selectByExample(managerExample);
                        if (list.size()!=0) {
                            request.getSession().setAttribute("user", list.get(0));
                            request.getSession().setAttribute("type", "1");
                            return true;
                        }
                    }
                }
            }
        }
        //response.sendError(HttpServletResponse.SC_FORBIDDEN,"用户未登录");
        toAlert(response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    //前台弹出alert框
    public void toAlert( HttpServletResponse response){

        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            OutputStreamWriter out=new OutputStreamWriter(response.getOutputStream());

            String msg="请先进行登录！";
            msg=new String(msg.getBytes("UTF-8"));

            out.write("<meta http-equiv='Content-Type' content='text/html';charset='UTF-8'>");
            out.write("<script>");
            out.write("alert('"+msg+"');");
            out.write("top.location.href = '/campus'; ");
            out.write("</script>");
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
