package top.lvjp.association.enums;

public enum ResultEnum {

    SUCCESS(0,"成功"),
    USER_UPDATE_FAIL(1,"用户更新失败"),
    USER_NOT_EXIST(1,"用户不存在"),
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
