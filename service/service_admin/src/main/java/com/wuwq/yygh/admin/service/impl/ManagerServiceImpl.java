package com.wuwq.yygh.admin.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuwq.yygh.admin.mapper.ManagerMapper;
import com.wuwq.yygh.admin.service.ManagerService;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.common.result.ResultCodeEnum;
import com.wuwq.yygh.common.util.MD5Utils;
import com.wuwq.yygh.common.util.RedisUtils;
import com.wuwq.yygh.model.admin.Manager;
import com.wuwq.yygh.vo.admin.ManagerUpdateVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.admin.service.impl
 * @ClassName:ManagerServiceImpl
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 16:23
 * @Version: 1.0
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {

    @Autowired
    private RedisUtils redisUtils;

    @Value("${manager.token.timeoutM}")
    private Long tokenTimeOutM = 30l;

    @Override
    public Result listManagers() {
        List<Manager> managerList = list(new QueryWrapper<Manager>().ne("org_code", "00000"));
        return Result.ok(managerList);
    }

    @Override
    public Result addManager(Manager manager) {
        Manager managerFound = getOne(new QueryWrapper<Manager>().eq("account", manager.getAccount()));
        if (managerFound != null)
            return Result.build(201, "重复添加");
        manager.setPassword(MD5Utils.encode(manager.getPassword()));
        if (save(manager))
            return Result.ok();
        return Result.build(201, "添加失败");
    }


    @Override
    public Result updateManager(ManagerUpdateVo managerUpdateVo) {
        String newPassword = managerUpdateVo.getNewPassword();
        Manager manager = new Manager();
        if (newPassword != null) {
            QueryWrapper<Manager> queryWrapper = new QueryWrapper<Manager>().eq("account", managerUpdateVo.getAccount());
            String oldPassword = managerUpdateVo.getOldPassword();
            manager = getOne(queryWrapper);
            String password = manager.getPassword();
            if (!MD5Utils.verify(oldPassword, password))
                return Result.build(201, "原密码错误");
            manager.setPassword(MD5Utils.encode(newPassword));
        } else {
            BeanUtils.copyProperties(managerUpdateVo, manager);
        }
        if (updateById(manager))
            return Result.ok();
        return Result.fail();
    }

    @Override
    public Result loginVerify(String account, String password) {
        Manager manager = getOne(new QueryWrapper<Manager>().eq("account", account));
        if (manager == null)
            return Result.build(201, "账号错误");
        if ("0".equals(manager.getStatus()))
            return Result.build(201, "账号已被禁用");
        if (!MD5Utils.verify(password, manager.getPassword()))
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        Map tokenMap = new HashMap<String, Object>();
        tokenMap.put("role",manager.getRole());
        tokenMap.put("orgCode",manager.getOrgCode());
        tokenMap.put("account",account);
        String token = UuidUtils.generateUuid().replace("-","").substring(0,18);
        redisUtils.set(token, tokenMap, tokenTimeOutM, TimeUnit.MINUTES);
        manager.setLastLoginTime(new Date());
        updateById(manager);
        return Result.ok(token);
    }

    @Override
    public Result getTokenInfo(String token) {
        Map tokenMap = (Map) redisUtils.get(token);
        return Result.ok(tokenMap);
    }

    @Override
    public Result logout(String token) {
        redisUtils.remove(token);
        return Result.ok();
    }

    @Override
    public Result deleteByOrgCode(String orgCode) {
        QueryWrapper<Manager> managerQueryWrapper = new QueryWrapper<>();
        managerQueryWrapper.eq("org_code", orgCode);
        List<Manager> list = list(managerQueryWrapper);
        if (list != null && list.size() > 0){
            boolean remove = remove(managerQueryWrapper);
            if (remove)
                return Result.build(true, ResultCodeEnum.SUCCESS);
            return Result.build(false, ResultCodeEnum.FAIL);
        }
        return Result.build(true, ResultCodeEnum.SUCCESS);
    }

}
