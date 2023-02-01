package com.wuwq.yygh.hosp.service.impl;

import com.alibaba.nacos.api.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.common.result.ResultCodeEnum;
import com.wuwq.yygh.common.util.DateUtils;
import com.wuwq.yygh.hosp.mapper.DoctorMapper;
import com.wuwq.yygh.hosp.mapper.ScheduleMapper;
import com.wuwq.yygh.hosp.service.DoctorService;
import com.wuwq.yygh.hosp.service.ScheduleService;
import com.wuwq.yygh.model.hosp.Doctor;
import com.wuwq.yygh.model.hosp.Schedule;
import com.wuwq.yygh.vo.hosp.ScheduleDay;
import com.wuwq.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.service.impl
 * @ClassName:ScheduleServiceImpl
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 22:29
 * @Version: 1.0
 */
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {

    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private DoctorService doctorService;

    @Override
    public Result getPageDaySchedule(Integer current, Integer size, ScheduleQueryVo scheduleQueryVo) {

        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(scheduleQueryVo.getHospCode()), "hospCode", scheduleQueryVo.getHospCode());
        queryWrapper.eq(scheduleQueryVo.getDeptId() != null && scheduleQueryVo.getDeptId() != 0, "deptId", scheduleQueryVo.getDeptId());
        queryWrapper.like(!StringUtils.isEmpty(scheduleQueryVo.getDoctorName()), "doctorName", scheduleQueryVo.getDoctorName());
        List<Doctor> doctorList = doctorMapper.selectList(queryWrapper);
        List<Long> doctorIdList = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            doctorIdList.add(doctor.getId());
        }
        if (doctorIdList.size() == 0) {
            return Result.build(201, "无医生");
        }
        String workDate = DateUtils.getDateStr(scheduleQueryVo.getWorkDate());
        ScheduleMapper scheduleMapper = getBaseMapper();
        List<ScheduleDay> scheduleDayList = scheduleMapper.getScheduleDay(doctorIdList, workDate);
        Page<ScheduleDay> scheduleDayPage = new Page<>();
        List<ScheduleDay> result = new ArrayList<>();
        int total = scheduleDayList.size();
        scheduleDayPage.setTotal(total);
        int to = current * size - 1;
        if (total <= size) {
            current = 1;
            to = total - 1;
        }
        int from = (current - 1) * size;
        while (to >= total && total > size) {
            to = to - size;
            if (to < total) {
                to = total - 1;
                break;
            }
            from = from - size;
            current--;
            if (to - size < total) {
                to = total - 1;
            }
        }
        for (; from <= to; from++) {
            result.add(scheduleDayList.get(from));
        }
        for (ScheduleDay scheduleDay : result) {
            scheduleDay.setDoctorName((String) doctorService.getDoctorNameById(scheduleDay.getDoctorId()).getData());
        }
        scheduleDayPage.setRecords(result);
        scheduleDayPage.setCurrent(current);
        scheduleDayPage.setSize(size);
        return Result.ok(scheduleDayPage);
    }

    @Override
    public Result getScheduleDetail(ScheduleDay scheduleDay) {
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("doctorId", scheduleDay.getDoctorId());
        queryWrapper.eq("workDate", DateUtils.getDateStr(scheduleDay.getWorkDate()));
        List<Schedule> list = list(queryWrapper);
        return Result.ok(list);
    }

    @Override
    public Result updateSchedule(Schedule schedule) {
        if (updateById(schedule))
            return Result.ok();
        return Result.fail();
    }

    @Override
    public Result increaseBookingCount(Long scheduleId) {
        Schedule schedule = getById(scheduleId);
        if (schedule.getAccess()) {
            Integer scheduleNum = schedule.getScheduleNum();
            Integer bookingCount = schedule.getBookingCount();
            if (scheduleNum - bookingCount > 0) {
                schedule.setBookingCount(bookingCount + 1);
                boolean b = updateById(schedule);
                return Result.build(b, ResultCodeEnum.SUCCESS);
            } else {
                return Result.build(201, "号源不足");
            }
        } else {
            return Result.build(201, "不可预约");
        }
    }

    @Override
    public Result decreaseBookingCount(Long scheduleId) {
        Schedule schedule = getById(scheduleId);
        Integer bookingCount = schedule.getBookingCount();
        schedule.setBookingCount(bookingCount - 1);
        if (bookingCount > 0 && updateById(schedule))
            return Result.ok(true);
        return Result.fail(false);
    }

}
