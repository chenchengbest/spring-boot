package com.ct.biz.dao.oracle;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Driving license oracle dao.
 *
 * @author chen.cheng
 */
@Mapper
@Repository
public interface DrivingLicenseOracleDao {

    /**
     * Count driver num integer.
     *
     * @return the integer
     * @author chen.cheng
     */
    Integer countDriverNum();

    /**
     * Count yesterday increase integer.
     *
     * @param yesterday the yesterday
     * @return the integer
     * @author chen.cheng
     */
    Integer countYesterdayIncrease(@Param("yesterday") String yesterday);

    /**
     * Count vehcle num integer.
     *
     * @return the integer
     * @author chen.cheng
     */
    Integer countVehcleNum();

    /**
     * Count today increase integer.
     *
     * @param today the today
     * @return the integer
     * @author chen.cheng
     */
    Integer countTodayIncrease(@Param("today") String today);
}