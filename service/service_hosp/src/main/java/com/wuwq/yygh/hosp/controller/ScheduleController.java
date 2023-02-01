package com.wuwq.yygh.hosp.controller;

import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.hosp.service.ScheduleService;
import com.wuwq.yygh.model.hosp.Schedule;
import com.wuwq.yygh.vo.hosp.ScheduleDay;
import com.wuwq.yygh.vo.hosp.ScheduleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.controller
 * @ClassName:ScheduleController
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 22:31
 * @Version: 1.0
 */
@Api(tags = "排班接口")
@RestController
@RequestMapping("/hosp/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation(value = "分页查询排班_日汇总")
    @PostMapping("pageScheduleDay/{current}/{size}")
    public Result queryPageScheduleDay(@PathVariable Integer current, @PathVariable Integer size, @RequestBody ScheduleQueryVo scheduleQueryVo){
        return scheduleService.getPageDaySchedule(current, size, scheduleQueryVo);
    }

    @ApiOperation(value = "获取排班明细")
    @PostMapping("getScheduleDetail")
    public Result getScheduleDetail(@RequestBody ScheduleDay scheduleDay){
        return scheduleService.getScheduleDetail(scheduleDay);
    }

    @ApiOperation(value = "修改排班信息")
    @PutMapping("updateSchedule")
    public Result updateSchedule(@RequestBody Schedule schedule) {
        return scheduleService.updateSchedule(schedule);
    }

    @ApiOperation(value = "增加预约数量")
    @PutMapping("increaseBookingCount/{scheduleId}")
    public Result increaseBookingCount(@PathVariable Long scheduleId){
        return scheduleService.increaseBookingCount(scheduleId);
    }

    @ApiOperation(value = "减少预约数量")
    @PutMapping("decreaseBookingCount/{scheduleId}")
    public Result decreaseBookingCount(@PathVariable Long scheduleId){
        return scheduleService.decreaseBookingCount(scheduleId);
    }

}
