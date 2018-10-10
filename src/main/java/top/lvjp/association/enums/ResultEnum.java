package top.lvjp.association.enums;

public enum ResultEnum {

    UNKNOW_ERROR(-1,"未知异常"),
    SUCCESS(1,"成功"),
    LOGIN_INFO_ERROR(0,"登录失败,用户名或密码错误"),
    VALIDATE_CODE_ERROR(0,"验证码错误"),
    RESULT_IS_NULL(0,"查询结果不存在"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
