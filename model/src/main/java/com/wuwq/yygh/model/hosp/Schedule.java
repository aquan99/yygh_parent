package com.wuwq.yygh.model.hosp;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @Package:com.wuwq.yygh.model.hosp
 * @ClassName:Schedule
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-18 11:04
 * @Version: 1.0
 */
@Data
@ApiModel(description = "排班")
@TableName("schedule")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 8783187200716792134L;

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "医生id")
    @TableField("doctorId")
    private Long doctorId;

    @ApiModelProperty(value = "排班日期yyyy-MM-dd")
    @TableField("workDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date workDate;

    @ApiModelProperty(value = "排班时段开始时间HH:mm:ss")
    @TableField("beginTime")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;

    @ApiModelProperty(value = "排班时段结束时间HH:mm:ss")
    @TableField("endTime")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "总号源数")
    @TableField("scheduleNum")
    private Integer scheduleNum;

    @ApiModelProperty(value = "已预约数")
    @TableField("bookingCount")
    private Integer bookingCount;

    @ApiModelProperty(value = "挂号费")
    @TableField("fees")
    private BigDecimal fees;

    @ApiModelProperty(value = "是否为编辑状态")
    @TableField("editing")
    private Boolean editing = false;

    @ApiModelProperty(value = "可否就诊")
    @TableField("access")
    private Boolean access;
}
