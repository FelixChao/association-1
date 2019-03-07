package top.lvjp.association.form;

import lombok.Data;
import top.lvjp.association.constant.RegexpConstant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AssociationApplyForm {

    /**
     * 申请社团编号
     */
    @NotNull(message = "报名社团不能为空")
    private String associationId;

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
    @Pattern(regexp = RegexpConstant.QQ,message = "请输入正确的QQ")
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
     * 申请理由
     */
    private String reason;

}
