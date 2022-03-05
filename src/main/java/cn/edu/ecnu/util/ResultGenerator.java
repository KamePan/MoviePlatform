package cn.edu.ecnu.util;

import cn.edu.ecnu.model.enums.ResultCodeEnum;

public class ResultGenerator {
    public static Result genFreeResult(int code, String message, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result genFreeResult(ResultCodeEnum resultCodeEnum, Object data) {
        return ResultGenerator.genFreeResult(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), data);
    }

    public static Result genSuccessResult() {
        return genFreeResult(ResultCodeEnum.SUCCESS, null);
    }

    public static Result genSuccessResult(Object data) {
        return genFreeResult(ResultCodeEnum.SUCCESS, data);
    }


    public static Result genFailResult() {
        return genFreeResult(ResultCodeEnum.FAIL, null);
    }
}
