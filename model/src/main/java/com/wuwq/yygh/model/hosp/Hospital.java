package com.wuwq.yygh.model.hosp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.model.hosp
 * @ClassName:Hospital
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-18 10:52
 * @Version: 1.0
 */
@Data
@ApiModel(description = "医院信息")
@TableName("hospital")
public class Hospital implements Serializable {

    private static final long serialVersionUID = -4088671172042896387L;

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "医院名称")
    @TableField("hospName")
    private String hospName;

    @ApiModelProperty(value = "医院编号")
    @TableField("hospCode")
    private String hospCode;

    @ApiModelProperty(value = "医院地址-省")
    @TableField("addressProvince")
    private String addressProvince;

    @ApiModelProperty(value = "医院地址-市")
    @TableField("addressCity")
    private String addressCity;

    @ApiModelProperty(value = "医院地址-县（区）")
    @TableField("addressCounty")
    private String addressCounty;

    @ApiModelProperty(value = "医院地址-具体地址")
    @TableField("addressDetail")
    private String addressDetail;

    @ApiModelProperty(value = "医院电话")
    @TableField("hospPhone")
    private String hospPhone;

    @ApiModelProperty(value = "医院等级 11一级甲等 12一级乙等 13一级丙等 21二级甲等 22二级乙等 23二级丙等 31三级甲等 32三级乙等 33三级丙等")
    @TableField("hospLevel")
    private String hospLevel;



}
