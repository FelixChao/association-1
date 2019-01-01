package top.lvjp.association.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import top.lvjp.association.dto.ActivitiesDTO;

import java.util.List;

@Data
public class AssociationVO {

    /**
     * 社团编号
     */
    @JsonProperty("id")
    private String associationId;

    /**
     * 社团名称
     */
    @JsonProperty("name")
    private String associationName;

    /**
     * 社团简介
     */
    @JsonProperty("description")
    private String associationDescription;

    /**
     * 社团logo
     */
    @JsonProperty("picture")
    private String picturePath;

    /**
     * 社团新闻
     */
    @JsonProperty("news")
    private List<NewsInfo> newsInfos;

    /**
     * 社团相关的活动活动
     */
    private ActivitiesDTO activities;

}
