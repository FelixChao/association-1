package top.lvjp.association.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pictureCategory;

    /**
     * 上传时间
     */
    @JsonProperty("time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 上传用户
     */
    @JsonProperty("user")
    private String userName;

    /**
     * 是否为图徽
     */
    private Integer isIcon;
}
