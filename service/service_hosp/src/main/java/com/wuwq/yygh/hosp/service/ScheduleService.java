package com.wuwq.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.model.hosp.Schedule;
import com.wuwq.yygh.vo.hosp.ScheduleDay;
import com.wuwq.yygh.vo.hosp.ScheduleQueryVo;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.service
 * @ClassName:ScheduleService
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 22:28
 * @Version: 1.0
 */
public interface ScheduleService extends IService<Schedule> {
    Result getPageDaySchedule(Integer current, Integer size, ScheduleQueryVo scheduleQueryVo);

    Result getScheduleDetail(ScheduleDay scheduleDay);

    Result updateSchedule(Schedule schedule);

    Result increaseBookingCount(Long scheduleId);

    Result decreaseBookingCount(Long scheduleId);
}
