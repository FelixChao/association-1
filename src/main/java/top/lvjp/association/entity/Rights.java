package top.lvjp.association.entity;

import lombok.Data;

@Data
public class Rights {

    /**
     * 维权编号
     */
    private String rightsId;

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
     * QQ
     */
    private String qq;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 维权内容
     */
    private String rightsText;

    /**
     * 维权状态, 0:未处理,1:正在处理,2:处理完成
     */
    private String rightsStatus;

}
