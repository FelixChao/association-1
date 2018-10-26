package top.lvjp.association.entity;

import lombok.Data;

import java.util.Date;

@Data
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

}
