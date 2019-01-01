package top.lvjp.association.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AssociationForm {

    /**
     * 社团编号
     */
    private String associationId;

    /**
     * 社团名称
     */
    @NotBlank
    private String associationName;

    /**
     * 社团描述
     */
    private String associationDescription;

    /**
     * 社团类别
     */
    @NotNull
    private String associationCategory;

    /**
     * 社团logo的图片的编号
     */
    private Integer pictureId;

    /**
     * 社团logo的图片的地址
     */
    private String picturePath;

    /**
     * 报名状态, 0 不可报名, 1 可以报名
     */
    private Integer applyEnable;

}
