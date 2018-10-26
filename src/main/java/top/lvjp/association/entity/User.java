package top.lvjp.association.entity;

import lombok.Data;

@Data
public class User {

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户权限等级
     */
    private Integer userType;

}
