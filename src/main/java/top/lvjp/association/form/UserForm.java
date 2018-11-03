package top.lvjp.association.form;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserForm {

    /**
     * 用户名
     */
    @Size(min = 1,message = "用户名不能为空")
    private String userName;

    /**
     * 用户密码
     */
    @Pattern(regexp = "^\\w{4,16}$",message = "请确保密码长度为4-16位数字或字母")
    private String userPassword;

    /**
     * 用户权限等级
     */
    private Integer userType;
}
