package top.lvjp.association.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    /**
     * 用户编号
     */
    @JsonProperty("id")
    private Integer userId;

    /**
     * 用户名
     */
    @JsonProperty("name")
    private String userName;

    /**
     * 用户密码
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPassword;

    /**
     * 用户权限等级
     */
    @JsonProperty("type")
    private Integer userType;

    /**
     * 用户所属社团
     */
    @JsonProperty("association")
    private Integer associationId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
