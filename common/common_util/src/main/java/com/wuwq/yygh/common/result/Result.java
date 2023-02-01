package com.wuwq.yygh.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.common.result
 * @ClassName:Result
 * @Author:Wuwq
 * @Description:统一返回结果
 * @Date: 2022-03-21 15:17
 * @Version: 1.0
 */

@Data
@ApiModel(description = "统一返回结果")
public class Result<T> {

    @ApiModelProperty(value = "返回结果码")
    private Integer code;

    @ApiModelProperty(value = "返回结果描述")
    private String message;

    @ApiModelProperty(value = "返回结果数据")
    private T data;

    public Result() {
    }

    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(data);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }


    public static <T> Result<T> build(Integer code, String message) {
        Result<T> result = build(null);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> ok() {
        return Result.ok(null);
    }

    /**
     * 操作成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> fail() {
        return Result.fail(null);
    }

    /**
     * 操作失败
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(T data) {
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }




    public Result<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    public boolean isOk() {
        if (this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue() || this.getCode().intValue() == ResultCodeEnum.SEND_SUCCESS.getCode().intValue()) {
            return true;
        }
        return false;
    }
}
