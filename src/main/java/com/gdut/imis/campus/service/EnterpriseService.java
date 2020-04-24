package com.gdut.imis.campus.service;

import com.gdut.imis.campus.mapper.EnterpriseMapper;
import com.gdut.imis.campus.mapper.JobMapper;
import com.gdut.imis.campus.model.Enterprise;
import com.gdut.imis.campus.model.EnterpriseExample;
import com.gdut.imis.campus.model.JobExample;
import com.gdut.imis.campus.model.JobWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private JobMapper jobMapper;


    public Enterprise createOrUpdate(Enterprise enterprise) {
        if(enterprise.getId()==null){
            //创建
            enterpriseMapper.insertSelective(enterprise);
            return enterprise;
        }else{
            //更新
            EnterpriseExample enterpriseExample=new EnterpriseExample();
            enterpriseExample.createCriteria()
                    .andIdEqualTo(enterprise.getId());
            enterpriseMapper.updateByExampleSelective(enterprise,enterpriseExample);
            List<Enterprise> list=enterpriseMapper.selectByExample(enterpriseExample);
            return list.get(0);
        }
    }

    /**
     *
     * @return
     */
    public Enterprise selectByEntId(int enterpriseId){
        Enterprise enterprise=enterpriseMapper.selectByPrimaryKey(enterpriseId);
        return enterprise;
    }

    public List<Enterprise> list() {
        EnterpriseExample enterpriseExample = new EnterpriseExample();
        enterpriseExample.createCriteria();
        List<Enterprise> list=enterpriseMapper.selectByExample(enterpriseExample);
        return list;
    }
    public List<Enterprise> selectByStatus(int status){
        EnterpriseExample enterpriseExample = new EnterpriseExample();
        enterpriseExample.createCriteria().andStatusEqualTo(status);
        List<Enterprise> list=enterpriseMapper.selectByExample(enterpriseExample);
        return list;
    }

    public int countByStatus(int status){
        EnterpriseExample enterpriseExample = new EnterpriseExample();
        enterpriseExample.createCriteria().andStatusEqualTo(status);
        List<Enterprise> list=enterpriseMapper.selectByExample(enterpriseExample);
        return list.size();
    }


    public int countAll(){
        EnterpriseExample enterpriseExample = new EnterpriseExample();
        enterpriseExample.createCriteria();
        List<Enterprise> list=enterpriseMapper.selectByExample(enterpriseExample);
        return list.size();
    }


    public void delete(Integer id){
        enterpriseMapper.deleteByPrimaryKey(id);
    }

}
