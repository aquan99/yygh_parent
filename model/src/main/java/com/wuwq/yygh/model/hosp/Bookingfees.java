package com.wuwq.yygh.model.hosp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.model.hosp
 * @ClassName:Bookingfees
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-18 10:04
 * @Version: 1.0
 */

@Data
@ApiModel(description = "挂号费用设置")
@TableName("bookingfees")
public class Bookingfees implements Serializable {

    private static final long serialVersionUID = 6707135817359002618L;
    //医院等级 1一级 2二级 3三级
    @ApiModelProperty(value = "医院等级")
    @TableField("hospLevel")
    private String hospLevel;
    //1普通医师 2副主任医师 3主任医师
    @ApiModelProperty(value = "医生类型")
    @TableField("doctorType")
    private String doctorType;
    //挂号费用
    @ApiModelProperty(value = "挂号费用")
    @TableField("fees")
    private BigDecimal fees;

}
