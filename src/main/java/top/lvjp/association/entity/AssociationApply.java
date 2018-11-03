package top.lvjp.association.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AssociationApply {


    /**
     * 申请编号
     */
    private Integer applyId;

    /**
     * 申请社团编号
     */
    private Integer associationId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学号
     */
    private String studentNumber;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 电话
     */
    private String phone;

    /**
     * QQ
     */
    private String qq;

    /**
     * 所在校区
     */
    private Integer campus;

    /**
     * 学生所在学院
     */
    private String academy;

    /**
     * 学生专业
     */
    private String speciality;

    /**
     * 申请部门
     */
    private String department;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 申请理由
     */
    private String reason;

}
