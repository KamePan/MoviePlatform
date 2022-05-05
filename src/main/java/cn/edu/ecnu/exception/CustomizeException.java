package cn.edu.ecnu.exception;
import cn.edu.ecnu.model.enums.ResultCodeEnum;

public class CustomizeException extends RuntimeException{
    private int code;

    public CustomizeException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    public static CustomizeException fail(ResultCodeEnum resultCodeEnum) {
        return new CustomizeException(resultCodeEnum);
    }

    public int getCode() {
        return code;
    }
}
