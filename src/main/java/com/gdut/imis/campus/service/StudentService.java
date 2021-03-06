package com.gdut.imis.campus.service;

import com.gdut.imis.campus.mapper.StudentMapper;
import com.gdut.imis.campus.model.Student;
import com.gdut.imis.campus.model.StudentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    //更新学生用户
    public Student createOrUpdate(Student student) {
        if(student.getAccountId()==null){
            //创建
            return null;
        }else{
            //更新--按账号更新
            StudentExample studentExample=new StudentExample();
            studentExample.createCriteria()
                    .andAccountIdEqualTo(student.getAccountId());
            studentMapper.updateByExampleSelective(student,studentExample);
            List<Student> list=studentMapper.selectByExample(studentExample);
            return list.get(0);
        }
    }


    public List<Student> list() {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria();
        List<Student> list=studentMapper.selectByExample(studentExample);
        return list;
    }

    public int countAll(){
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria();
        List<Student> list=studentMapper.selectByExample(studentExample);
        return list.size();
    }

    //插入学生用户
    public void insetStudent(Student stu){
        studentMapper.insert(stu);
    }

    public Student findByAccountId(String accountId){
        StudentExample stu=new StudentExample();
        stu.createCriteria().andAccountIdEqualTo(accountId);
        List<Student>list=studentMapper.selectByExample(stu);
        if(list.size()!=0) {
            return list.get(0);
        }
        return null;
    }

}
