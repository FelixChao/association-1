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
    private String associationId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 电话
     */
    @Pattern(regexp = RegexpConstant.PHONE,message = "请输入正确的电话")
    private String phone;

    /**
     * QQ
     */
    @Pattern(regexp = RegexpConstant.QQ,message = "请输入正确的QQ")
    private String qq;

    /**
     * 维权内容
     */
    @Size(min = 1,message = "请填写维权内容")
    private String rightsText;

}
