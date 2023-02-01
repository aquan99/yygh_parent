package com.wuwq.yygh.admin.controller;

import com.wuwq.yygh.admin.service.ManagerService;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.model.admin.Manager;
import com.wuwq.yygh.vo.admin.ManagerUpdateVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.admin.controller
 * @ClassName:ManagerController
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 16:24
 * @Version: 1.0
 */
@RestController
@RequestMapping("/admin/manager")
@Api(tags = "管理员接口")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("listManagers")
    @ApiOperation(value = "获取所有管理员")
    public Result listManagers() {
        return managerService.listManagers();
    }

    @PostMapping("addManager")
    @ApiOperation("新增管理员")
    public Result addManager(@RequestBody Manager manager) {
        return managerService.addManager(manager);
    }

    @PutMapping("updateManager")
    @ApiOperation("修改管理员信息")
    public Result updateManager(@RequestBody ManagerUpdateVo managerUpdateVo) {
        return managerService.updateManager(managerUpdateVo);
    }

    @ApiOperation(value = "登录验证")
    @PostMapping("/login")
    public Result doLogin(@ApiParam(value = "账号", required = true) @RequestParam(value = "account") String account,
                          @ApiParam(value = "密码", required = true) @RequestParam(value = "password") String password) {
        return managerService.loginVerify(account,password);
    }

    @ApiOperation(value = "获取token信息")
    @GetMapping("/getInfoByToken")
    public Result getInfoByToken(@ApiParam(value = "token", required = true) @RequestParam(value = "token") String token){
        return managerService.getTokenInfo(token);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public Result doLogout(@ApiParam(value = "token", required = true) @RequestParam(value = "token") String token) {
        return managerService.logout(token);
    }

    @DeleteMapping("deleteManagersByOrgCode/{orgCode}")
    public Result deleteManagersByOrgCode(@PathVariable String orgCode){
        return managerService.deleteByOrgCode(orgCode);
    }


}
