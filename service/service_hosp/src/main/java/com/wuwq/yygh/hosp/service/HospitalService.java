package com.wuwq.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.model.hosp.Hospital;
import com.wuwq.yygh.vo.hosp.HospitalQueryVo;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.service
 * @ClassName:HospitalService
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 21:58
 * @Version: 1.0
 */
public interface HospitalService extends IService<Hospital> {
    Result listHospitals(Integer page, Integer size, HospitalQueryVo hospitalQueryVo);

    Result addHospital(Hospital hospital);

    Result updateHospital(Hospital hospital);

    Result getOrgCodeList();

    Result deleteHosp(String hospCode);
}
