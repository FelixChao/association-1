package top.lvjp.association.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.lvjp.association.constant.RegexpConstant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class ActivityForm {

    /**
     * 活动编号
     */
    private Integer activityId;

    /**
     * 所属社团编号
     */
    @NotNull(message = "请选择社团")
    private Integer associationId;

    /**
     * 活动标题
     */
    @Size(min = 1,message = "请输入标题")
    private String activityTitle;

    /**
     * 活动开始时间
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date startTime;

    /**
     * 活动结束时间
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date endTime;

    /**
     * 活动内容
     */
    @Size(min = 1,message = "请输入活动内容")
    private String activityText;

    /**
     * 活动图片
     */
    private String activityImage;

    /**
     * 活动标签
     */
    @Pattern(regexp = RegexpConstant.LABEL, message = "请用逗号将标签分隔开")
    private String activityLabel;

    /**
     * 最大报名人数
     */
    @NotNull
    private Integer maxApply;

    /**
     * 文章状态,0未发布,1已发布
     */
    @NotNull
    private Integer textStatus;

}
