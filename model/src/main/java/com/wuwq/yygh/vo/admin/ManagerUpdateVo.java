package com.wuwq.yygh.vo.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.vo.admin
 * @ClassName:ManagerUpdateVo
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 17:11
 * @Version: 1.0
 */
@ApiModel(description = "管理员修改信息Vo")
@Data
public class ManagerUpdateVo implements Serializable {

    private static final long serialVersionUID = -1513724760089371783L;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "旧密码")
    private String oldPassword;

    @ApiModelProperty(value = "新密码")
    private String newPassword;

    @ApiModelProperty(value = "机构代码")
    private String orgCode;

    @ApiModelProperty(value = "状态 1启用 0禁用")
    private String status;

}
