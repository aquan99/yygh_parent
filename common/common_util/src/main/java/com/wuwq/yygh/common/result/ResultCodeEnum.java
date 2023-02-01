package com.wuwq.yygh.common.result;

import lombok.Getter;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.common.result
 * @ClassName:ResultCodeEnum
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-21 15:10
 * @Version: 1.0
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201,"失败"),
    PARAM_ERROR(202, "参数不正确"),
    CODE_ERROR(203, "验证码错误"),
    USER_EXIST(204,"用户已存在"),
    PASSWORD_ERROR(205,"密码错误"),
    USER_NOT_EXIST(206,"用户未注册"),
    REGISTER_SUCCESS(208,"注册成功"),
    REGISTER_FAIL(209,"注册失败"),
    SEND_SUCCESS(210,"发送成功"),
    SEND_FAIL(211,"发送失败"),
    ;

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
