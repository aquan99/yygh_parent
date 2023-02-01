package com.wuwq.yygh.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuwq.yygh.model.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.user.mapper
 * @ClassName:UserMapper
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-21 11:20
 * @Version: 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
