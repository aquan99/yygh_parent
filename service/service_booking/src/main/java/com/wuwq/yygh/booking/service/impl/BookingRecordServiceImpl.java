package com.wuwq.yygh.booking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuwq.yygh.booking.feign.HospFeign;
import com.wuwq.yygh.booking.mapper.BookingRecordMapper;
import com.wuwq.yygh.booking.service.BookingRecordService;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.common.util.DateUtils;
import com.wuwq.yygh.model.booking.BookingRecord;
import com.wuwq.yygh.vo.booking.BookingRecordVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.booking.service.impl
 * @ClassName:BookingRecordServiceImpl
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-23 22:22
 * @Version: 1.0
 */
@Service
@Transactional
public class BookingRecordServiceImpl extends ServiceImpl<BookingRecordMapper, BookingRecord> implements BookingRecordService {


    @Autowired
    private HospFeign hospFeign;

    @Override
    public Result book(BookingRecord bookingRecord) {
        Result canBook = filterBookingCondition(bookingRecord);
        if (canBook.getCode() == 201)
            return canBook;
        UUID uuid = UUID.randomUUID();
        String registerStr = bookingRecord.getRegisterId().toString();
        StringBuilder bookNoStr = new StringBuilder();
        bookNoStr.append(registerStr);
        bookNoStr.append(uuid.toString());
        String string = bookNoStr.toString().replaceAll("-", "");
        String bookingNo = string.substring(0, 20);
        bookingRecord.setBookingNo(bookingNo);
        boolean save = save(bookingRecord);
        if (save) {
            Result result = hospFeign.increaseBookingCount(bookingRecord.getScheduleId());
            Integer code = result.getCode();
            if (code.equals(200) && (Boolean) result.getData() == true) {
                return Result.ok();
            }
        }
        remove(new QueryWrapper<BookingRecord>(bookingRecord));
        return Result.fail();
    }

    @Override
    public Result pageRecords(Integer current, Integer size, Long registerId) {
        Page<BookingRecord> recordPage = new Page<>(current, size);
        QueryWrapper<BookingRecord> recordQueryWrapper = new QueryWrapper<>();
        recordQueryWrapper.eq("registerId", registerId);
        recordQueryWrapper.orderByDesc("visitTimeStart");
        recordPage = page(recordPage, recordQueryWrapper);
        List<BookingRecord> records = recordPage.getRecords();
        List<BookingRecord> recordVoList = new ArrayList<>();
        for (BookingRecord record : records) {
            Result result = hospFeign.getHospNameByDoctorId(record.getDoctorId());
            String hospName = (String) result.getData();
            BookingRecordVo bookingRecordVo = new BookingRecordVo();
            BeanUtils.copyProperties(record, bookingRecordVo);
            bookingRecordVo.setHospName(hospName);
            recordVoList.add(bookingRecordVo);
        }
        recordPage.setRecords(recordVoList);
        return Result.ok(recordPage);
    }

    @Override
    public Result cancelBooking(String bookingNo) {
        QueryWrapper<BookingRecord> recordQueryWrapper = new QueryWrapper<BookingRecord>().eq("bookingNo", bookingNo);
        BookingRecord bookingRecord = getOne(recordQueryWrapper);
        String status = bookingRecord.getStatus();
        Long scheduleId = bookingRecord.getScheduleId();
        Date cancelDate = bookingRecord.getVisitTimeStart();
        Date today = new Date();
        Boolean unableCancel;
        try {
            unableCancel = DateUtils.date1LeDate2(cancelDate, today);
        } catch (ParseException e) {
            e.printStackTrace();
            return Result.fail();
        }
        if (unableCancel){
            return Result.build(201,"取消失败，请至少提前一天取消预约");
        }
        bookingRecord.setStatus("2");
        boolean updateSuccess = update(bookingRecord, recordQueryWrapper);
        if (updateSuccess){
            Result result = hospFeign.decreaseBookingCount(scheduleId);
            if ((Boolean) result.getData())
                return Result.ok();
            bookingRecord.setStatus("1");
            update(bookingRecord, recordQueryWrapper);
        }
        return Result.build(201,"取消预约失败");
    }

    private Result filterBookingCondition(BookingRecord bookingRecord){
        Long patientId = bookingRecord.getPatientId();
        Date visitTimeStart = bookingRecord.getVisitTimeStart();
        QueryWrapper<BookingRecord> recordQueryWrapper = new QueryWrapper<>();
        recordQueryWrapper.eq("patientId", patientId);
        recordQueryWrapper.eq("visitTimeStart", DateUtils.getDateTimeStr(visitTimeStart));
        List<BookingRecord> list = list(recordQueryWrapper);
        if (list != null && list.size() > 0) {
            return Result.build(201, "该患者已预约同时段号源");
        }
        recordQueryWrapper = new QueryWrapper<>();
        recordQueryWrapper.eq("patientId", patientId);
        recordQueryWrapper.eq("doctorId", bookingRecord.getDoctorId());
        recordQueryWrapper.likeRight("visitTimeStart",DateUtils.getDateStr(visitTimeStart));
        list = list(recordQueryWrapper);
        if (list != null && list.size() > 0) {
            return Result.build(201, "该患者已预约该医生当天号源");
        }
        return Result.ok();
    }
}
