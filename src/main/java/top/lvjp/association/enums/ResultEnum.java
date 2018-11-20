package top.lvjp.association.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    UNKNOW_ERROR(-1,"未知异常！"),
    SUCCESS(0,"成功"),
    LOGIN_INFO_ERROR(1,"登录失败,用户名或密码错误！"),
    VALIDATE_CODE_ERROR(2,"验证码错误！"),
    RESULT_IS_NULL(3,"查询结果不存在！"),
    FORM_VALID_ERROR(4,"表单验证失败！"),
    ASSOCIATION_NOT_EXISTS(5,"社团不存在！"),
    ACTIVITY_NOT_EXISTS(6,"活动不存在！"),
    APPLY_IS_FULL(7,"报名人数已满！"),
    APPLY_IS_FINISHED(8,"报名已结束！"),
    NOW_NOT_APPLY(9,"当前不能报名"),
    USER_NOT_EXISTS(10,"用户不存在"),
    USER_IS_EXISTS(11,"用户名已存在"),
    OPERATE_IS_FAIL(12,"操作失败"),
    APPLY_ALREADY_EXISTS(13,"报名信息已存在"),
    NEWS_NOT_EXISTS(14,"新闻不存在"),
    PARAMETERS_IS_NULL(15,"缺少参数"),
    RIGHTS_NOT_SATISFY(16,"权限不足"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
