package com.gdut.imis.campus.mapper;

import com.gdut.imis.campus.model.Enterprise;
import com.gdut.imis.campus.model.EnterpriseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnterpriseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    long countByExample(EnterpriseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int deleteByExample(EnterpriseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int insert(Enterprise record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int insertSelective(Enterprise record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    List<Enterprise> selectByExample(EnterpriseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    Enterprise selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int updateByExampleSelective(@Param("record") Enterprise record, @Param("example") EnterpriseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int updateByExample(@Param("record") Enterprise record, @Param("example") EnterpriseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int updateByPrimaryKeySelective(Enterprise record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Sat Apr 25 13:54:02 CST 2020
     */
    int updateByPrimaryKey(Enterprise record);
}