package com.wuwq.yygh.vo.booking;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wuwq.yygh.model.booking.BookingRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.vo.booking
 * @ClassName:BookingRecordVo
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-05-12 21:41
 * @Version: 1.0
 */
@Data
@ApiModel(description = "预约记录Vo")
public class BookingRecordVo extends BookingRecord {
    @ApiModelProperty(value = "医院名称")
    @TableField("hospName")
    private String hospName;
}
