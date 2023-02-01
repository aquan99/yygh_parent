package com.wuwq.yygh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.common.result.ResultCodeEnum;
import com.wuwq.yygh.common.util.*;
import com.wuwq.yygh.model.user.User;
import com.wuwq.yygh.user.mapper.UserMapper;
import com.wuwq.yygh.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.user.service.impl
 * @ClassName:UserServiceImpl
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-21 13:37
 * @Version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    public Result registerUser(String code, User user) {
        if (!verifyCode(user.getPhone(), code)) {
            return Result.build(null, ResultCodeEnum.CODE_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String cardNo = user.getCardNo();
        queryWrapper.eq("phone", user.getPhone())
                .or()
                .eq("cardNo", cardNo);
        User existUser = getOne(queryWrapper);
        if (existUser != null) {
            return Result.build(null, ResultCodeEnum.USER_EXIST);
        }
        String birthStr = cardNo.substring(6, 14);
        try {
            Date birth = DateUtils.ConvertBirth(birthStr);
            user.setBirth(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setCreateTime(new Date());
        user.setPassword(MD5Utils.encode(user.getPassword()));
        System.out.println(user.getPassword() + user.getPassword().length());
        boolean success = save(user);
        if (success)
            return Result.build(null, ResultCodeEnum.REGISTER_SUCCESS);
        return Result.build(null, ResultCodeEnum.REGISTER_FAIL);
    }

    /**
     * 验证登录是否通过
     *
     * @param phone
     * @param password
     * @return
     */
    @Override
    public Result verify(String phone, String password) {
        User user = getOne(new QueryWrapper<User>().eq("phone", phone).or().eq("cardNo", phone));
        if (user == null)
            return Result.build(false, ResultCodeEnum.USER_NOT_EXIST);
        if (password.length() == 5 && phone.length() == 11) {
            if (password.equals((String) redisUtils.get(phone))) {
                return Result.ok();
            } else {
                return Result.build(false, ResultCodeEnum.CODE_ERROR);
            }
        }
        if (MD5Utils.verify(password, user.getPassword())) {
            user.setPassword(null);
            return Result.ok(user);
        }
        return Result.build(false, ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result updatePassword(Long id, String oldPassword, String newPassword) {
        User user = getById(id);
        if (!MD5Utils.verify(oldPassword, user.getPassword())) {
           return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
        User updateOne = new User();
        updateOne.setId(id);
        updateOne.setPassword(MD5Utils.encode(newPassword));
        updateById(updateOne);
        return Result.ok();
    }

    @Override
    public Result updateHeadImg(Long id, MultipartFile file) {
        String res = OssUtils.uploadImage(file);
        if("fail".equals(res)){
            return Result.build(201,"头像上传失败");
        }
        User user = new User();
        user.setId(id);
        user.setHeadImg(res);
        if (updateById(user)) {
            return Result.ok(getById(id));
        }
        return Result.build(201,"头像修改失败");
    }

    /**
     * 发送手机验证码
     *
     * @param phone
     * @return
     */
    @Override
    public Result sendMsg(String phone) {
        if (redisUtils.get(phone) != null) {
            return Result.build(201, "请勿重复请求");
        }
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            codeBuilder.append(random.nextInt(10));
        }
        String codeStr = codeBuilder.toString();
        try {
            String responseCode = SMSUtils.sendDXMsgCode(phone, codeStr);
            if ("00000".equals(responseCode)) {
                redisUtils.set(phone, codeStr, 5L, TimeUnit.MINUTES);
                return Result.build(null, ResultCodeEnum.SEND_SUCCESS);
            }
            return Result.build(null, ResultCodeEnum.SEND_FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(null, ResultCodeEnum.SEND_FAIL);
        }
    }

    public boolean verifyCode(String phone, String code) {
        String codeStr = (String) redisUtils.get(phone);
        if (code.equals(codeStr)) {
            return true;
        } else {
            return false;
        }
    }
}
