package com.wuwq.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.model.hosp.Dept;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.service
 * @ClassName:DeptService
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-04-19 16:05
 * @Version: 1.0
 */
public interface DeptService extends IService<Dept> {
    Result getDeptList();
}
