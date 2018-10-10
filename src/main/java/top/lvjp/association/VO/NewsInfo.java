package top.lvjp.association.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class NewsInfo {

    /**
     * 新闻编号
     */
    @JsonProperty("id")
    private Integer newsId;

    /**
     * 新闻标题
     */
    @JsonProperty("tilte")
    private String newsTitle;

    /**
     * 所属社团
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("name")
    private String associationName;

    /**
     * 新闻作者
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("author")
    private String newsAuthor;

    /**
     * 新闻摘要
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("digest")
    private String newsDigest;

    /**
     * 新闻正文内容
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("text")
    private String newsText;

    /**
     * 新闻发布时间
     */
    @JsonProperty("time")
    private Date publishTime;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
