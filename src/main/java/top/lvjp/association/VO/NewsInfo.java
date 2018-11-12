package top.lvjp.association.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonProperty("publish")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date publishTime;

    /**
     * 新闻更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("update")
    private Date updateTime;
}
