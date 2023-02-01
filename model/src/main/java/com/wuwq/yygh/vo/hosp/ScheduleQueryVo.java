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
 * @ClassName:ScheduleQueryVo
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-04-24 21:11
 * @Version: 1.0
 */
@Data
@ApiModel(description = "排班查询Vo")
public class ScheduleQueryVo implements Serializable {
    private static final long serialVersionUID = -5562193944038509610L;

    @ApiModelProperty(value = "医生姓名")
    private String doctorName;

    @ApiModelProperty(value = "排班日期yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date workDate;

    @ApiModelProperty(value = "科室编号")
    private Long deptId;

    @ApiModelProperty(value = "医院编号")
    private String hospCode;


}
