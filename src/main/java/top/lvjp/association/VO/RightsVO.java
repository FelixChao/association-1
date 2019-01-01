package top.lvjp.association.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RightsVO {
    /**
     * 维权编号
     */
    @JsonProperty("id")
    private Integer rightsId;

    /**
     * 相关社团
     */
    @JsonProperty("association")
    private String associationName;

    /**
     * 学生姓名
     */
    @JsonProperty("student")
    private String studentName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 维权标题
     */
    @JsonProperty("title")
    private String rightsTitle;

    /**
     * 维权内容
     */
    @JsonProperty("content")
    private String rightsContent;

    /**
     * 维权状态, 0:未处理,1:正在处理,2:处理完成
     */
    @JsonProperty("status")
    private Integer rightsStatus;

    /**
     * 解决办法
     */
    private String solution;
}

