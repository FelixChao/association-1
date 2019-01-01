package top.lvjp.association.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PictureInfo {


    /**
     * 图标对应实体的编号, 如社团编号
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String associationId;

    /**
     * 图片编号
     */
    @JsonProperty("id")
    private Integer pictureId;

    /**
     * 图片标题
     */
    @JsonProperty("associationName")
    private String pictureTitle;

    /**
     * 图片地址
     */
    @JsonProperty("picture")
    private String picturePath;

}
