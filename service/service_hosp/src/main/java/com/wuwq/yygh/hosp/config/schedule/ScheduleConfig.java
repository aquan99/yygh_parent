package com.wuwq.yygh.hosp.config.schedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuwq.yygh.common.util.DateUtils;
import com.wuwq.yygh.common.util.SMSUtils;
import com.wuwq.yygh.hosp.mapper.BookingFeesMapper;
import com.wuwq.yygh.hosp.service.DoctorService;
import com.wuwq.yygh.hosp.service.HospitalService;
import com.wuwq.yygh.hosp.service.ScheduleService;
import com.wuwq.yygh.model.hosp.Bookingfees;
import com.wuwq.yygh.model.hosp.Doctor;
import com.wuwq.yygh.model.hosp.Hospital;
import com.wuwq.yygh.model.hosp.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.config.schedule
 * @ClassName:ScheduleConfig
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-04-21 13:57
 * @Version: 1.0
 */

@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private ScheduleService scheduleService;
    @Resource
    private BookingFeesMapper bookingFeesMapper;
    @Value("${yygh.schedule.num}")
    private Integer scheduleNum;
    @Value("${yygh.schedule.weekendNum}")
    private Integer weekendNum;

    @Scheduled(cron = "${yygh.schedule.timeCron}")
    private void autoScheduleDaily() {
        Date workDate = DateUtils.getAfterDate(14);
        ArrayList<Map<String, Date>> timeList = new ArrayList<>();
        String[] timeStrs = {"08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00"};
        for (int i = 0; i < timeStrs.length - 1; i++) {
            Map<String, Date> map = new HashMap<>();
            if (i != 4) {
                try {
                    Date startTime = DateUtils.ConvertTime(timeStrs[i]);
                    Date endTime = DateUtils.ConvertTime(timeStrs[i + 1]);
                    map.put("startTime", startTime);
                    map.put("endTime", endTime);
                    timeList.add(map);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        if (DateUtils.isWeekend(workDate)) {
            timeList.remove(timeList.size()-1);
        }
        List<Doctor> doctorList = doctorService.list(new QueryWrapper<Doctor>().orderByAsc("hospCode","deptId"));
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            String hospCode = doctor.getHospCode();
            QueryWrapper<Hospital> queryWrapper = new QueryWrapper<Hospital>().select("hospLevel");
            queryWrapper.eq("hospCode",hospCode);
            Hospital hospital = hospitalService.getOne(queryWrapper);
            String hospLevel = hospital.getHospLevel().substring(0, 1);
            QueryWrapper<Bookingfees> feesQueryWrapper = new QueryWrapper<>();
            feesQueryWrapper.eq("hospLevel",hospLevel).eq("doctorType",doctor.getDoctorType());
            Bookingfees bookingfees = bookingFeesMapper.selectOne(feesQueryWrapper);
            BigDecimal fees = bookingfees.getFees();
            for (Map<String, Date> timeMap : timeList) {
                Schedule schedule = new Schedule();
                schedule.setDoctorId(doctor.getId());
                schedule.setScheduleNum(scheduleNum);
                if (DateUtils.isWeekend(workDate)) {
                    schedule.setScheduleNum(weekendNum);
                }
                schedule.setWorkDate(workDate);
                schedule.setFees(fees);
                schedule.setBeginTime(timeMap.get("startTime"));
                schedule.setEndTime(timeMap.get("endTime"));
                scheduleList.add(schedule);
            }
        }
        boolean success = scheduleService.saveBatch(scheduleList);
        if (success) {
            try {
                SMSUtils.sendDXMsgCode("13219012145", "MRPBCG");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(cron = "${yygh.delSchedule.timeCron}")
    private void autoDelScheduleDaily(){
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("workDate", DateUtils.getDateStr(new Date()));
        boolean remove = scheduleService.remove(queryWrapper);
    }

}
