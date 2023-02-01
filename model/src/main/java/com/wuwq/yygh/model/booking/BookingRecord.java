package com.wuwq.yygh.model.booking;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.model.booking
 * @ClassName:BookingRecord
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-18 14:01
 * @Version: 1.0
 */
@Data
@ApiModel(description = "预约挂号记录")
@TableName("booking_record")
public class BookingRecord implements Serializable {

    private static final long serialVersionUID = -3831613961303427256L;

    @ApiModelProperty(value = "预约编号")
    @TableId("bookingNo")
    private String bookingNo;

    @ApiModelProperty(value = "用户id")
    @TableField("registerId")
    private Long registerId;

    @ApiModelProperty(value = "病人id")
    @TableField("patientId")
    private Long patientId;

    @ApiModelProperty(value = "病人姓名")
    @TableField("patientName")
    private String patientName;

    @ApiModelProperty(value = "排班id")
    @TableField("scheduleId")
    private Long scheduleId;

    @ApiModelProperty(value = "医生id")
    @TableField("doctorId")
    private Long doctorId;

    @ApiModelProperty(value = "医生姓名")
    @TableField("doctorName")
    private String doctorName;

    @ApiModelProperty(value = "挂号费")
    @TableField("costs")
    private BigDecimal costs;

    @ApiModelProperty(value = "就诊起始时间")
    @TableField("visitTimeStart")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date visitTimeStart;

    @ApiModelProperty(value = "就诊截止时间")
    @TableField("visitTimeEnd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date visitTimeEnd;

    @ApiModelProperty(value = "就诊室")
    @TableField("visitRoom")
    private String visitRoom;

    @ApiModelProperty(value = "0已预约 1已就诊 2取消预约 3已过期")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "就诊时间")
    @TableField("visitTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date visitTime;

}
