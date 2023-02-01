package com.wuwq.yygh.hosp.service.impl;

import com.alibaba.nacos.api.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.common.result.ResultCodeEnum;
import com.wuwq.yygh.hosp.feign.AdminFeign;
import com.wuwq.yygh.hosp.mapper.DoctorMapper;
import com.wuwq.yygh.hosp.mapper.HospitalMapper;
import com.wuwq.yygh.hosp.service.HospitalService;
import com.wuwq.yygh.model.hosp.Doctor;
import com.wuwq.yygh.model.hosp.Hospital;
import com.wuwq.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.service.impl
 * @ClassName:HospitalServiceImpl
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 21:59
 * @Version: 1.0
 */
@Service
@Transactional
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital> implements HospitalService {

    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private AdminFeign adminFeign;

    @Override
    public Result listHospitals(Integer page, Integer size, HospitalQueryVo hospitalQueryVo) {

        Page<Hospital> hospitalPage = new Page<>();
        hospitalPage.setCurrent(page).setSize(size);
        QueryWrapper<Hospital> queryWrapper = new QueryWrapper<>();
        if (hospitalQueryVo != null) {
            queryWrapper.eq(!StringUtils.isEmpty(hospitalQueryVo.getAddressProvince()), "addressProvince", hospitalQueryVo.getAddressProvince());
            queryWrapper.eq(!StringUtils.isEmpty(hospitalQueryVo.getAddressCity()), "addressCity", hospitalQueryVo.getAddressCity());
            queryWrapper.eq(!StringUtils.isEmpty(hospitalQueryVo.getAddressCounty()), "addressCounty", hospitalQueryVo.getAddressCounty());
            queryWrapper.eq(!StringUtils.isEmpty(hospitalQueryVo.getHospLevel()), "hospLevel", hospitalQueryVo.getHospLevel());
        }
        hospitalPage = page(hospitalPage, queryWrapper);
        while (hospitalPage.getRecords().size() == 0 && page != 1) {
            hospitalPage.setCurrent(--page);
            hospitalPage = page(hospitalPage, queryWrapper);
        }
        return Result.build(hospitalPage, ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result addHospital(Hospital hospital) {
        Hospital hospital1 = getOne(new QueryWrapper<Hospital>().eq("hospCode", hospital.getHospCode()));
        if (hospital1 != null) {
            return Result.build(201, "医院已存在");
        }
        if (save(hospital)) {
            return Result.ok();
        }
        return Result.fail();
    }

    @Override
    public Result updateHospital(Hospital hospital) {
        if (updateById(hospital))
            return Result.ok();
        return Result.fail();
    }

    @Override
    public Result getOrgCodeList() {
        List<Map<String, Object>> orgList = listMaps(new QueryWrapper<Hospital>().select("hospCode", "hospName").ne("hospCode", ""));
        return Result.ok(orgList);
    }

    @Override
    public Result deleteHosp(String hospCode) {
        try {
            doctorMapper.delete(new QueryWrapper<Doctor>().eq("hospCode", hospCode));
            remove(new QueryWrapper<Hospital>().eq("hospCode", hospCode));
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.fail(false);
        }
        Result result = adminFeign.deleteManagersByHospCode(hospCode);
        if ((Boolean) result.getData()){
            return Result.ok(true);
        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return Result.fail(false);
    }

}
