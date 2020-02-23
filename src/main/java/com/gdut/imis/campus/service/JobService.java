package com.gdut.imis.campus.service;

import com.gdut.imis.campus.mapper.*;
import com.gdut.imis.campus.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private JobExtMapper jobExtMapper;



    public List<JobWithBLOBs> list() {
        JobExample jobExample = new JobExample();
        jobExample.createCriteria();
        List<JobWithBLOBs> joblist=jobMapper.selectByExampleWithBLOBs(jobExample);
        return joblist;
    }

    /**
     * 条件查询
     * @param condition
     * @return
     */
    public List<JobWithBLOBs> listByCondition(String condition) {
        JobExample jobExample = new JobExample();
        String s="%"+condition+"%";
//        jobExample.createCriteria().andTitleLike(s);
//        List<Question> questionlist=questionMapper.selectByExample(questionExample);
//        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
//        this.copyProperties(questionlist,questionDTOList);
        return null;
    }


    public Integer count() {
        JobExample jobExample=new JobExample();
        jobExample.createCriteria();
        return jobMapper.selectByExample(jobExample).size();
    }

    /**
     *
     * @param id--企业id
     * @return 该企业已发布的职位
     */
    public List<JobWithBLOBs> listByEntId(int id) {
        JobExample jobExample=new JobExample();
        jobExample.createCriteria()
                .andEnterpriseIdEqualTo(id);
        List<JobWithBLOBs> joblist=jobMapper.selectByExampleWithBLOBs(jobExample);
        return joblist;
    }

    public Integer countByEntId(int id) {
        JobExample jobExample=new JobExample();
        jobExample.createCriteria()
                .andEnterpriseIdEqualTo(id);
        return jobMapper.selectByExample(jobExample).size();
    }

    /**
     *
     * @param id--职位id
     * @return 职位具体内容
     */
    public JobWithBLOBs getById(Integer id) {
        JobWithBLOBs job=jobMapper.selectByPrimaryKey(id);
        return job;
    }

    public void createOrUpdate(JobWithBLOBs jobWithBLOBs) {
        if(jobWithBLOBs.getId()==null){
            //创建
            jobWithBLOBs.setGmtCreate(System.currentTimeMillis());
            jobWithBLOBs.setGmtModify(System.currentTimeMillis());
            jobWithBLOBs.setViewCount(0);
            jobWithBLOBs.setStatus(1);
            jobMapper.insert(jobWithBLOBs);
        }else{
            //更新
            JobExample jobExample=new JobExample();
            jobExample.createCriteria()
                    .andIdEqualTo(jobWithBLOBs.getId());
            jobMapper.updateByExampleSelective(jobWithBLOBs,jobExample);
        }
    }

    /**
     * 修改阅读数
     * @param id
     */
    public void incView(Integer id) {
        Job job=new Job();
        job.setId(id);
        job.setViewCount(1);
        jobExtMapper.incView(job);
    }

    public void delete(Integer id){
        jobMapper.deleteByPrimaryKey(id);
    }
}
