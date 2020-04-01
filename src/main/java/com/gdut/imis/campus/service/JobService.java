package com.gdut.imis.campus.service;

import com.alibaba.fastjson.JSON;
import com.gdut.imis.campus.mapper.*;
import com.gdut.imis.campus.model.*;
import com.gdut.imis.campus.utils.RecommendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobService {

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private JobExtMapper jobExtMapper;



    public List<JobWithBLOBs> list() {
        JobExample jobExample = new JobExample();
        jobExample.setOrderByClause("`gmt_modify` DESC");
        List<JobWithBLOBs> joblist=jobMapper.selectByExampleWithBLOBs(jobExample);
        return joblist;
    }

    /**
     * 单条件查询，按职位名
     * @param condition
     * @return
     */
    public List<JobWithBLOBs> listByCondition(String condition) {
        JobExample jobExample = new JobExample();
        String s="%"+condition+"%";
        jobExample.createCriteria().andNameLike(s);
        List<JobWithBLOBs> joblist=jobMapper.selectByExampleWithBLOBs(jobExample);
        return joblist;
    }

    /**
     *
     * @param days
     * @param type
     * @param degree
     *
     * @return
     */
    public List<JobWithBLOBs> selectByCondition(int days,String type,String degree) {
        JobExample jobExample = new JobExample();
        JobExample.Criteria createria=jobExample.createCriteria();
        if (days!=0){
            createria.andWorkDaysEqualTo(days);
        }
        if(type!="" && type!=null){
            createria.andTypeEqualTo(type);
        }
        if(degree!="" && degree!=null){
            createria.andDegreeEqualTo(degree);
        }
//        if(name!="" && name!=null){
//            createria.andNameLike("%"+name+"%");
//        }
        List<JobWithBLOBs> joblist=jobMapper.selectByExampleWithBLOBs(jobExample);
        return joblist;
    }

    public List<JobWithBLOBs> selectByName(String name){
        JobExample jobExample = new JobExample();
        JobExample.Criteria createria=jobExample.createCriteria();
        createria.andNameLike("%"+name+"%");
        List<JobWithBLOBs> joblist=jobMapper.selectByExampleWithBLOBs(jobExample);
        return joblist;
    }

    public List<JobWithBLOBs> selectByType(String type){
        JobExample jobExample = new JobExample();
        JobExample.Criteria createria=jobExample.createCriteria();
        if(type!=null && type!="") {
            createria.andTypeEqualTo(type);
        }
        List<JobWithBLOBs> joblist=jobMapper.selectByExampleWithBLOBs(jobExample);
        return joblist;
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

    /**
     *
     * @return
     */

    public List<JobWithBLOBs> listByViewcount(){
        JobExample jobExample=new JobExample();
        jobExample.setOrderByClause("`view_count` DESC");
        List<JobWithBLOBs> listByCount=jobMapper.selectByExampleWithBLOBs(jobExample);
        if(listByCount.size()>8){
            listByCount=listByCount.subList(0, 7);
        }
        return listByCount;
    }



    /**
     * 推荐列表
     */
    public Map<String,Double> recommendlist(String TargetType,String TargetJob,String TargetProvince,String TargetCity,String TargetDistrict) {
        List<JobWithBLOBs> list = this.selectByType(TargetType);
        if (list.size() != 0) {
            Map map = new HashMap<Object, Double>();
            RecommendUtil recommendUtil = new RecommendUtil();
            for (int i = 0; i < list.size(); i++) {
                Double temp = recommendUtil.getCoefficient(list.get(i),TargetJob, TargetProvince, TargetCity, TargetDistrict);
                map.put(JSON.toJSON(list.get(i)).toString(), temp);
            }

            if (map.size() == 0) {
                return map;
            }
            Map res = recommendUtil.sortByValue(map);
            return res;
        }
        return null;
    }
}
