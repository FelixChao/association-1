package top.lvjp.association.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import top.lvjp.association.dto.ActivitiesDTO;

import java.util.List;

public class AssociationVO {

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
    @JsonProperty("description")
    private String associationDescription;

    /**
     * 社团logo
     */
    @JsonProperty("icon")
    private String associationIcon;

    /**
     * 社团新闻
     */
    @JsonProperty("news")
    private List<NewsInfo> newsInfos;

    /**
     * 社团相关的活动活动
     */
    private ActivitiesDTO activities;

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

    public List<NewsInfo> getNewsInfos() {
        return newsInfos;
    }

    public void setNewsInfos(List<NewsInfo> newsInfos) {
        this.newsInfos = newsInfos;
    }

    public ActivitiesDTO getActivities() {
        return activities;
    }

    public void setActivities(ActivitiesDTO activities) {
        this.activities = activities;
    }
}
