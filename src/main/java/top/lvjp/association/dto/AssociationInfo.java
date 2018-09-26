package top.lvjp.association.dto;

public class AssociationInfo {

    /**
     * 社团编号
     */
    private Integer associationId;

    /**
     * 社团名称
     */
    private String associationName;

    /**
     * 社团简介
     */
    private String associationDescription;

    /**
     * 社团logo
     */
    private String associationLogo;

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

    public String getAssociationLogo() {
        return associationLogo;
    }

    public void setAssociationLogo(String associationLogo) {
        this.associationLogo = associationLogo;
    }
}
