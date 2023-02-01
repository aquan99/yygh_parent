package com.wuwq.yygh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuwq.yygh.common.result.Result;
import com.wuwq.yygh.model.user.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.user.service
 * @ClassName:UserService
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-21 13:35
 * @Version: 1.0
 */
public interface UserService extends IService<User> {
    Result registerUser(String code, User user);

    Result sendMsg(String phone);

    Result verify(String phone, String password);

    Result updatePassword(Long id, String oldPassword, String newPassword);

    Result updateHeadImg(Long id, MultipartFile file);
}
