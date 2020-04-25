package com.gdut.imis.campus.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AlertUtil {
    //前台弹出alert框
    public static void alert( HttpServletResponse response,String msg){

        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            OutputStreamWriter out=new OutputStreamWriter(response.getOutputStream());

            msg=new String(msg.getBytes("UTF-8"));

            out.write("<meta http-equiv='Content-Type' content='text/html';charset='UTF-8'>");
            out.write("<script>");
            out.write("alert('"+msg+"');");
            out.write("</script>");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
