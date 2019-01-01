package top.lvjp.association.entity;

import lombok.Data;

import java.util.Date;

@Data
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
     * 介绍语
     */
    private String videoDescription;

    /**
     * 所属社团id
     */
    private String associationId;

    /**
     * 上传用户
     */
    private Integer userId;

    /**
     * 上传时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
