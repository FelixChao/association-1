package top.lvjp.association.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Rights {

    /**
     * 维权编号
     */
    private Integer rightsId;

    /**
     * 相关社团
     */
    private String associationId;

    /**
     * 学生姓名
     */
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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 维权标题
     */
    private String rightsTitle;

    /**
     * 维权内容
     */
    private String rightsContent;

    /**
     * 维权状态, 0:未处理,1:正在处理,2:处理完成
     */
    private Integer rightsStatus;

    private String solution;
}
