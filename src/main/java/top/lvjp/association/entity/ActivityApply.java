package top.lvjp.association.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ActivityApply {


    /**
     * 申请编号
     */
    private Integer applyId;

    /**
     * 申请活动编号
     */
    private Integer activityId;

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
     * 学院
     */
    private String academy;

    /**
     * 申请时间
     */
    private Date applyTime;

}