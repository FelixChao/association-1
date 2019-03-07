package top.lvjp.association.form;

import lombok.Data;
import top.lvjp.association.constant.RegexpConstant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ActivityApplyForm {


    /**
     * 申请活动编号
     */
    @NotNull(message = "报名活动不能为空")
    private Integer activityId;

    /**
     * 学生姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String studentName;

    /**
     * 学号
     */
    @NotNull(message = "学号不能为空")
    @Pattern(regexp = RegexpConstant.STUDENT_NUMBER,message = "请输入正确的学号")
    private String studentNumber;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空")
    private String sex;

    /**
     * 电话
     */
    @Pattern(regexp = RegexpConstant.PHONE,message = "请输入正确的电话号码")
    private String phone;

    /**
     * QQ
     */
    @Pattern(regexp = RegexpConstant.QQ ,message = "请输入正确的QQ")
    private String qq;

    /**
     * 学院
     */
    private String academy;

}
