package com.wuwq.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.hosp.mapper.DeptMapper;
import com.wuwq.yygh.hosp.service.DeptService;
import com.wuwq.yygh.model.hosp.Dept;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.service.impl
 * @ClassName:DeptServiceImpl
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-04-19 16:06
 * @Version: 1.0
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Override
    public Result getDeptList() {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("deptId","deptName","parentId");
        List<Dept> list = list(queryWrapper);
        List<Dept> parentDepts = new ArrayList<>();
        List<Dept> resultList = new ArrayList<>();
        for (Dept dept : list) {
            if (dept.getParentId() == 0)
                parentDepts.add(dept);
        }
        for (Dept pdept : parentDepts) {
            Long deptId = pdept.getDeptId();
            List<Dept> children = new ArrayList<>();
            for (Dept dept : list) {
                if (deptId.equals(dept.getParentId()))
                    children.add(dept);
            }
            pdept.setChildren(children);
            resultList.add(pdept);
        }
        return Result.ok(resultList);
    }
}
