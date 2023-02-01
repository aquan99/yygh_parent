package com.wuwq.yygh.hosp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuwq.yygh.model.hosp.Schedule;
import com.wuwq.yygh.vo.hosp.ScheduleDay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.mapper
 * @ClassName:ScheduleMapper
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 22:26
 * @Version: 1.0
 */
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {

    List<ScheduleDay> getScheduleDay(List<Long> doctorIdList, String workDate);

}
