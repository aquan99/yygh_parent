package com.wuwq.yygh.booking.feign;

import com.wuwq.yygh.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.booking.feign
 * @ClassName:ScheduleFeign
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-05-12 14:29
 * @Version: 1.0
 */
@Component
@FeignClient("service-hosp")
public interface HospFeign {

    @PutMapping("hosp/schedule/increaseBookingCount/{scheduleId}")
    Result increaseBookingCount(@PathVariable Long scheduleId);

    @PutMapping("hosp/schedule/decreaseBookingCount/{scheduleId}")
    Result decreaseBookingCount(@PathVariable Long scheduleId);

    @GetMapping("hosp/doctor/getHospNameByDoctorId/{doctorId}")
    Result getHospNameByDoctorId(@PathVariable Long doctorId);

}
