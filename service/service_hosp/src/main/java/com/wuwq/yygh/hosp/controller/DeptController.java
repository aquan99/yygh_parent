package com.wuwq.yygh.hosp.controller;

import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.hosp.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp.controller
 * @ClassName:DeptController
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-04-19 15:58
 * @Version: 1.0
 */
@Api(tags = "科室接口")
@RestController
@RequestMapping("/hosp/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @ApiOperation(value = "获取科室列表")
    @GetMapping("getDeptList")
    public Result getDeptList(){
        return deptService.getDeptList();
    }

}
