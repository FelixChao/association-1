package top.lvjp.association.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ActivityInfo {

    /**
     * 活动编号
     */
    @JsonProperty("id")
    private Integer activityId;

    /**
     * 所属社团
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("name")
    private String associationName;

    /**
     * 活动标题
     */
    @JsonProperty("title")
    private String activityTitle;

    /**
     * 活动开始时间
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("start")
    private Date startTime;

    /**
     * 活动结束时间
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("end")
    private Date endTime;

    /**
     * 活动内容更新时间
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("update")
    private Date updateTime;

    /**
     * 活动内容
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("text")
    private String activityText;

    /**
     * 活动图片
     */
    @JsonProperty("image")
    private String activityImage;

    /**
     * 活动标签
     */
    @JsonProperty("label")
    private String activityLabel;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getActivityText() {
        return activityText;
    }

    public void setActivityText(String activityText) {
        this.activityText = activityText;
    }

    public String getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(String activityImage) {
        this.activityImage = activityImage;
    }

    public String getActivityLabel() {
        return activityLabel;
    }

    public void setActivityLabel(String activityLabel) {
        this.activityLabel = activityLabel;
    }
}
