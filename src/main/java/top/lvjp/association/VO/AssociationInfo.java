package top.lvjp.association.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AssociationInfo {

    /**
     * 社团编号
     */
    @JsonProperty("id")
    private Integer associationId;

    /**
     * 社团名称
     */
    @JsonProperty("name")
    private String associationName;

    /**
     * 社团简介
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("description")
    private String associationDescription;

    /**
     * 社团logo
     */
    @JsonProperty("icon")
    private String associationIcon;

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

    public String getAssociationIcon() {
        return associationIcon;
    }

    public void setAssociationIcon(String associationIcon) {
        this.associationIcon = associationIcon;
    }
}
