package top.lvjp.association.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PictureVO {

    /**
     * 图片编号
     */
    @JsonProperty("id")
    private Integer pictureId;

    /**
     * 图片标题
     */
    @JsonProperty("title")
    private String pictureTitle;

    /**
     * 图片地址
     */
    @JsonProperty("path")
    private String picturePath;

    /**
     * 所属社团
     */
    @JsonProperty("association")
    private String associationName;

    /**
     * 照片类别
     */
    @JsonProperty("category")
    private String pictureCategory;

    /**
     * 上传时间
     */
    @JsonProperty("time")
    private Date createTime;

    /**
     * 上传用户
     */
    @JsonProperty("user")
    private String userName;

    /**
     * 被使用次数
     */
    @JsonProperty("used")
    private Integer isIcon;
}
