package com.wuwq.yygh.user.controller;

import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.model.user.User;
import com.wuwq.yygh.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.user.controller
 * @ClassName:UserController
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-21 14:04
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user/user")
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册接口")
    @PostMapping("/register/{code}")
    public Result userRegister(@ApiParam(name = "code", value = "验证码", required = true)@PathVariable String code, @RequestBody User user) {
        return userService.registerUser(code, user);
    }

    @ApiOperation(value = "发送验证码")
    @PostMapping("/sendMsg/{phone}")
    public Result sendMsg(@ApiParam(name = "phone", value = "手机号", required = true) @PathVariable String phone) {
        return userService.sendMsg(phone);
    }

    @ApiOperation(value = "登录接口")
    @PostMapping("/doLogin/{phone}/{password}")
    @ApiParam()
    public Result doLogin(@ApiParam(name = "phone", value = "手机号或身份证号", required = true) @PathVariable String phone,
                          @ApiParam(name = "password", value = "密码或验证码", required = true) @PathVariable String password) {
        return userService.verify(phone, password);
    }

    @ApiOperation(value = "密码修改接口")
    @PostMapping("/updatePassword/{id}/{oldPassword}/{newPassword}")
    @ApiParam()
    public Result updatePassword(@ApiParam(name = "id", value = "userId", required = true) @PathVariable Long id,
                          @ApiParam(name = "oldPassword", value = "旧密码", required = true) @PathVariable String oldPassword,
                          @ApiParam(name = "newPassword", value = "新密码", required = true) @PathVariable String newPassword) {
        return userService.updatePassword(id, oldPassword, newPassword);
    }

    @ApiOperation(value = "更改用户头像")
    @PostMapping("updateHeadImg/{id}")
    public Result updateHeadImg(@ApiParam(name = "id", value = "userId", required = true) @PathVariable Long id, MultipartFile file){
        return userService.updateHeadImg(id, file);
    }

}
