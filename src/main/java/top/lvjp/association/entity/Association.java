package top.lvjp.association.entity;

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
    private String associationLogo;

    /**
     * 申请人数
     */
    private Integer applyNumber;

    /**
     * 社团级别,1校级,0院级
     */
    private Integer associationGrade;

    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public String getAssociationDescription() {
        return associationDescription;
    }

    public void setAssociationDescription(String associationDescription) {
        this.associationDescription = associationDescription;
    }

    public String getAssociationCategory() {
        return associationCategory;
    }

    public void setAssociationCategory(String associationCategory) {
        this.associationCategory = associationCategory;
    }

    public String getAssociationLogo() {
        return associationLogo;
    }

    public void setAssociationLogo(String associationLogo) {
        this.associationLogo = associationLogo;
    }

    public Integer getApplyNumber() {
        return applyNumber;
    }

    public void setApplyNumber(Integer applyNumber) {
        this.applyNumber = applyNumber;
    }

    public Integer getAssociationGrade() {
        return associationGrade;
    }

    public void setAssociationGrade(Integer associationGrade) {
        this.associationGrade = associationGrade;
    }
}
