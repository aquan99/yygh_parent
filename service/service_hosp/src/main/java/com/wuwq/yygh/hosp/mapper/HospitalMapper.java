package com.wuwq.yygh.hosp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuwq.yygh.model.hosp.Hospital;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.mapper
 * @ClassName:HospitalMapper
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 21:51
 * @Version: 1.0
 */
@Mapper
public interface HospitalMapper extends BaseMapper<Hospital> {
}
