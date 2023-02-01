package com.wuwq.yygh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.common.result.ResultCodeEnum;
import com.wuwq.yygh.common.util.DateUtils;
import com.wuwq.yygh.model.user.Patient;
import com.wuwq.yygh.model.user.User;
import com.wuwq.yygh.user.mapper.PatientMapper;
import com.wuwq.yygh.user.mapper.UserMapper;
import com.wuwq.yygh.user.service.PatientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.user.service.impl
 * @ClassName:PatientServiceImpl
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 14:36
 * @Version: 1.0
 */

@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {


    @Resource
    private UserMapper userMapper;

    /**
     * 添加就诊人
     * @param patient
     * @return
     */
    @Override
    public Result addNewPatient(Patient patient) {

        Long registerId = patient.getRegisterId();

        User user = userMapper.selectById(registerId);
        if (user == null)
            return Result.build(201,"用户不存在,添加就诊人失败");

        int count = count(new QueryWrapper<Patient>().eq("registerId",registerId));
        System.out.println(count);
        if (count >= 5){
            return Result.build(201,"超过绑卡限制");
        }
        Patient patient1 = getOne(new QueryWrapper<Patient>()
                .eq("registerId", registerId)
                .eq("idCardNo", patient.getIdCardNo()));
        if (patient1 != null)
            return Result.build(201,"您已添加该就诊人,请勿重复添加");

        try {
            Date birth = DateUtils.ConvertBirth(patient.getIdCardNo().substring(6, 14));
            patient.setBirth(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (save(patient))
            return Result.build(200,"添加成功");

        return Result.fail();
    }

    @Override
    public Result getPatientsByRegId(Long registerId) {
        List<Patient> patientList = list(new QueryWrapper<Patient>().eq("registerId", registerId));
        return Result.ok(patientList);
    }

    @Override
    public Result updatePatient(Patient patient) {
        if (patient.getId() == null)
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        if (updateById(patient))
            return Result.ok();
        return Result.fail();
    }

    @Override
    public Result getPatientById(Long id) {
        Patient patient = getById(id);
        if (patient == null)
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        return Result.ok(patient);
    }

    @Override
    public Result delPatient(Long id) {
        if(removeById(id)){
            return Result.ok();
        }
        return Result.fail();
    }
}
