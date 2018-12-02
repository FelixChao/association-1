package top.lvjp.association.VO;

import lombok.Data;

@Data
public class PictureVO {


    /**
     * 图标对应实体的编号, 如社团编号
     */
    private Integer id;

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

}
