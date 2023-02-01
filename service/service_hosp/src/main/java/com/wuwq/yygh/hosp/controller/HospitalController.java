package com.wuwq.yygh.hosp.controller;

import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.hosp.service.HospitalService;
import com.wuwq.yygh.model.hosp.Hospital;
import com.wuwq.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.controller
 * @ClassName:HospitalController
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 22:01
 * @Version: 1.0
 */
@Api(tags = "医院接口")
@RestController
@RequestMapping("/hosp/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/queryPageHospital/{page}/{size}")
    @ApiOperation(value = "分页查询医院列表")
    public Result listHospitals(@PathVariable(name = "page") Integer page,
                                @PathVariable(name = "size") Integer size,
                                @RequestBody HospitalQueryVo hospitalQueryVo){
        return hospitalService.listHospitals(page,size,hospitalQueryVo);
    }

    @PostMapping("/addHospital")
    @ApiOperation(value = "新增医院")
    public Result addHospital(@RequestBody Hospital hospital){
        return hospitalService.addHospital(hospital);
    }

    @PutMapping("/updateHospital")
    @ApiOperation(value = "修改医院")
    public Result updateHospital(@RequestBody Hospital hospital){
        return hospitalService.updateHospital(hospital);
    }

    @GetMapping("getOrgCodeList")
    @ApiOperation(value = "获取机构代码列表")
    public Result getOrgCodeList(){
        return hospitalService.getOrgCodeList();
    }

    @DeleteMapping("/deleteHosp/{hospCode}")
    @ApiOperation(value = "移除医院")
    public Result deleteHosp(@PathVariable String hospCode){
        return hospitalService.deleteHosp(hospCode);
    }


}
