package com.gdut.imis.campus.service;

import com.gdut.imis.campus.dataobject.QuestionDTO;
import com.gdut.imis.campus.mapper.QuestionExtMapper;
import com.gdut.imis.campus.mapper.QuestionMapper;
import com.gdut.imis.campus.mapper.StudentMapper;
import com.gdut.imis.campus.model.Question;
import com.gdut.imis.campus.model.QuestionExample;
import com.gdut.imis.campus.model.Student;
import com.gdut.imis.campus.model.StudentExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private StudentMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;


    public List<QuestionDTO> list() {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria();
        List<Question> questionlist=questionMapper.selectByExample(questionExample);
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        this.copyProperties(questionlist,questionDTOList);
        return questionDTOList;
    }


    public List<QuestionDTO> listByCondition(String condition) {
        QuestionExample questionExample = new QuestionExample();
        String s="%"+condition+"%";
        questionExample.createCriteria().andTitleLike(s);
        List<Question> questionlist=questionMapper.selectByExample(questionExample);
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        this.copyProperties(questionlist,questionDTOList);
        return questionDTOList;
    }

    //复制属性值
    public void copyProperties(List<Question> questionlist,List<QuestionDTO> questionDTOList){
        for(Question qt:questionlist){
            StudentExample userExample=new StudentExample();
            userExample.createCriteria()
                    .andAccountIdEqualTo(qt.getCreator());
            List<Student> list=userMapper.selectByExample(userExample);
            Student user=list.get(0);
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(qt,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
    }

    public Integer count() {
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria();
        return questionMapper.selectByExample(questionExample).size();
    }

    public List<QuestionDTO> listByUserId(String id) {
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(id);
        List<Question> questionlist=questionMapper.selectByExample(questionExample);
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        for(Question qt:questionlist){
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(qt,questionDTO);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public Integer countByUserId(String id) {
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(id);
        return questionMapper.selectByExample(questionExample).size();
    }

    public QuestionDTO getById(Integer id) {
        Question question=questionMapper.selectByPrimaryKey(id);
        StudentExample userExample=new StudentExample();
        userExample.createCriteria().andAccountIdEqualTo(question.getCreator());
        Student user=userMapper.selectByExample(userExample).get(0);
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(System.currentTimeMillis());
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
        }else{
            //更新
            QuestionExample questionExample=new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(question,questionExample);
        }
    }

    public void incView(Integer id) {
        Question question=new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<Question> listByViewcount(){
        QuestionExample questionExample=new QuestionExample();
        questionExample.setOrderByClause("`view_count` DESC");
        List<Question> listByCount=questionMapper.selectByExample(questionExample);
        if(listByCount.size()>8){
            listByCount=listByCount.subList(0, 7);
        }
        return listByCount;
    }
}
