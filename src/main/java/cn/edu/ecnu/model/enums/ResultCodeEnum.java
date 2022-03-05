package cn.edu.ecnu.model.enums;

/**
 * @author shenhe
 */
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(200, "success"),

    FAIL(500, "系统繁忙。。。"),

    PARAM_ILEEGAL(401, "参数有误"),

    USER_NAME_INVALID(402, "账号输入非法"),

    USER_NAME_EXISTED(403, "账号已存在"),

    USER_NAME_NOT_EXISTED(404, "账号不存在"),

    PASSWORD_NOT_MATCHED(405, "密码错误"),

    TIME_PARSE_ERROR(406, "时间解析错误"),

    START_TIME_AFTER_END_TIME(407, "开始时间晚于结束时间"),

    GOODS_NOT_EXISTED(408, "商品不存在"),

    ACTIVITY_NAME_EXISTED(409, "活动名已存在"),

    ACTIVITY_NOT_EXISTED(410, "活动不存在"),

    ACTIVITY_NOT_START(411, "活动未开始"),

    ACTIVITY_HAS_END(412, "活动已结束"),

    DRAW_CHANCE_NOT_ENOUGH(413, "抽奖次数不足"),

    PRIZE_REVENTORY_EMPTY(414, "奖品已抽完"),

    PRIZE_NOT_HIT(415, "未中奖"),

    Coupon_NOT_EXIST(416, "优惠券不存在");
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
