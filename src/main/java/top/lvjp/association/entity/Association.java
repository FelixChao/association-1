package top.lvjp.association.entity;

import lombok.Data;

@Data
public class Association {

    /**
     * 社团编号
     */
    private Integer associationId;

    /**
     * 社团名称
     */
    private String associationName;

    /**
     * 社团描述
     */
    private String associationDescription;

    /**
     * 社团类别
     */
    private String associationCategory;

    /**
     * 社团logo
     */
    private String associationIcon;

    /**
     * 申请人数
     */
    private Integer applyNumber;

    /**
     * 报名状态, 0 不可报名, 1 可以报名
     */
    private Integer applyState;

    /**
     * 社团级别,1校级,0院级
     */
    private Integer associationGrade;

}
