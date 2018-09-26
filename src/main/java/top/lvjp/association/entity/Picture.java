package top.lvjp.association.entity;

import java.util.Date;

public class Picture {


    /**
     * 图片编号
     */
    private Integer pictureId;

    /**
     * 图片标题
     */
    private String pictureTitle;

    /**
     * 图片地址
     */
    private String picturePath;

    /**
     * 所属社团id
     */
    private Integer associationId;

    /**
     * 照片类别
     */
    private String pictureCategory;

    /**
     * 上传时间
     */
    private Date createTime;

    /**
     * 上传用户
     */
    private Integer userId;

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureTitle() {
        return pictureTitle;
    }

    public void setPictureTitle(String pictureTitle) {
        this.pictureTitle = pictureTitle;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    public String getPictureCategory() {
        return pictureCategory;
    }

    public void setPictureCategory(String pictureCategory) {
        this.pictureCategory = pictureCategory;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
