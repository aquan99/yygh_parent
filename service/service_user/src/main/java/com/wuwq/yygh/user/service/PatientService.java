package com.wuwq.yygh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.model.user.Patient;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.user.service
 * @ClassName:PatientService
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 14:35
 * @Version: 1.0
 */
public interface PatientService extends IService<Patient> {
    Result addNewPatient(Patient patient);

    Result getPatientsByRegId(Long registerId);

    Result updatePatient(Patient patient);

    Result getPatientById(Long id);

    Result delPatient(Long id);
}
