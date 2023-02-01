package com.wuwq.yygh.hosp.service.impl;

import com.alibaba.nacos.api.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.common.util.OssUtils;
import com.wuwq.yygh.hosp.mapper.DoctorMapper;
import com.wuwq.yygh.hosp.mapper.HospitalMapper;
import com.wuwq.yygh.hosp.service.DoctorService;
import com.wuwq.yygh.model.hosp.Doctor;
import com.wuwq.yygh.model.hosp.Hospital;
import com.wuwq.yygh.vo.hosp.DoctorQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.service.impl
 * @ClassName:DoctorServiceImpl
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 22:09
 * @Version: 1.0
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {

    @Resource
    private HospitalMapper hospitalMapper;

    @Override
    public Result uploadDoctorPhoto(MultipartFile file) {
        String imageUrl = OssUtils.uploadImage(file);
        if ("fail".equals(imageUrl))
            return Result.fail();
        return Result.ok(imageUrl);
    }

    @Override
    public Result addDoctor(Doctor doctor) {
        try {
            if (save(doctor))
                return Result.ok();
            return Result.fail();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500, "服务器错误");
        }
    }

    @Override
    public Result getPageDoctors(Integer current, Integer size, DoctorQueryVo doctorQueryVo) {

        Page<Doctor> doctorPage = new Page<>(current, size);

        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(!StringUtils.isEmpty(doctorQueryVo.getHospCode()), "hospCode", doctorQueryVo.getHospCode());
        queryWrapper.eq(doctorQueryVo.getDeptId() != null && doctorQueryVo.getDeptId() != 0, "deptId", doctorQueryVo.getDeptId());
        queryWrapper.eq(!StringUtils.isEmpty(doctorQueryVo.getDoctorType()), "doctorType", doctorQueryVo.getDoctorType());
        queryWrapper.like(!StringUtils.isEmpty(doctorQueryVo.getDoctorName()), "doctorName", doctorQueryVo.getDoctorName());

        queryWrapper.orderByAsc(doctorQueryVo.getOrderByDept(),"deptId");

        doctorPage = page(doctorPage, queryWrapper);
        while (doctorPage.getRecords().size() == 0 && current != 1) {
            doctorPage.setCurrent(--current);
            doctorPage = page(doctorPage, queryWrapper);
        }

        return Result.ok(doctorPage);
    }

    @Override
    public Result updateDoctor(Doctor doctor) {
        if (updateById(doctor))
            return Result.ok();
        return Result.fail();
    }

    @Override
    public Result getDoctorNameById(Long doctorId) {
        Doctor doctor = getById(doctorId);
        return Result.ok(doctor.getDoctorName());
    }

    @Override
    public Result getDoctorById(Long doctorId) {
        Doctor doctor = getById(doctorId);
        return Result.ok(doctor);
    }

    @Override
    public Result getHospNameByDoctorId(Long doctorId) {
        Doctor doctor = getById(doctorId);
        String hospCode = doctor.getHospCode();
        Hospital hospital = hospitalMapper.selectOne(new QueryWrapper<Hospital>().eq("hospCode", hospCode));
        return Result.ok(hospital.getHospName());
    }

    @Override
    public Result deleteDoctorId(Long doctorId) {
        if (removeById(doctorId)){
            return Result.ok(true);
        }
        return Result.fail(false);
    }


}
