package top.lvjp.association.entity;

import java.util.Date;

public class Video {

    /**
     * 视频编号
     */
    private Integer videoId;

    /**
     * 视频标题
     */
    private String videoTitle;

    /**
     * 视频路径
     */
    private String videoPath;

    /**
     * 所属社团id
     */
    private Integer associationId;

    /**
     * 上传用户
     */
    private Integer userId;

    /**
     * 上传时间
     */
    private Date createTime;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
