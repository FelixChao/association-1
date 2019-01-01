package top.lvjp.association.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityInfo {

    /**
     * 活动编号
     */
    @JsonProperty("id")
    private Integer activityId;

    /**
     * 所属社团
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("association")
    private String associationName;

    /**
     * 活动标题
     */
    @JsonProperty("title")
    private String activityTitle;

    /**
     * 活动开始时间
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("start")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 活动结束时间
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("end")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 活动内容更新时间
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("update")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 活动内容
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("content")
    private String activityContent;

    /**
     * 活动图片
     */
    @JsonProperty("picture")
    private String picturePath;

    /**
     * 活动标签
     */
    @JsonProperty("label")
    private String activityLabel;


    /**
     * 文章发布状态
     */
    @JsonProperty("status")
    private String textStatus;

    /**
     * 最大报名人数
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("max")
    private Integer maxApply;

    /**
     * 已报名人数
     */
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("applied")
    private Integer applyNumber;

}
