package top.lvjp.association.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class VideoInfo {

    /**
     * 视频编号
     */
    @JsonProperty("id")
    private Integer videoId;

    /**
     * 上传者
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String author;

    /**
     * 所属社团
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String association;

    /**
     * 视频标题
     */
    @JsonProperty("title")
    private String videoTitle;

    /**
     * 上传时间
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 视频路径
     */
    @JsonProperty("path")
    private String videoPath;

}
