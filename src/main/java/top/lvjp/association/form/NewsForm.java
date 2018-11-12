package top.lvjp.association.form;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class NewsForm {

    /**
     * 新闻编号
     */
    private Integer newsId;

    /**
     * 所属社团编号
     */
    @NotNull
    private Integer associationId;

    /**
     * 新闻标题
     */
    @Size(min = 1)
    private String newsTitle;

    /**
     * 新闻摘要
     */
    @Size(max = 255)
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 新闻图片
     */
    private String newsImage;
}
