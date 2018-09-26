package top.lvjp.association.entity;

import java.util.Date;

public class Activity {

    /**
     * 活动编号
     */
    private Integer activityId;

    /**
     * 所属社团编号
     */
    private Integer associationId;

    /**
     * 活动标题
     */
    private String activityTitle;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 活动内容更新时间
     */
    private Date updateTime;

    /**
     * 活动内容
     */
    private String activityText;

    /**
     * 活动图片
     */
    private String activityImage;

    /**
     * 活动标签
     */
    private String activityLabel;

    /**
     * 活动状态,0未开始,1正在进行,2已经结束
     */
    private String acstatusStatus;

    /**
     * 文章状态,0未发布,1已发布
     */
    private String textStatus;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
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

    public String getAcstatusStatus() {
        return acstatusStatus;
    }

    public void setAcstatusStatus(String acstatusStatus) {
        this.acstatusStatus = acstatusStatus;
    }

    public String getTextStatus() {
        return textStatus;
    }

    public void setTextStatus(String textStatus) {
        this.textStatus = textStatus;
    }
}
