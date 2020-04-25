package com.gdut.imis.campus.utils;

import com.gdut.imis.campus.mapper.StudentMapper;
import com.gdut.imis.campus.model.Student;
import com.gdut.imis.campus.service.StudentService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImportMysql {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询指定目录中电子表格中所有的数据
     * @param file
     * 文件完整路径
     * @return
     */
    public List<Student> getAllByExcel(String file) {
        List<Student> list = new ArrayList<Student>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File(file));
            // 或者rwb.getSheet(0)
            Sheet rs = rwb.getSheet(0);
            // 得到所有的列
            int clos = rs.getColumns();
            // 得到所有的行
            int rows = rs.getRows();
            //第0行为属性名，故从第1行开始读取数据
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    // 第一个是列数，第二个是行数
                    // 默认最左边编号也算一列
                    String accountId = rs.getCell(j++, i).getContents();
                    // 所以这里得j++
                    String password = rs.getCell(j++, i).getContents();
                    String name=rs.getCell(j++, i).getContents();
                    String dept=rs.getCell(j++, i).getContents();
                    String degree=rs.getCell(j++, i).getContents();
                    String email=rs.getCell(j++, i).getContents();
                    String address=rs.getCell(j++, i).getContents();
                    String major=rs.getCell(j++, i).getContents();
                    String phone=rs.getCell(j++, i).getContents();
                    Student stu=new Student();
                    stu.setAccountId(accountId);
                    stu.setPassword(password);
                    stu.setNickname(name);
                    stu.setName(name);
                    stu.setDept(dept);
                    stu.setDegree(degree);
                    stu.setEmail(email);
                    stu.setAddress(address);
                    stu.setMajor(major);
                    stu.setPhone(phone);
                    stu.setGmtCreate(System.currentTimeMillis());
                    stu.setGmtModified(System.currentTimeMillis());
                    stu.setType(0);
                    stu.setStatus(1);
                    list.add(stu);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public boolean downloadfile(){
        try {
            WritableWorkbook wwb = null;
            // 创建可写入的Excel工作簿
            String fileName = "C://Users//Administrator//Desktop//学校用户导入模板.xlsx";
            File file=new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //以fileName为文件名来创建一个Workbook
            wwb = Workbook.createWorkbook(file);
            // 创建工作表
            WritableSheet ws = wwb.createSheet("学校用户信息", 0);

            //要插入到的Excel表格的行号，默认从0开始
            Label label1= new Label(0, 0, "账号");
            Label label2= new Label(1, 0, "密码");
            Label label3= new Label(2, 0, "姓名");
            Label label4= new Label(3, 0, "学院");
            Label label5= new Label(4, 0, "学位");
            Label label6= new Label(5, 0, "邮箱");
            Label label7= new Label(6, 0, "地址");
            Label label8= new Label(7, 0, "专业");
            Label label9= new Label(8, 0, "联系方式");
            ws.addCell(label1);
            ws.addCell(label2);
            ws.addCell(label3);
            ws.addCell(label4);
            ws.addCell(label5);
            ws.addCell(label6);
            ws.addCell(label7);
            ws.addCell(label8);
            ws.addCell(label9);
            //写进文档
            wwb.write();
            // 关闭Excel工作簿对象
            wwb.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
