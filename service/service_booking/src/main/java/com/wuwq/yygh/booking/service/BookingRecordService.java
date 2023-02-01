package com.wuwq.yygh.booking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.model.booking.BookingRecord;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.booking.service
 * @ClassName:BookingRecordService
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-23 22:21
 * @Version: 1.0
 */
public interface BookingRecordService extends IService<BookingRecord> {
    Result book(BookingRecord bookingRecord);

    Result pageRecords(Integer current, Integer size, Long registerId);

    Result cancelBooking(String bookingNo);
}
