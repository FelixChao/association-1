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
    private String associationId;
//
//    /**
//     * 照片类别
//     */
//    private Integer pictureCategory;

    /**
     * 上传时间
     */
    private Date createTime;

    /**
     * 上传用户
     */
    private Integer userId;

    /**
     * 是否作为图标, 数值代表被使用的次数
     */
    private Integer isIcon;

}
