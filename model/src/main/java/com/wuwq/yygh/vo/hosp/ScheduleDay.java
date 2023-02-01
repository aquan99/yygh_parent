package com.wuwq.yygh.vo.hosp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.vo.hosp
 * @ClassName:ScheduleDay
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-04-25 9:11
 * @Version: 1.0
 */
@Data
@ApiModel(description = "日排班Vo")
public class ScheduleDay implements Serializable {
    private static final long serialVersionUID = -7453882794166677417L;

    @ApiModelProperty(value = "医生id")
    private Long doctorId;

    @ApiModelProperty(value = "医生姓名")
    private String doctorName;

    @ApiModelProperty(value = "排班日期yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date workDate;

    @ApiModelProperty(value = "日总号源数")
    private Integer scheduleNumDay;

    @ApiModelProperty(value = "日已预约数")
    private Integer bookingCountDay;


}
