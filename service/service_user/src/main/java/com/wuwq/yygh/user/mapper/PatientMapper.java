package com.wuwq.yygh.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuwq.yygh.model.user.Patient;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.user.mapper
 * @ClassName:PatientMapper
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 14:33
 * @Version: 1.0
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
}
