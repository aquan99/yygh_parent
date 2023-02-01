package com.wuwq.yygh.model.user;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.model.user
 * @ClassName:User
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-18 11:22
 * @Version: 1.0
 */
@Data
@ApiModel(description = "用户")
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = -4805326038823060814L;

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "性别 0男 1女 2未知")
    @TableField("gender")
    private String gender;

    @ApiModelProperty(value = "出生日期")
    @TableField("birth")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birth;

    @ApiModelProperty(value = "手机号码")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "证件类型 01居民身份证")
    @TableField("cardType")
    private String cardType;

    @ApiModelProperty(value = "证件号")
    @TableField("cardNo")
    private String cardNo;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "头像")
    @TableField("headImg")
    private String headImg;

    @ApiModelProperty(value = "注册时间")
    @TableField("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "是否删除（逻辑）")
    @TableField("isdeleted")
    @TableLogic(value = "0", delval = "1")
    private String isdeleted;




}
