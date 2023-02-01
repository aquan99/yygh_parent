package com.wuwq.yygh.booking.controller;

import com.wuwq.yygh.booking.service.BookingRecordService;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.model.booking.BookingRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.booking.controller
 * @ClassName:BookingRecordController
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-23 22:23
 * @Version: 1.0
 */
@Api(tags = "预约记录接口")
@RestController
@RequestMapping("/booking/bookingRecord")
public class BookingRecordController {

    @Autowired
    private BookingRecordService bookingRecordService;

    @ApiOperation(value = "预约")
    @PostMapping("book")
    public Result book(@RequestBody BookingRecord bookingRecord){
        return bookingRecordService.book(bookingRecord);
    }

    @ApiOperation(value = "预约记录")
    @PostMapping("pageRecords/{current}/{size}/{registerId}")
    public Result pageRecords(@PathVariable Integer current,
                              @PathVariable Integer size,
                              @PathVariable Long registerId){
        return bookingRecordService.pageRecords(current,size,registerId);
    }

    @ApiOperation(value = "取消预约")
    @DeleteMapping("cancelBooking/{bookingNo}")
    public Result cancelBooking(@PathVariable String bookingNo){
        return bookingRecordService.cancelBooking(bookingNo);
    }

}
