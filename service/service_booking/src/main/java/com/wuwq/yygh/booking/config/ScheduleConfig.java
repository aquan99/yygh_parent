package com.wuwq.yygh.booking.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuwq.yygh.booking.service.BookingRecordService;
import com.wuwq.yygh.common.util.DateUtils;
import com.wuwq.yygh.model.booking.BookingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.booking.config
 * @ClassName:AutoExpireConfig
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-05-22 20:25
 * @Version: 1.0
 */
@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Autowired
    private BookingRecordService bookingRecordService;

    @Scheduled(cron = "${yygh.schedule.expireCron}")
    private void autoExpire(){
        QueryWrapper<BookingRecord> recordQueryWrapper = new QueryWrapper<>();
        recordQueryWrapper.eq("status","0");
        String tomorrow = DateUtils.getDateStr(DateUtils.getAfterDate(1));
        recordQueryWrapper.lt("visitTimeEnd",tomorrow);
        List<BookingRecord> expireRecords = bookingRecordService.list(recordQueryWrapper);
        for (BookingRecord expireRecord : expireRecords) {
            expireRecord.setStatus("3");
        }
        bookingRecordService.updateBatchById(expireRecords);
    }

}
