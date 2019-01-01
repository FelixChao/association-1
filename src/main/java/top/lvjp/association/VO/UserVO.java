package top.lvjp.association.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserVO {

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
     * 用户权限等级
     */
    @JsonProperty("type")
    private String userType;

    /**
     * 用户所属社团
     */
    @JsonProperty("association")
    private String associationName;

}
