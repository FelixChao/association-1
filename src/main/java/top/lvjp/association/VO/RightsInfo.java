package top.lvjp.association.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RightsInfo {

    /**
     * 维权编号
     */
    @JsonProperty("id")
    private Integer rightsId;

    /**
     * 相关社团
     */
    @JsonProperty("association")
    private String associationName;

    /**
     * 维权标题
     */
    @JsonProperty("title")
    private String rightsTitle;

    /**
     * 维权状态, 0:未处理,1:正在处理,2:处理完成
     */
    @JsonProperty("status")
    private Integer rightsStatus;

    /**
     * 解决办法
     */
    private String solution;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @JsonProperty("time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date updateTime;
}
