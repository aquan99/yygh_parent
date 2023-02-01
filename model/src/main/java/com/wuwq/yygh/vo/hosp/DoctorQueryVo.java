package com.wuwq.yygh.vo.hosp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.vo.hosp
 * @ClassName:DoctorQueryVo
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-04-18 15:33
 * @Version: 1.0
 */
@Data
@ApiModel(description = "医生查询Vo")
public class DoctorQueryVo implements Serializable {
    private static final long serialVersionUID = -5021774532521288854L;

    @ApiModelProperty(value = "科室编号")
    private Long deptId;

    @ApiModelProperty(value = "医院编号")
    private String hospCode;

    @ApiModelProperty(value = "医生类型 1普通医师 2副主任医师 3主任医师")
    private String doctorType;

    @ApiModelProperty(value = "医生姓名")
    private String doctorName;

    @ApiModelProperty(value = "是否根据科室排序")
    private Boolean orderByDept;

}
