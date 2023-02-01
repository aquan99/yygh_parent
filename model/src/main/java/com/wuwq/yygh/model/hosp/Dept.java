package com.wuwq.yygh.model.hosp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.model.hosp
 * @ClassName:Dept
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-18 10:11
 * @Version: 1.0
 */
@Data
@ApiModel(description = "科室")
@TableName("dept")
public class Dept implements Serializable {
    private static final long serialVersionUID = -288092420148330866L;

    @ApiModelProperty(value = "科室id")
    @TableField("deptId")
    private Long deptId;

    @ApiModelProperty(value = "科室名称")
    @TableField("deptName")
    private String deptName;

    @ApiModelProperty(value = "上级科室id")
    @TableField("parentId")
    private Long parentId;

    private List<Dept> children;

}
