package com.ct.biz.dao.impala;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * The type Tfc impala dao.
 *
 * @author chen.cheng
 */
@Mapper
@Repository
public interface ITfcImpalaScheduleDAO {
   String queryTfcInterFlowMaxTimeStamp();
}
