package com.wuwq.yygh.model.admin;

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
 * @Package:com.wuwq.yygh.model.admin
 * @ClassName:Manager
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-18 14:22
 * @Version: 1.0
 */
@Data
@ApiModel(description = "管理员")
@TableName("manager")
public class Manager implements Serializable {

    private static final long serialVersionUID = 3776418832276227297L;

    @ApiModelProperty(value = "账号")
    @TableId("account")
    private String account;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "角色")
    @TableField("role")
    private String role;

    @ApiModelProperty(value = "机构代码")
    @TableField("org_code")
    private String orgCode;

    @ApiModelProperty(value = "最近登录时间")
    @TableField("lastLoginTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

    @ApiModelProperty(value = "状态 1启用 0禁用")
    @TableField("status")
    private String status;

}
