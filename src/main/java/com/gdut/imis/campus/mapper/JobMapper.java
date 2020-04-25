package com.gdut.imis.campus.mapper;

import com.gdut.imis.campus.model.Job;
import com.gdut.imis.campus.model.JobExample;
import com.gdut.imis.campus.model.JobWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    long countByExample(JobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int deleteByExample(JobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int insert(JobWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int insertSelective(JobWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    List<JobWithBLOBs> selectByExampleWithBLOBs(JobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    List<Job> selectByExample(JobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    JobWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int updateByExampleSelective(@Param("record") JobWithBLOBs record, @Param("example") JobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") JobWithBLOBs record, @Param("example") JobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int updateByExample(@Param("record") Job record, @Param("example") JobExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int updateByPrimaryKeySelective(JobWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(JobWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table job
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int updateByPrimaryKey(Job record);
}