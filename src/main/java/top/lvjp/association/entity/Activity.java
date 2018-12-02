package top.lvjp.association.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Activity {

    /**
     * 活动编号
     */
    private Integer activityId;

    /**
     * 所属社团编号
     */
    private Integer associationId;

    /**
     * 活动标题
     */
    private String activityTitle;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 活动内容更新时间
     */
    private Date updateTime;

    /**
     * 活动内容
     */
    private String activityText;

    /**
     * 活动图片
     */
    private String activityImage;

    /**
     * 活动标签
     */
    private String activityLabel;

    /**
     * 最大报名人数
     */
    private Integer maxApply;

    /**
     * 已报名人数
     */
    private Integer applyNumber;

    /**
     * 活动状态,0未开始,1正在进行,2已经结束
     */
    private Integer activityStatus;

    /**
     * 文章状态,0未发布,1已发布
     */
    private Integer textStatus;

}
