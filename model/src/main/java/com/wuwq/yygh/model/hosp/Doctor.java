package com.wuwq.yygh.model.hosp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.model.hosp
 * @ClassName:Doctor
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-18 10:26
 * @Version: 1.0
 */
@Data
@ApiModel(description = "医生表实体类")
@TableName("doctor")
public class Doctor implements Serializable {

    private static final long serialVersionUID = 793656304059576914L;

    @ApiModelProperty(value = "医生id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "医生姓名")
    @TableField("doctorName")
    private String doctorName;

    @ApiModelProperty(value = "性别 0男 1女")
    @TableField("gender")
    private String gender;

    @ApiModelProperty(value = "科室编号")
    @TableField("deptId")
    private Long deptId;

    @ApiModelProperty(value = "医院编号")
    @TableField("hospCode")
    private String hospCode;

    @ApiModelProperty(value = "医生简介")
    @TableField("introduce")
    private String introduce;

    @ApiModelProperty(value = "接诊室")
    @TableField("receptionRoom")
    private String receptionRoom;

    @ApiModelProperty(value = "医生类型 1主治医师 2副主任医师 3主任医师")
    @TableField("doctorType")
    private String doctorType;

    @ApiModelProperty(value = "证件照")
    @TableField("idPhoto")
    private String idPhoto;

}
