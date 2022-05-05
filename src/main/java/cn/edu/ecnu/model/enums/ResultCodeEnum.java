package cn.edu.ecnu.model.enums;

/**
 * @author shenhe
 */
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(200, "success"),

    /**
     * 失败
     */
    FAIL(500, "系统繁忙。。。"),

    /**
     * 4xx 公共模块异常
     */
    PARAM_ILLEGAL(401, "参数有误"),

    USERNAME_INVALID(402, "账号输入非法"),

    PASSWORD_INVALID(403, "密码输入非法"),

    USERNAME_EXISTED(404, "账号已存在"),

    USERNAME_NOT_EXISTED(405, "账号不存在"),

    PASSWORD_NOT_MATCHED(406, "密码错误"),

    /**
     * 5xx 预约、咨询模块异常，包括访客、咨询师、督导相关
     */
    CONSULT_HAS_FINISHED(501, "咨询已结束"),

    CONSULT_NOT_EXIST(502, "咨询不存在"),

    CONSULT_TIME_INVALID(503, "咨询时间不合理"),

    /**
     * 6xx 排班、审批、值班模块异常
     */
    ARRANGEMENT_TIME_INVALID(600, "排版时间不合理"),

    /**
     * 7** 查询顾客、咨询师、督导接口调用错误
     */
    NOT_SUPERVISOR(701, "该用户不是督导"),

    NOT_COUNSELOR(702, "该用户不是咨询师"),

    NOT_CUSTOMER(703, "该用户不是顾客"),

    NOT_ADMIN(704, "该用户不是管理员");

    /**
     * 错误码
     */
    private final int code;

    /**
     * 错误信息
     */
    private final String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResultCodeEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
