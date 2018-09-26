package top.lvjp.association.entity;

import java.util.Date;

public class News {


    /**
     * 新闻编号
     */
    private Integer newsId;

    /**
     * 所属社团编号
     */
    private Integer associationId;

    /**
     * 新闻标题
     */
    private String newsTitle;

    /**
     * 新闻摘要
     */
    private String newsDigest;

    /**
     * 新闻作者
     */
    private String newsAuthor;

    /**
     * 新闻发布状态,已发布为1,未发布为0
     */
    private String newsStatus;

    /**
     * 新闻正文内容
     */
    private String newsText;

    /**
     * 新闻更新时间
     */
    private Date updateTime;

    /**
     * 新闻图片
     */
    private String newsImage;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDigest() {
        return newsDigest;
    }

    public void setNewsDigest(String newsDigest) {
        this.newsDigest = newsDigest;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public String getNewsStatus() {
        return newsStatus;
    }

    public void setNewsStatus(String newsStatus) {
        this.newsStatus = newsStatus;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }
}
