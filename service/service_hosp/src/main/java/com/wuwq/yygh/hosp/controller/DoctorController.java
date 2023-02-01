package com.wuwq.yygh.hosp.controller;

import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.hosp.service.DoctorService;
import com.wuwq.yygh.model.hosp.Doctor;
import com.wuwq.yygh.vo.hosp.DoctorQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.controller
 * @ClassName:DoctorController
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 22:11
 * @Version: 1.0
 */
@Api(tags = "医生接口")
@RestController
@RequestMapping("/hosp/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @ApiOperation(value = "上传医生照片")
    @PostMapping("uploadPhoto")
    public Result uploadPhoto(MultipartFile file){
        return doctorService.uploadDoctorPhoto(file);
    }

    @ApiOperation(value = "新增医生")
    @PostMapping("addDoctor")
    public Result addDoctor(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }

    @ApiOperation(value = "修改医生信息")
    @PutMapping("updateDoctor")
    public Result updateDoctor(@RequestBody Doctor doctor){
        return doctorService.updateDoctor(doctor);
    }

    @ApiOperation(value = "分页查询医生")
    @PostMapping("pageDoctor/{current}/{size}")
    public Result queryPageDoctor(@PathVariable Integer current, @PathVariable Integer size, @RequestBody DoctorQueryVo doctorQueryVo){
        return doctorService.getPageDoctors(current, size, doctorQueryVo);
    }

    @ApiOperation(value = "根据医生id获得医生姓名")
    @GetMapping("getNameById/{doctorId}")
    public Result getNameById(@PathVariable Long doctorId){
        return doctorService.getDoctorNameById(doctorId);
    }

    @ApiOperation(value = "根据医生id获得医生信息")
    @GetMapping("getDoctorById/{doctorId}")
    public Result getDoctorById(@PathVariable Long doctorId){
        return doctorService.getDoctorById(doctorId);
    }

    @ApiOperation(value = "根据医生id获得医院名称")
    @GetMapping("getHospNameByDoctorId/{doctorId}")
    public Result getHospNameByDoctorId(@PathVariable Long doctorId){
        return doctorService.getHospNameByDoctorId(doctorId);
    }

    @ApiOperation(value = "根据医生id删除医生")
    @DeleteMapping("deleteDoctorId/{doctorId}")
    public Result deleteDoctorId(@PathVariable Long doctorId){
        return doctorService.deleteDoctorId(doctorId);
    }

}
