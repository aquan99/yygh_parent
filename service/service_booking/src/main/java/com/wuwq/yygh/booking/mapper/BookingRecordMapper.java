package com.wuwq.yygh.booking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuwq.yygh.model.booking.BookingRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.booking.mapper
 * @ClassName:BookingRecordMapper
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-23 22:17
 * @Version: 1.0
 */

@Mapper
public interface BookingRecordMapper extends BaseMapper<BookingRecord> {
}
