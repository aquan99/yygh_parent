package com.wuwq.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.model.hosp.Doctor;
import com.wuwq.yygh.vo.hosp.DoctorQueryVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.service
 * @ClassName:DoctorService
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 22:09
 * @Version: 1.0
 */
public interface DoctorService extends IService<Doctor> {
    Result uploadDoctorPhoto(MultipartFile file);

    Result addDoctor(Doctor doctor);

    Result getPageDoctors(Integer current, Integer size, DoctorQueryVo doctorQueryVo);


    Result updateDoctor(Doctor doctor);

    Result getDoctorNameById(Long doctorId);

    Result getDoctorById(Long doctorId);

    Result getHospNameByDoctorId(Long doctorId);

    Result deleteDoctorId(Long doctorId);
}
