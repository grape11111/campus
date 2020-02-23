package com.gdut.imis.campus.mapper;


import com.gdut.imis.campus.model.Job;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobExtMapper {
    int incView(Job record);
}