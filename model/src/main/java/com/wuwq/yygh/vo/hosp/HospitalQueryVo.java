package com.wuwq.yygh.vo.hosp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.vo.hosp
 * @ClassName:HospitalQueryVo
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-23 9:56
 * @Version: 1.0
 */
@Data
@ApiModel(description = "医院查询Vo")
public class HospitalQueryVo implements Serializable {
    private static final long serialVersionUID = 9142311402372472234L;

    @ApiModelProperty(value = "医院地址-省")
    private String addressProvince;

    @ApiModelProperty(value = "医院地址-市")
    private String addressCity;

    @ApiModelProperty(value = "医院地址-县（区）")
    private String addressCounty;

    @ApiModelProperty(value = "医院等级 11一级甲等 12一级乙等 13一级丙等 21二级甲等 22二级乙等 23二级丙等 31三级甲等 32三级乙等 33三级丙等")
    private String hospLevel;

}
