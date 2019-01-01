package top.lvjp.association.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.lvjp.association.constant.RegexpConstant;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    private String associationId;

    /**
     * 活动标题
     */
    @NotEmpty(message = "活动标题不能为空")
    private String activityTitle;

    /**
     * 活动开始时间
     */
    @NotNull(message = "请选择活动开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 活动结束时间
     */
    @NotNull(message = "请选择活动结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 活动内容
     */
    @NotEmpty(message = "请输入活动内容")
    private String activityContent;

    /**
     * 活动图片编号
     */
    private Integer pictureId;

    /**
     * 活动图片地址
     */
    private String picturePath;

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
