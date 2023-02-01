package com.wuwq.yygh.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.model.user
 * @ClassName:Patient
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-18 13:40
 * @Version: 1.0
 */
@Data
@ApiModel(description = "就诊人信息")
@TableName("patient")
public class Patient implements Serializable {

    private static final long serialVersionUID = -501614384698297696L;

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "注册用户id")
    @TableField("registerId")
    private Long registerId;

    @ApiModelProperty(value = "就诊人姓名")
    @TableField("patientName")
    private String patientName;

    @ApiModelProperty(value = "身份证号")
    @TableField("idCardNo")
    private String idCardNo;

    @ApiModelProperty(value = "出生日期")
    @TableField("birth")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birth;

    @ApiModelProperty(value = "性别0男 1女 2不详")
    @TableField("gender")
    private String gender;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "居住地址—省")
    @TableField("addressProvince")
    private String addressProvince;

    @ApiModelProperty(value = "居住地址-市")
    @TableField("addressCity")
    private String addressCity;

    @ApiModelProperty(value = "居住地址-县（区）")
    @TableField("addressCounty")
    private String addressCounty;

    @ApiModelProperty(value = "居住地址-具体位置")
    @TableField("addressDetail")
    private String addressDetail;


    @ApiModelProperty(value = "居住地址-全称")
    @TableField("addressFullName")
    private String addressFullName;

}
