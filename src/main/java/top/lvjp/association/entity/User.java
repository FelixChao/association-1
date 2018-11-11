package top.lvjp.association.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPassword;

    /**
     * 用户权限等级
     */
    private Integer userType;

    /**
     * 用户所属社团
     */
    private Integer associationId;
}
