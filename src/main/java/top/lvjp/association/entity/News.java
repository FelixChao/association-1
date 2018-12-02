package top.lvjp.association.entity;

import lombok.Data;

import java.util.Date;

@Data
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
    private Integer newsStatus;

    /**
     * 新闻正文内容
     */
    private String newsText;

    /**
     * 新闻更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 新闻发布时间
     */
    private Date publishTime;

    /**
     * 新闻图片
     */
    private String newsImage;

}
