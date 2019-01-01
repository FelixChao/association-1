package top.lvjp.association.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class QueryForm {

    private String associationId;

    /**
     * 指定查询的活动
     */
    private Integer activityId;

    private String studentName;

    private String studentNumber;

    private String qq;

    private String phone;

    private String academy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date minTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date maxTime;

    private String sex;

    private String speciality;

    /**
     * 申请部门
     */
    private String department;

    private Integer campus;
}
