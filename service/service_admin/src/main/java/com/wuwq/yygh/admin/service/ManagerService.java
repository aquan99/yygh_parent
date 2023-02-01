package com.wuwq.yygh.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.model.admin.Manager;
import com.wuwq.yygh.vo.admin.ManagerUpdateVo;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.admin.service
 * @ClassName:ManagerService
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 16:22
 * @Version: 1.0
 */
public interface ManagerService extends IService<Manager> {
    Result listManagers();

    Result addManager(Manager manager);

    Result updateManager(ManagerUpdateVo managerUpdateVo);

    Result loginVerify(String account, String password);

    Result getTokenInfo(String token);

    Result logout(String account);

    Result deleteByOrgCode(String orgCode);

}
