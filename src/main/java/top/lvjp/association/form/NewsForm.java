package top.lvjp.association.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewsForm {

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
    @Size(min = 1,message = "请输入新闻标题")
    private String newsTitle;

    /**
     * 新闻摘要
     */
    @Size(max = 114,message = "摘要不能超过114个字")
    private String newsDigest;

    /**
     * 新闻作者
     */
    private String newsAuthor;

    /**
     * 新闻发布状态,已发布为1,未发布为0
     */
    @NotNull
    private Integer newsStatus;

    /**
     * 新闻正文内容
     */
    @Size(min = 1,message = "请输入新闻内容")
    private String newsText;

//    /**
//     * 新闻更新时间
//     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date updateTime;

    /**
     * 新闻图片
     */
    private String newsImage;
}
