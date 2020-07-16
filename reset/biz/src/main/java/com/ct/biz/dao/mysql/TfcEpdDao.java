package com.ct.biz.dao.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * The interface Tfc epd dao.
 *
 * @author chen.cheng
 */
@Mapper
@Repository
public interface TfcEpdDao {

    /**
     * Count tfc epd integer.
     *
     * @param ds the ds
     * @return the integer
     * @author chen.cheng
     */
    Integer countTfcEpd(@Param("ds") String ds);

    BigInteger queryMaxTimeStampOfSatur();

}