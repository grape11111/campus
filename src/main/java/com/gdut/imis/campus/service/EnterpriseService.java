package com.gdut.imis.campus.service;

import com.gdut.imis.campus.mapper.EnterpriseMapper;
import com.gdut.imis.campus.model.Enterprise;
import com.gdut.imis.campus.model.EnterpriseExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;


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


}
