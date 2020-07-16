package com.ct.biz.dao.trafficIndex;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Tfc index dao.
 *
 * @author chen.cheng
 */
@Mapper
@Repository
public interface TfcIndexDao {

    Integer countAvtiveVehNum(@Param("areaCode") String areaCode);
}