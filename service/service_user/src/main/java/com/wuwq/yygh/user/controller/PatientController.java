package com.wuwq.yygh.user.controller;

import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.model.user.Patient;
import com.wuwq.yygh.user.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.user.controller
 * @ClassName:PatientController
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 14:30
 * @Version: 1.0
 */

@RestController
@RequestMapping("/user/patient")
@Api(tags = "就诊人接口")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @ApiOperation(value = "添加就诊人")
    @PostMapping("/addPatient")
    public Result addPatient(@RequestBody Patient patient) {
        return patientService.addNewPatient(patient);
    }

    @ApiOperation(value = "获取就诊人列表")
    @PostMapping("/getPatientsByRegId/{registerId}")
    public Result getPatientsByRegId(@ApiParam(name = "registerId", value = "注册人id（用户id）", required = true)
                                         @PathVariable Long registerId) {
        return patientService.getPatientsByRegId(registerId);
    }

    @ApiOperation(value = "获取就诊人信息")
    @PostMapping("/getPatientById/{id}")
    public Result getPatientById(@ApiParam(name = "id", value = "就诊人id", required = true)
                                     @PathVariable Long id) {
        return patientService.getPatientById(id);
    }


    @ApiOperation(value = "修改就诊人信息")
    @PostMapping("/upadtePatient")
    public Result upadtePatient(@RequestBody Patient patient){
        return patientService.updatePatient(patient);
    }

    @ApiOperation(value = "删除就诊人信息")
    @DeleteMapping("/delPatient/{id}")
    public Result delPatient(@PathVariable Long id){
        return patientService.delPatient(id);
    }

}
