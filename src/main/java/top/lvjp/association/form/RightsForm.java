package top.lvjp.association.form;

import lombok.Data;
import top.lvjp.association.constant.RegexpConstant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RightsForm {

    /**
     * 相关社团
     */
    @NotNull(message = "社团不能为空")
    private Integer associationId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 电话
     */
    @Pattern(regexp = RegexpConstant.PHONE, message = "请输入正确的电话")
    private String phone;

    /**
     * 邮箱
     */
    @Pattern(regexp = RegexpConstant.EMAIL, message = "请输入正确的邮箱")
    private String email;

    /**
     * 维权标题
     */
    private String rightsTitle;

    /**
     * 维权内容
     */
    @Size(min = 1,message = "请填写维权内容")
    private String rightsContent;

}
