package com.ct.biz.dao.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Driving license mysql dao.
 *
 * @author chen.cheng
 */
@Mapper
@Repository
public interface DrivingLicenseMysqlDao {

    /**
     * Count driver license integer.
     *
     * @param ds the ds
     * @return the integer
     * @author chen.cheng
     */
    Integer countDriverLicense(@Param("ds") String ds);

}